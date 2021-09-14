package com.data.display.service.orderService.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import com.data.display.mapper.commodityMapper.ProServiceBillMapper;
import com.data.display.mapper.commodityMapper.ProductMapper;
import com.data.display.mapper.orderMapper.*;
import com.data.display.mapper.richMapper.InformationMapper;
import com.data.display.model.bonus.BonusPoolOutBill;
import com.data.display.model.commodity.ProServiceBill;
import com.data.display.model.commodity.Product;
import com.data.display.model.order.*;
import com.data.display.model.rich.Information;
import com.data.display.service.bonusService.YMBonusPoolOutBillService;
import com.data.display.util.DateUtil;
import com.data.display.util.StringUtil;
import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.ioc.aop.Aop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.enums.OrderStatusEnum;
import com.data.display.model.enums.PayTypeEnum;
import com.data.display.service.PayService;
import com.data.display.service.orderService.OrderService;
import com.data.display.util.BurroKit;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.transaction.annotation.Transactional;

@Service("orderService")
public class OrderServiceImpl implements OrderService{

	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	private PayService payService;
	
	@Resource
    private OrderMapper orderMapper;

	@Resource
	private ProductMapper productMapper;
	
	@Resource
    private OrderDetailMapper orderDetailMapper;
	
	@Resource
    private RebateMapper rebateMapper; 
	
	@Resource
    private RefundDetailsMapper refundDetailsMapper;

	@Resource
	private InformationMapper informationMapper;

	@Resource
	private AssembleRefundMapper assembleRefundMapper;

	@Resource
	private ProServiceBillMapper proServiceBillMapper;

	@Resource
	private YMGrouperMapper yMGrouperMapper;

	@Autowired
	private YMBonusPoolOutBillService bonusPoolOutBillService;

	@Override
	public Page<Order> getOrderData(DataTableDTO dataTableDTO,Order order) {
		PageHelper.startPage(dataTableDTO.getPageNum(), dataTableDTO.getLength());
		return orderMapper.selectByPrimaryKey(order);
	}

	@Override
	public Page<Order> getOrderByAfterStatus(DataTableDTO dataTableDTO, Order order) {
		PageHelper.startPage(dataTableDTO.getPageNum(), dataTableDTO.getLength());
		return orderMapper.getOrderByAfterStatus(order);
	}
	
	@Override
	public Page<Order> getOrderByAfterStatus2(DataTableDTO dataTableDTO, Order order) {
		PageHelper.startPage(dataTableDTO.getPageNum(), dataTableDTO.getLength());
		return orderMapper.getOrderByAfterStatus2(order);
	}
	
	@Override
	public DataTableResult editOrderById(Order order) {
		DataTableResult result = new DataTableResult();
		try {
			result.setError("保存成功");
			Order order2 = this.selectById(String.valueOf(order.getId()));
			if(order2 == null){
				result.setError("系统错误");
				return  result;
			}
			if(StringUtil.isNotBlank(order.getAfter_sale_status())){
				if("cwwtg".equals(order.getAfter_sale_status()) || "kfwtg".equals(order.getAfter_sale_status())){
					//发送消息
					Information information = new Information();
					information.setCreate_time(new Date());
					information.setUser_id(order2.getUser_id());
					information.setTitle("您的售后申请未通过");
					information.setSub_title("很抱歉的通知您,您的售后申请未通过审核，如有疑问请联系客服");
					information.setJump_page(10);
					information.setStatus(0);
					information.setMessage("很抱歉的通知您,您的售后申请未通过审核，如有疑问请联系客服");
					informationMapper.insertYmInformation(information);
					OrderDetail orderDetail2 = new OrderDetail();
					orderDetail2.setOrder_no(order2.getOrder_no());
					orderDetail2.setKefu_time(new Date());
					orderDetailMapper.updateOrderDetailByOrderNo(orderDetail2);

					//补回用户退款机会
					OrderDetail orderDetail = new OrderDetail();
					orderDetail.setSub_order_no(order2.getSub_order_no());
					List<OrderDetail> detailsList = orderDetailMapper.selectByOthers(orderDetail);
					logger.debug("订单详情数====="+detailsList.size());
					if (detailsList != null && detailsList.size() > 0){
						String skuid = detailsList.get(0).getSkuid();
						Product product = new Product();
						product.setSkuid(skuid);
						List<Product> proList = productMapper.getProductData(product);
						if (proList != null && proList.size() > 0){
							YMGrouper grouper = new YMGrouper();
							grouper.setUser_id(order2.getUser_id());
							grouper.setSpuid(proList.get(0).getSpuid());
							YMGrouper grouper2 = yMGrouperMapper.selectGrouper(grouper);
							Integer num = grouper2.getChances_num()+1000;
							grouper2.setChances_num(num);
							yMGrouperMapper.updateGrouper(grouper2);
						}
					}

				}if("cwclz".equals(order.getAfter_sale_status())){
					//发送消息
					Information information = new Information();
					information.setCreate_time(new Date());
					information.setJump_page(10);
					information.setStatus(0);
					information.setUser_id(order2.getUser_id());
					information.setTitle("您的售后申请进入最终审核");
					information.setSub_title("您的退货申请已进入最终审核，请联系客服按照引导配合完成售后流程。");
					information.setMessage("您的退货申请已进入最终审核，请联系客服按照引导配合完成售后流程。");
					informationMapper.insertYmInformation(information);
					OrderDetail orderDetail2 = new OrderDetail();
					orderDetail2.setOrder_no(order2.getOrder_no());
					orderDetail2.setKefu_time(new Date());
					orderDetailMapper.updateOrderDetailByOrderNo(orderDetail2);
				}
			}else{
				if (StringUtil.isBlank(order.getTrans_id())){
					order.setOrder_status("dfh");
				}
			}

			if (StringUtil.isNotBlank(order.getTrans_id()) && StringUtil.isNotBlank(order.getTrans_com())){
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setOrder_no(order2.getOrder_no());
				List<OrderDetail> list = orderDetailMapper.selectByOthers(orderDetail);
				//发送消息
				Information information = new Information();
				information.setCreate_time(new Date());
				information.setJump_page(7);
				information.setStatus(0);
				information.setUser_id(order2.getUser_id());
				information.setTitle("您的拼团商品已发货");
				information.setSub_title("【"+list.get(0).getSku_name()+"】商品的快递单号 ("+order.getTrans_com()+")"+order.getTrans_id()+"。您也赶快开团吧，一个团至少赚18-2600元！");
				information.setMessage("【"+list.get(0).getSku_name()+"】商品的快递单号 ("+order.getTrans_com()+")"+order.getTrans_id()+"。您也赶快开团吧，一个团至少赚18-2600元！");
				informationMapper.insertYmInformation(information);

			}

			order.setOrder_no(order2.getOrder_no());
			orderMapper.updateByOrderNo(order);
			result.setDraw(1);
		} catch (Exception e) {
			result.setError("保存失败");
			System.out.println(e);
		}
		return result;
	}


	@Override
	public Order selectById(String id) {
		return orderMapper.selectById(id);
	}


	@Override
	@Transactional(rollbackFor = {Exception.class,RuntimeException.class})
	public DataTableResult submitRefund(Order order, BigDecimal amount) {
		DataTableResult result = new DataTableResult();
		BigDecimal refundSum = new BigDecimal(0.5);
		BigDecimal refundSum2 = new BigDecimal(1);
		logger.info("传来的信息有：" +order.getId()+ ",传来的金额为：" + amount);
		order = orderMapper.selectById(String.valueOf(order.getId()));

		if(!"dfh".equals(order.getOrder_status()) && !"dsh".equals(order.getOrder_status()) && !"ywc".equals(order.getOrder_status())){
			result.setError("订单状态错误");
			return  result;
		}

		//未发货金额
		BigDecimal allAmout = order.getC_amount().add(order.getDis_fee());
		logger.info("未发货金额：" + allAmout);
		//已发货金额
		BigDecimal amountAgo = order.getC_amount();
		logger.info("已发货金额：" + amountAgo);

		//真实退款金额
//		BigDecimal realAmount = BigDecimal.ZERO;
//		AssembleRefund assembleRefund = assembleRefundMapper.selectByOrderNo(order.getOrder_no());
//		if(assembleRefund != null){
//			realAmount = amount.subtract(assembleRefund.getRefund_price());
//		}else{
//			realAmount = amount;
//		}
		logger.info("真实退款金额：" + amount);

		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setSub_order_no(order.getSub_order_no());
		 //获取子订单对应的详情
		List<OrderDetail> detailList = orderDetailMapper.selectByOthers(orderDetail);
		//根据订单详情获取对应的分佣信息rebate
		for (OrderDetail orderDetail2 : detailList) {
			//订单详情表 添加退款信息
			orderDetail2.setRefund_amt(amount);//退款金额
			orderDetail2.setRefund_num(orderDetail2.getNum());//退货数量
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            orderDetail2.setRefund_remark(sdf.format(new Date()) + "后台订单列表操作退款");//退款说明
            orderDetail2.setRefund_status("4");//0:待审核,1:审核拒绝,2:审核通过,3:退回商品已入仓,4:已退款
            orderDetail2.setCheck_time(new Date());
            orderDetailMapper.updateOrderDetail(orderDetail2);//更新订单详情的退款信息
            logger.info("detail------" + JSON.toJSONString(orderDetail2));
            //处理对应的分佣
            Rebate rebate = new Rebate();
            rebate.setOrder_no(orderDetail2.getOrder_no());
            rebate.setSkuid(orderDetail2.getSkuid());
            List<Rebate> rebateList = rebateMapper.selectRebateByOthers(rebate);
            for (Rebate rebate2 : rebateList) {
            	if (null == rebate2) {
                    continue;
                }
                logger.info("rebate---修改前---" + JSON.toJSONString(rebate2));
                if (0 == rebate2.getStatus() || 1 == rebate2.getStatus()) {
                	rebate2.setStatus(2);//状态：0,待确认;1已确认;2已取消,3,已结算',  订单退款，对应佣金取消
                } else {
                    result.setDraw(0);
    	            result.setError("订单分佣已结算，不可退款,分佣状态:" + rebate2.getStatus());
    	            return result;
                }
                rebateMapper.updateRebate(rebate2);
                logger.info("rebate---修改后---" + JSON.toJSONString(rebate2));
			}
		}
			  logger.info("支付总金额为（含运费）：" + allAmout);

		      Order order2 = new Order();
		      order2.setOrder_no(order.getOrder_no());
	    	  order2.setIf_refund("2");//是否有退款 0:没退款,1:部分退款,2:全部退款
	    	  order2.setOrder_status(OrderStatusEnum.CLOSE.getOrderStatus());
	    	  order2.setTo_examine_status("ytk");
	    	  order2.setAfter_sale_status("cwytg");
	    	  order2.setOrder_close_status("tkgb");
			  order2.setOrder_status("close");
	    	  order2.setUpdate_time(new Date());

	    	  OrderDetail orderDetail2 = new OrderDetail();
	    	  orderDetail2.setOrder_no(order.getOrder_no());
	    	  orderDetail2.setCheck_time(new Date());
	    	  orderDetailMapper.updateOrderDetailByOrderNo(orderDetail2);
	    	  //XXX 此处根据退款信息添加微信退款接口
	    	  orderMapper.updateByOrderNo(order2);
			  logger.info("订单号：" + order.getOrder_no());
	    	  if(PayTypeEnum.payType(order.getPay_type()) == PayTypeEnum.WXSMALL){
				  logger.info("测试退款金额：" + refundSum);
//	    		  DataTableResult results = payService.refundWx(refundSum2.multiply(new BigDecimal(100)).intValue(),refundSum.multiply(new BigDecimal(100)).intValue(), order.getOrder_no(), order.getSub_order_no() + "R");
	    		  DataTableResult results = payService.refundWx(allAmout.multiply(new BigDecimal(100)).intValue(),amount.multiply(new BigDecimal(100)).intValue(), order.getOrder_no(), order.getSub_order_no() + "R");
	    		  if(results.getDraw()==0){
	    		  	  throw new RuntimeException("微信退款失败");
	    		  }
	    	  }
	    	  //插入退款详情 （全部退款）
	    	  RefundDetails refundDetails = new RefundDetails();
	    	  refundDetails.setOrder_no(order.getOrder_no());
	    	  refundDetails.setSub_order_no(order.getSub_order_no());
	    	  refundDetails.setIf_refund(2); //1：部分退款 2：全部退款
	    	  refundDetails.setOrder_money(allAmout); //订单金额（主订单金额）
	    	  refundDetails.setRefund_money(amount); // 退款金额
	    	  refundDetails.setCreate_time(BurroKit.current());
	    	  logger.info("RefundDetails====》" + JSON.toJSONString(refundDetails));
	    	  refundDetailsMapper.addRefundDetails(refundDetails);


	    	  //退款扣除对应的合伙人和股东的分红

		       Calendar c = Calendar.getInstance();
		       c.setTime(order.getPay_time());
		       c.add(Calendar.DAY_OF_MONTH, 1);

			  String time = DateUtil.dateToString(c.getTime(),DateUtil.YYYY_MM_DD);
			  //股东
			  if (order.getStockerholder_id() != 0){
					List<BonusPoolOutBill> billList =  bonusPoolOutBillService.getData4(String.valueOf(order.getStockerholder_id()),time,"2");
					for (int i = 0; i < billList.size(); i++) {
						if (detailList != null && detailList.size() > 0){
							BigDecimal num = new BigDecimal(detailList.get(0).getNum());
							BigDecimal deduct = billList.get(i).getDeduct_amount();
							billList.get(i).setDeduct_amount(deduct.add(billList.get(i).getEven_bonus().multiply(num)));
							bonusPoolOutBillService.updateOutBill(billList.get(i));
						}
					}
			   }
			   //合伙人
			   if (order.getPartener_id() != 0){
					List<BonusPoolOutBill> billList =  bonusPoolOutBillService.getData4(String.valueOf(order.getPartener_id()),time,"1");
					for (int i = 0; i < billList.size(); i++) {
						if (detailList != null && detailList.size() > 0){
							BigDecimal num = new BigDecimal(detailList.get(0).getNum());
							BigDecimal deduct = billList.get(i).getDeduct_amount();
							billList.get(i).setDeduct_amount(deduct.add(billList.get(i).getEven_bonus().multiply(num)));
							bonusPoolOutBillService.updateOutBill(billList.get(i));
						}
					}
			   }



			//商品服务商
			ProServiceBill proServiceBill = new ProServiceBill();
			proServiceBill.setOrder_no(order.getOrder_no());
			List<ProServiceBill> proList = proServiceBillMapper.getProServiceByOrderNo(proServiceBill);
			for (int i = 0; i < proList.size(); i++) {
				logger.debug("ProServiceBill开始前====》" + JSON.toJSONString(proList.get(i)));
				if (proList.get(i).getStatus() != 2 && proList.get(i).getStatus() != 7){
					if (proList.get(i).getType() == 1){
						proList.get(i).setType(0);
					}else{
						proList.get(i).setType(1);
					}
					proList.get(i).setCreate_time(new Date());
					proList.get(i).setId(null);
				logger.info("ProServiceBill修改后====》" + JSON.toJSONString(proList.get(i)));
					proServiceBillMapper.insertProServiceBill(proList.get(i));
				}
			}



			//发送消息
			Information information = new Information();
			information.setCreate_time(new Date());
			information.setJump_page(0);
			information.setStatus(0);
			information.setUser_id(order.getUser_id());
			information.setTitle("您的售后申请最终审核通过");
			information.setSub_title("您的退货申请最终审核通过，退款"+amount+"元已经退回您的微信账户，请您查收。");
			information.setMessage("您的退货申请最终审核通过，退款"+amount+"元已经退回您的微信账户，请您查收。。");
			informationMapper.insertYmInformation(information);

		 result.setDraw(1);
         result.setError("退款成功");
         return result;
	}

	@Override
	public Map<String,Object> getOtherData(String id) {
		Map<String,Object> map = orderMapper.getOtherData(id);
		return map;
	}


	@Override
	public List<Map<String,Object>> exportPrem(String s_id,String spuid){
		List<Map<String,Object>> map = orderMapper.exportPrem(s_id,spuid);
		return map;
	}

	@Override
	public List<Order> selectByOthers(Order order){
		return orderMapper.selectByOthers(order);
	};


	@Override
	public int updateByPrimaryKey(Order order){
		return orderMapper.updateByPrimaryKey(order);
	}


	@Override
	public DataTableResult selectRefundPrice(String id){
		DataTableResult result = new DataTableResult();
		BigDecimal amount = BigDecimal.ZERO;
		Order order = orderMapper.selectById(id);
		if(order == null){
			result.setError("系统错误");
			return result;
		}
		if("dfh".equals(order.getOrder_status()) || "dsh".equals(order.getOrder_status()) || "ywc".equals(order.getOrder_status())){
			if("dfh".equals(order.getOrder_status())){
				amount = order.getC_amount().add(order.getDis_fee());
			}
			if("dsh".equals(order.getOrder_status()) || "ywc".equals(order.getOrder_status())){
				amount = order.getC_amount();
			}
		}else{
			result.setError("当前状态不支持退款");
			return result;
		}

		//真实退款金额
		BigDecimal realAmount = BigDecimal.ZERO;
		AssembleRefund assembleRefund = assembleRefundMapper.selectByOrderNo(order.getOrder_no());
		if(assembleRefund != null){
			realAmount = amount.subtract(assembleRefund.getRefund_price());
		}else{
			realAmount = amount;
		}
		result.setDraw(1);
		result.setData(realAmount);

		return result;
	}

	@Override
	public List<Map<String, Object>> exportRefund() {
		return orderMapper.exportRefund();
	}


}
