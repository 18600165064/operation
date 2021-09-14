package com.data.display.service.supplierService.impl;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.data.display.mapper.supplierMapper.SupplierFinaceMapper;
import com.data.display.mapper.supplierMapper.SupplierInfoMapper;
import com.data.display.mapper.supplierMapper.SupplierWithdrawJMapper;
import com.data.display.mapper.supplierMapper.SupplierWithdrawMapper;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.supplier.SupplierFinace;
import com.data.display.model.supplier.SupplierInfo;
import com.data.display.model.supplier.SupplierWithdraw;
import com.data.display.model.supplier.SupplierWithdrawJournal;
import com.data.display.model.user.SysUser;
import com.data.display.service.supplierService.SupplierFinaceService;
import com.data.display.util.BurroKit;
import com.data.display.util.OrderNum;
import com.data.display.util.StringUtil;
import com.data.display.util.UserUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.transaction.annotation.Transactional;

@Service("supplierService")
public class SupplierFinaceServiceImpl implements SupplierFinaceService{

	private static final Logger logger = LoggerFactory.getLogger(SupplierFinaceServiceImpl.class);
	
	@Resource
    private SupplierFinaceMapper supplierFinaceMapper;
	
	@Resource
    private SupplierInfoMapper supplierInfoMapper;
	
	@Resource
    private SupplierWithdrawJMapper supplierWithdrawJMapper;
	
	@Resource
    private SupplierWithdrawMapper supplierWithdrawMapper;

	@Override
	public Page<SupplierFinace> getSupplierFinaceData(DataTableDTO dataTableDTO, SupplierFinace supplierFinace) {
		PageHelper.startPage(dataTableDTO.getPageNum(), dataTableDTO.getLength());
		SysUser user = UserUtil.getUserMessage();
		SupplierInfo supplierInfo = supplierInfoMapper.selectByUserId(String.valueOf(user.getId()));
		if(supplierInfo != null){
			supplierFinace.setSid(supplierInfo.getS_id());
		}
		return supplierFinaceMapper.getSupplierFinaceData(supplierFinace);
	}

	
	@Override
	@Transactional(rollbackFor = {Exception.class,RuntimeException.class})
	public DataTableResult withdrawal(String id, String deduction_money) {
		logger.debug("接收数据：id："+id + "提现金额：" + deduction_money );
		SysUser user = UserUtil.getUserMessage();
		DataTableResult result = new DataTableResult();
		
		if (StringUtil.isBlank(deduction_money)) {
				result.setDraw(0);
	            result.setError("请输入提现金额");
	            return result;
		 }else if(Double.parseDouble(deduction_money) < 0){
				result.setDraw(0);
	            result.setError("提现金额不可以小于0，请重新输入");
	            return result;
		 }
		
		SupplierInfo supplierInfo = supplierInfoMapper.selectByUserId(String.valueOf(user.getId()));
		 if (null == supplierInfo) {
            result.setDraw(0);
            result.setError("登录用户不是供应商,请联系客服确认");
            return result;
		 }
		 if (StringUtil.isBlank(supplierInfo.getBank_account_no()) || StringUtil.isBlank(supplierInfo.getAccount_holder())) {
            result.setDraw(0);
			result.setError("供应商账户信息不完善，请先完善商户信息");
			return result;
        }
		 
		 SupplierFinace supplierFinace = supplierFinaceMapper.selectById(id);
		 if(supplierFinace!=null){
				BigDecimal can_withdraw = supplierFinace.getCan_withdraw();
				if(can_withdraw.doubleValue() < Double.parseDouble(deduction_money)){
					result.setDraw(0);
		            result.setError("您账户余额不足,请重新输入!");
		            return result;
				}
				
				//账户余额
				can_withdraw = can_withdraw.subtract(new BigDecimal(deduction_money));
				//累计提现金额
				BigDecimal already_withdraw = supplierFinace.getAlready_withdraw();
				already_withdraw = already_withdraw.add(new BigDecimal(deduction_money));
				SupplierFinace supplierFinace2 = new SupplierFinace();
				supplierFinace2.setId(Integer.parseInt(id));
				supplierFinace2.setCan_withdraw(can_withdraw);
				supplierFinace2.setAlready_withdraw(already_withdraw);
				supplierFinaceMapper.updateSupplierById(supplierFinace2);
				
				
				String num_ber = OrderNum.makeOrderNum();
				//供应商账户流水
				SupplierWithdrawJournal supplierWithdrawJournal = new SupplierWithdrawJournal();
		        supplierWithdrawJournal.setCreate_time(new Date());
		        supplierWithdrawJournal.setSid(supplierInfo.getS_id());
		        supplierWithdrawJournal.setStatus(0);
		        supplierWithdrawJournal.setWithdraw(new BigDecimal(deduction_money));
		        supplierWithdrawJournal.setWithdrawal_no("TX"+num_ber+"1");
		        supplierWithdrawJMapper.addSupplierWithdrawJ(supplierWithdrawJournal);
				
		        
		        SupplierWithdraw withdraw = new SupplierWithdraw();
				withdraw.setS_id(supplierInfo.getS_id());
				withdraw.setBack_user_id(user.getId());
				withdraw.setPublic_or_private(supplierInfo.getPublic_or_private()); //判断公私户 0：公户，1：私户
				withdraw.setS_name(supplierInfo.getS_name());
				withdraw.setS_manager(supplierInfo.getS_manager());
				withdraw.setOffice_phone(supplierInfo.getOffice_phone());
				withdraw.setCurrency(supplierInfo.getCurrency());
				withdraw.setBank_code(supplierInfo.getBank_code());
				withdraw.setBank_name(supplierInfo.getBank_name());
				withdraw.setBank_account_no(supplierInfo.getBank_account_no());
				withdraw.setAccount_holder(supplierInfo.getAccount_holder());
				withdraw.setBatch_no("TX"+num_ber+"1");
				withdraw.setSettle_status("0");//0：审核中，1：已完成
				withdraw.setCreate_time(BurroKit.current());
				withdraw.setTotal_product(new BigDecimal(deduction_money));
				withdraw.setSettle_amt_total(new BigDecimal(deduction_money));
				supplierWithdrawMapper.addSupplierWithdraw(withdraw);
		        
		 }
		 
		 result.setDraw(1);
         result.setError("申请提现成功");
         return result;
	}
	
}
