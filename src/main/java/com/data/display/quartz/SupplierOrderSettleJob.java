package com.data.display.quartz;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.ioc.aop.Aop;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import com.data.display.mapper.orderMapper.OrderDetailMapper;
import com.data.display.mapper.orderMapper.OrderMapper;
import com.data.display.mapper.orderMapper.OrderSettleMapper;
import com.data.display.mapper.userMapper.UserInfoFinaceMapper;
import com.data.display.model.order.Order;
import com.data.display.model.order.OrderDetail;
import com.data.display.model.order.OrderSettle;
import com.data.display.util.DateUtil;

/**
 * ym_order_detail
 * 供应商订单结算 ---> 将订单详情中已完成状态  的11天之前的  数据  同步到  订单结算 中
 * @author l
 *
 */
@DisallowConcurrentExecution
public class SupplierOrderSettleJob extends QuartzJobBean {

	private static Logger _log = LoggerFactory.getLogger(UserOrderSettleJob.class);

	 @Resource
	 private OrderMapper orderMapper;
	 
	 @Resource
	 private OrderDetailMapper orderDetailMapper;
	 
	 @Resource
	 private OrderSettleMapper orderSettleMapper;
	 
	 @Resource
	 private UserInfoFinaceMapper userInfoFinaceMapper;
	 
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			Date dt1= DateUtil.getStartTime2(-11);
			_log.info("开始时间============>"+DateUtil.dateToString(dt1,DateUtil.YYYY_MM_DD_HH_MM_SS));
	   	
		   	Date dt2= DateUtil.getEndTime2(-11);
		   	_log.info("结束时间============>"+DateUtil.dateToString(dt2,DateUtil.YYYY_MM_DD_HH_MM_SS));
			
	   		Order order = new Order();
			order.setStartTime(dt1);
			order.setEndTime(dt2);
			order.setOrder_status("ywc");
			order.setIf_primary(0);
			List<Order> orderList = orderMapper.selectByOthers(order);
			for (Order order2 : orderList) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setSub_order_no(order2.getSub_order_no());
				List<OrderDetail> orderDetailList = orderDetailMapper.selectByOthers(orderDetail);
				for (OrderDetail orderDetail2 : orderDetailList) {
					OrderSettle orderSettle = new OrderSettle();
					orderSettle.setUser_id(orderDetail2.getUser_id());
					orderSettle.setOrder_no(orderDetail2.getOrder_no());
					orderSettle.setSub_order_no(orderDetail2.getSub_order_no());
					orderSettle.setSub_order_no_skuid(orderDetail2.getSub_order_no()+orderDetail2.getSkuid());
					orderSettle.setSkuid(orderDetail2.getSkuid());
					orderSettle.setSku_name(orderDetail2.getSku_name());
					orderSettle.setSku_image(orderDetail2.getImagePath());
					orderSettle.setSupply_price(orderDetail2.getSupply_price());
					orderSettle.setSupplier_id(order2.getSupplier_id());
					orderSettle.setPrice(orderDetail2.getPrice());
					orderSettle.setNum(orderDetail2.getNum());
					orderSettle.setSubtotal(orderDetail2.getSubtotal());
					orderSettle.setRefund_amt(orderDetail2.getRefund_amt());
					orderSettle.setSettle_amt(orderDetail2.getShould_liquidation_money());
					orderSettle.setSettle_status(0);
					orderSettle.setCreate_time(new Date());
					orderSettleMapper.addOrderSettle(orderSettle);
				}
			}
		} catch (Exception e) {
			_log.error("供应商结算错误",e);
		}
		
	}

}
