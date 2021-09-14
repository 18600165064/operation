package com.data.display.service.supplierService.impl;

import com.data.display.mapper.orderMapper.OrderSettleMapper;
import com.data.display.mapper.supplierMapper.SupplierFinaceMapper;
import com.data.display.mapper.supplierMapper.SupplierInfoMapper;
import com.data.display.mapper.supplierMapper.SupplierWithdrawJMapper;
import com.data.display.mapper.supplierMapper.SupplierWithdrawMapper;
import com.data.display.mapper.userMapper.UserInfoMapper;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.order.OrderSettle;
import com.data.display.model.supplier.SupplierFinace;
import com.data.display.model.supplier.SupplierInfo;
import com.data.display.model.supplier.SupplierWithdraw;
import com.data.display.model.supplier.SupplierWithdrawJournal;
import com.data.display.model.user.SysUser;
import com.data.display.model.user.UserInfo;
import com.data.display.service.PayService;
import com.data.display.service.supplierService.SupplierWithdrawService;
import com.data.display.util.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("SupplierWithdrawService")
public class SupplierWithdrawServiceImpl implements SupplierWithdrawService{

	private static final Logger logger = LoggerFactory.getLogger(SupplierWithdrawServiceImpl.class);
	
	@Resource
    private SupplierWithdrawMapper supplierWithdrawMapper;
	
	@Resource
    private SupplierWithdrawJMapper supplierWithdrawJMapper;
	
	@Resource
    private SupplierInfoMapper supplierInfoMapper;
	
	@Resource
    private SupplierFinaceMapper supplierFinaceMapper;
	
	@Resource
    private OrderSettleMapper orderSettleMapper; 
	
	@Resource
    private UserInfoMapper userInfoMapper;
	
	@Autowired
	private PayService payService;
	
	
	
	@Override
	public Page<SupplierWithdraw> getSupplierWithdrawData(DataTableDTO dataTableDTO,SupplierWithdraw supplierWithdraw) {
		PageHelper.startPage(dataTableDTO.getPageNum(), dataTableDTO.getLength());
		return supplierWithdrawMapper.getSupplierWithdrawData(supplierWithdraw);
	}

	@Override
	public DataTableResult liquidationAgo(SupplierWithdraw supplierWithdraw) {
		int update=0;
		SysUser user = UserUtil.getUserMessage();
		List<OrderSettle> orderSettleslist= new ArrayList<>();
		DataTableResult result = new DataTableResult();
		supplierWithdraw = supplierWithdrawMapper.selectById(String.valueOf(supplierWithdraw.getId()));
		SupplierInfo supplierInfo = supplierInfoMapper.selectById(String.valueOf(supplierWithdraw.getS_id()));
		 if (null == supplierInfo) {
            result.setDraw(0);
            result.setError("该供应商信息缺失,请联系客服确认");
            return result;
		 }
		 if (supplierInfo.getPublic_or_private() == 1) {
            result.setDraw(0);
            result.setError("此账户类型为私户");
            return result;
		 }
		 if ("1".equals(supplierWithdraw.getSettle_status())) {//0:待清算 1:已清算
	            result.setDraw(0);
	            result.setError("该提现请求已处理,不能再次清算");
	            return result;
	     }
		 supplierWithdraw.setBack_user_id(user.getId());//操作确认清算功能的后台登录人员的编号
         String settle_status = supplierWithdraw.getSettle_status();
         logger.info("----settle_status的值是-----"+settle_status);
         BigDecimal withdrawMoney = BigDecimal.ZERO;
         if(settle_status.equals("2")){
             supplierWithdraw.setSettle_status("1");//0:待清算 1:已清算 2:预付款
             
             OrderSettle orderSettle = new OrderSettle();
             orderSettle.setBatch_no(supplierWithdraw.getBatch_no());
             orderSettle.setSettle_status(3);
             orderSettleslist=orderSettleMapper.getOrderSettleList(orderSettle);
             for (int i = 0; i < orderSettleslist.size(); i++) {
            	 orderSettleslist.get(i).setSettle_status(1);
            	 orderSettleMapper.updateOrderSettle(orderSettleslist.get(i));
			}

         }else{
             supplierWithdraw.setSettle_status("1");//0:待清算 1:已清算 2:预付款

             OrderSettle orderSettle = new OrderSettle();
             orderSettle.setBatch_no(supplierWithdraw.getBatch_no());
             orderSettle.setSettle_status(2);
             orderSettleslist=orderSettleMapper.getOrderSettleList(orderSettle);
             
             for (int i = 0; i < orderSettleslist.size(); i++) {
            	 orderSettleslist.get(i).setSettle_status(1);
            	 orderSettleMapper.updateOrderSettle(orderSettleslist.get(i));
			 }

             if (orderSettleslist.size()>0 && null!=orderSettleslist) {
                 for (OrderSettle orderSettles:orderSettleslist) {
                     withdrawMoney = withdrawMoney.add(orderSettles.getSettle_amt());
                 }
             }

         }
         
         supplierWithdraw.setSettle_time(new Date());
         supplierWithdrawMapper.editSupplierWithdraw(supplierWithdraw);
         logger.info("-----Batch_no-----"+supplierWithdraw.getBatch_no());
         
         if (orderSettleslist.size() < 1){
             	result.setDraw(0);
	            result.setError("清算单 数量异常");
	            return result;
         }




         if (orderSettleslist.size() != update){
                result.setDraw(0);
	            result.setError("更改清算单状态失败2");
	            return result;
         }
         
         boolean b = BigDecimals.compareIf(supplierWithdraw.getSettle_amt_total(), ">", withdrawMoney);
         if (b){
                result.setDraw(0);
	            result.setError("提现金额 大于 结算金额,请重新核算");
	            return result;
         }

         result.setDraw(1);
         result.setError("清算成功");
         return result;
	}

	@Override
	@Transactional(rollbackFor = {Exception.class,RuntimeException.class})
	public DataTableResult submit(SupplierWithdraw supplierWithdraw) {
		int update=0;
		BigDecimal amount = supplierWithdraw.getConfirm_amt_total();
		SysUser user = UserUtil.getUserMessage();
		DataTableResult result = new DataTableResult();
		supplierWithdraw = supplierWithdrawMapper.selectById(String.valueOf(supplierWithdraw.getId()));
		SupplierInfo supplierInfo = supplierInfoMapper.selectById(String.valueOf(supplierWithdraw.getS_id()));
		 if (null == supplierInfo) {
            result.setDraw(0);
            result.setError("该供应商信息缺失,请联系客服确认");
            return result;
		 }
		 if (supplierInfo.getPublic_or_private() == 0) {
            result.setDraw(0);
            result.setError("此账户类型为公户,不能再次清算");
            return result;
		 }
		 if ("1".equals(supplierWithdraw.getSettle_status())) {//0:待清算 1:已清算
	            result.setDraw(0);
	            result.setError("该提现请求已处理,不能再次清算");
	            return result;
	     }
		 
		 if (supplierWithdraw.getSettle_amt_total().compareTo(new BigDecimal(0)) <= 0) {
			 result.setDraw(0);
	         result.setError("提现金额小于0，不可提现");
	         return result;
		 } 
		 
		UserInfo userInfo = new UserInfo();
		userInfo.setUser_id(supplierInfo.getJd_user_id());
		List<UserInfo> list = userInfoMapper.getUserInfoListByOthers(userInfo);
		if(list.size() == 0 || StringUtil.isBlank(list.get(0).getOpen_id_small())){
			result.setDraw(0);
			result.setError("该用户未通过微信登录注册");
			return result;
		}
		 
		
		 if (!supplierWithdraw.getBatch_no().startsWith("TX")) {
			 	//看该笔 提现明细 是否有对应 清算单
				OrderSettle orderSettle = new OrderSettle();
				orderSettle.setBatch_no(supplierWithdraw.getBatch_no());
				int count = orderSettleMapper.count(orderSettle);
				if (count < 1){
		            result.setDraw(0);
					result.setError("提现订单异常");
					return result;
		        }
				BigDecimal withdrawMoney = BigDecimal.ZERO;
				List<OrderSettle> orderSettleslist = orderSettleMapper.getOrderSettleList(orderSettle);
				
				// 更改 清算单 明细
				String settle_status =supplierWithdraw.getSettle_status();
				 if(settle_status.equals("2")){
		             
		             OrderSettle orderSettle2 = new OrderSettle();
		             orderSettle2.setBatch_no(supplierWithdraw.getBatch_no());
		             orderSettle2.setSettle_status(3);
		             orderSettleslist=orderSettleMapper.getOrderSettleList(orderSettle2);
		             for (int i = 0; i < orderSettleslist.size(); i++) {
		            	 orderSettleslist.get(i).setSettle_status(1);
		            	 update=orderSettleMapper.updateOrderSettle(orderSettleslist.get(i));
		            	 update ++;
					}

		             supplierWithdraw.setSettle_status("1");//0:待清算 1:已清算 2:预付款
		         }else{
		        	 
		        	 if (orderSettleslist.size()>0 && null!=orderSettleslist) {
		                 for (OrderSettle orderSettles:orderSettleslist) {
		                     withdrawMoney = withdrawMoney.add(orderSettles.getSettle_amt());
		                 }
		             }
		        	 
		        	 
		        	 OrderSettle orderSettle3 = new OrderSettle();
		             orderSettle3.setBatch_no(supplierWithdraw.getBatch_no());
		             orderSettle3.setSettle_status(2);
		             orderSettleslist=orderSettleMapper.getOrderSettleList(orderSettle3); 
		             
		             for (int i = 0; i < orderSettleslist.size(); i++) {
		            	 orderSettleslist.get(i).setSettle_status(1);
		            	 update=orderSettleMapper.updateOrderSettle(orderSettleslist.get(i));
		            	 update ++;
					 }
		             supplierWithdraw.setSettle_status("1");//0:待清算 1:已清算 2:预付款
		         }
				 
				 if (orderSettleslist.size() != update){
		             result.setDraw(0);
		             result.setError("更改清算单状态失败");
		             return result;
		         }
				 
				 logger.info("-----Batch_no-----"+supplierWithdraw.getBatch_no());
			     logger.info("---------"+amount);
			     logger.info("-----withdrawMoney的值是----"+withdrawMoney);
			     
			     if(amount.compareTo(BigDecimal.ZERO) == 0){
			            boolean b = BigDecimals.compareIf(amount, ">", withdrawMoney);
			            if (b){
			            	result.setDraw(0);
				            result.setError("提现金额 大于 结算金额,请重新核算");
				            return result;
			            }
			            logger.debug("==========确认清算金额为空==========");
			            DataTableResult result2 = payService.balanceWithdraw(list.get(0).getUser_id(),list.get(0).getOpen_id_small(), supplierWithdraw.getSettle_amt_total().toString(),
			                    supplierWithdraw.getBatch_no(),""); //openid , 提现金额 , 批次号
			            if (result2.getDraw() == 0) {
							throw new RuntimeException("确认清算错误");
			            }

			        }else{

			            boolean b = BigDecimals.compareIf(amount, ">", withdrawMoney);
			            if (b){
			            	result.setDraw(0);
				            result.setError("提现金额 大于 结算金额,请重新核算");
				            return result;
			            }
			            logger.debug("==========确认清算金额不为空=========="+amount);
			            DataTableResult result3 = payService.balanceWithdraw(list.get(0).getUser_id(),list.get(0).getOpen_id_small(), amount.toString(),supplierWithdraw.getBatch_no(),""); //openid , 提现金额 , 批次号
			            if (result3.getDraw() == 0) {
							throw new RuntimeException("确认清算错误");
			            }

			        }
			     
			     supplierWithdraw.setBack_user_id(user.getId());//操作确认清算功能的后台登录人员的编号
				 supplierWithdraw.setSettle_time(new Date());
				 supplierWithdrawMapper.editSupplierWithdraw(supplierWithdraw);
			     result.setError("清算成功");
			     result.setDraw(1);
				 return result;
				 
				 
		 }
		
		
		 //流水修改状态
		 SupplierWithdrawJournal supplierWithdrawJournal = new SupplierWithdrawJournal();
		 supplierWithdrawJournal.setStatus(1);
		 supplierWithdrawJournal.setWithdrawal_no(supplierWithdraw.getBatch_no());
		 supplierWithdrawJMapper.updateSupplierWithdrawJ(supplierWithdrawJournal);
		 logger.info("---------"+supplierWithdraw.getConfirm_amt_total());
		 
		 DataTableResult result2 = payService.balanceWithdraw(list.get(0).getUser_id(),list.get(0).getOpen_id_small(), supplierWithdraw.getSettle_amt_total().toString(),
                 supplierWithdraw.getBatch_no(),""); //openid , 提现金额 , 批次号
         if (result2.getDraw() == 0) {
         	result.setError(result2.getError());
         	return result;
         }
	     
         supplierWithdraw.setBack_user_id(user.getId());//操作确认清算功能的后台登录人员的编号
         supplierWithdraw.setSettle_status("1");//0:待清算 1:已清算
		 supplierWithdraw.setSettle_time(new Date());
		 supplierWithdrawMapper.editSupplierWithdraw(supplierWithdraw);
	     result.setError("清算成功");
	     result.setDraw(1);
		 return result;
	}
	
	/**
	 * 一键提现
	 */
	@Override
	public DataTableResult apply() {
		DataTableResult result = new DataTableResult();
		SysUser user = UserUtil.getUserMessage();
		BigDecimal surplusMoney = BigDecimal.ZERO;
		SupplierInfo supplierInfo = supplierInfoMapper.selectByUserId(String.valueOf(user.getId()));
		if(supplierInfo == null){
			result.setDraw(0);
			result.setError("非供应商登录,不可操作提现");
			return result;
		}
		if(supplierInfo!=null &&supplierInfo.getStatus()==1){
			result.setDraw(0);
			result.setError("当前账户不能提现，请联系相关人员");
			return result;
        }
		
		if (StringUtil.isBlank(supplierInfo.getBank_account_no()) || StringUtil.isBlank(supplierInfo.getAccount_holder())) {
            result.setDraw(0);
			result.setError("供应商账户信息不完善，请先完善商户信息");
			return result;
        }
		
		OrderSettle orderSettle = new OrderSettle();
		orderSettle.setSupplier_id(supplierInfo.getS_id());
        orderSettle.setSettle_status(0); //0:未清算 1:已清算 2:处理中
        List<OrderSettle> orderSettleslist=orderSettleMapper.getOrderSettleList(orderSettle);
        if (orderSettleslist.size() == 0) {
            result.setDraw(0);
			result.setError("当前没有待提现的订单清算记录");
			return result;
        }
        
        
//        SupplierFinace supplierFinace = new SupplierFinace();
//        supplierFinace.setSid(supplierInfo.getS_id());
//        supplierFinace = supplierFinaceMapper.selectByOthers(supplierFinace);
//        if (supplierFinace == null) {
//        	SupplierFinace supplierFinance1 = new SupplierFinace();
//            supplierFinance1.setSid(supplierInfo.getS_id());
//            supplierFinance1.setBond(new BigDecimal(0));
//            supplierFinance1.setIncome(new BigDecimal(0));
//            supplierFinance1.setFrozen(new BigDecimal(0));
//            supplierFinance1.setCan_withdraw(new BigDecimal(0));
//            supplierFinance1.setRefund(new BigDecimal(0));
//            supplierFinance1.setAlready_withdraw(new BigDecimal(0));
//            supplierFinance1.setCreate_time(BurroKit.current());
//            supplierFinaceMapper.addSupplierFinace(supplierFinance1);
//        }
        
        
        String batch_no = OrderNum.makeOrderNum();//批次号
        //统计生成一条供应商提现记录，供财务审核，查看
        Object settle_amt = orderSettleMapper.countBySettleStatus(orderSettle);
        logger.info("settle_amt_total------" + settle_amt);
        BigDecimal settleAmtTotal = (BigDecimal) settle_amt;//当前批次提现总金额
        if (null == settle_amt) {
            result.setDraw(0);
			result.setError("金额求和失败");
			return result;
        }
        
        logger.info("--------settleAmtTotal的值是------"+settleAmtTotal);
        SupplierFinace supplierFinace2 = new SupplierFinace();
        supplierFinace2.setSid(supplierInfo.getS_id());
        supplierFinace2 = supplierFinaceMapper.selectByOthers(supplierFinace2);
        if (supplierFinace2 != null) {
        	//钱包
        	surplusMoney= supplierFinace2.getCan_withdraw().add(settleAmtTotal);
        	supplierFinace2.setCan_withdraw(surplusMoney);
        	supplierFinaceMapper.updateSupplierBySid(supplierFinace2);
        }
        
        SupplierWithdrawJournal supplierWithdrawJournal = new SupplierWithdrawJournal();
        supplierWithdrawJournal.setCreate_time(new Date());
        supplierWithdrawJournal.setSid(supplierInfo.getS_id());
        supplierWithdrawJournal.setStatus(0);
        supplierWithdrawJournal.setWithdraw(settleAmtTotal);
        supplierWithdrawJMapper.addSupplierWithdrawJ(supplierWithdrawJournal);
        
        
       //修改提现记录JdOrderSettle的批次号和清算状态 方法1
       OrderSettle orderSettle2 = new OrderSettle();
       orderSettle2.setBatch_no(batch_no);
       orderSettle2.setSettle_status(1);
       orderSettle2.setSupplier_id(supplierInfo.getS_id());
       orderSettle2.setSettle_status2(0);
       orderSettleMapper.updateOrderSettleOthers(orderSettle2);
        
       result.setDraw(1);
	   result.setError("提现成功");
	   return result;
	}

}
