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

import com.data.display.mapper.orderMapper.OrderMapper;
import com.data.display.mapper.orderMapper.RebateMapper;
import com.data.display.model.order.Order;
import com.data.display.model.order.Rebate;
import com.data.display.util.DateUtil;

/**
 * 查询7天之前待收货状态的子订单------->  待收货 ---> 已完成
 * @author l
 *
 */
@DisallowConcurrentExecution
public class OrderJob extends QuartzJobBean {

	 private static Logger _log = LoggerFactory.getLogger(OrderJob.class);

	 @Resource
	 private OrderMapper orderMapper;
	 
	 @Resource
	 private RebateMapper rebateMapper;
	 
	 
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			Date dt1= DateUtil.getStartTime2(-7);
			_log.info("开始时间============>"+DateUtil.dateToString(dt1,DateUtil.YYYY_MM_DD_HH_MM_SS));
	    	
	    	Date dt2= DateUtil.getEndTime2(-7);
	    	_log.info("结束时间============>"+DateUtil.dateToString(dt2,DateUtil.YYYY_MM_DD_HH_MM_SS));
	    	
			Order order = new Order();
			order.setStartTime(dt1);
			order.setEndTime(dt2);
			order.setOrder_status("dsh");
			order.setIf_primary(0);
			List<Order> orderList = orderMapper.selectByOthers(order);
			for (Order order2 : orderList) {
				order2.setOrder_status("ywc");
				order2.setYwc_time(new Date());
				orderMapper.updateByPrimaryKeySelective(order2);
				Rebate rebate = new Rebate();
				rebate.setOrder_no(order2.getOrder_no());
				List<Rebate> rebateList = rebateMapper.selectRebateByOthers(rebate);
				for (Rebate rebate2 : rebateList) {
					rebate2.setStatus(1);
					rebateMapper.updateRebate(rebate2);
				}
			}
		} catch (Exception e) {
			_log.error("订单结算失败",e);
		}
		
	}

}
