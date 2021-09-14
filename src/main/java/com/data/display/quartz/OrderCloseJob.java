package com.data.display.quartz;

import com.data.display.mapper.orderMapper.OrderMapper;
import com.data.display.mapper.orderMapper.RebateMapper;
import com.data.display.model.order.Order;
import com.data.display.model.order.Rebate;
import com.data.display.util.DateUtil;
import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.ioc.aop.Aop;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 查询待付款订单改为关闭状态
 * @author l
 *
 */
@DisallowConcurrentExecution
public class OrderCloseJob extends QuartzJobBean {

	 private static Logger _log = LoggerFactory.getLogger(OrderCloseJob.class);

	 @Resource
	 private OrderMapper orderMapper;
	 
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			Calendar beforeTime = Calendar.getInstance();
			beforeTime.add(Calendar.MINUTE, -20);// 20分钟之前的时间
			Date beforeD = beforeTime.getTime();
			_log.info("开始时间============>"+DateUtil.dateToString(beforeD,DateUtil.YYYY_MM_DD_HH_MM_SS));

			Order order2 = new Order();
			order2.setOrder_status("dfk");
			order2.setGroup_num("0");
			order2.setStartTime(beforeD);
			order2.setEndTime(new Date());
			List<Order> orderList = orderMapper.selectByOthers2(order2);
			for (Order order3 : orderList) {
				order3.setOrder_status("close");
				order3.setOrder_close_status("csgb");
				order3.setUpdate_time(new Date());
				orderMapper.updateByPrimaryKeySelective(order3);
			}
		} catch (Exception e) {
			_log.error("订单状态更改失败",e);
		}
		
	}

}
