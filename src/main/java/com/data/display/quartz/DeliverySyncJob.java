package com.data.display.quartz;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import com.data.display.mapper.commodityMapper.SpuCommodityMapper;
import com.data.display.model.commodity.SpuCommodity;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.data.display.mapper.orderMapper.ExpressCompanyMapper;
import com.data.display.mapper.orderMapper.OrderMapper;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.enums.OrderStatusEnum;
import com.data.display.model.order.ExpressCompany;
import com.data.display.model.order.Order;
import com.data.display.service.PostOrderService;
import com.data.display.util.StringUtil;

/**
 * 快递100详情任务
 * @author l
 *
 */
@DisallowConcurrentExecution
public class DeliverySyncJob extends QuartzJobBean {

	 private static Logger _log = LoggerFactory.getLogger(DeliverySyncJob.class);
	
	 @Resource
	 private OrderMapper orderMapper;
	 
	 @Resource
	 private ExpressCompanyMapper expressCompanyMapper;
	 
	 @Autowired
	 private PostOrderService postOrderService;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		_log.info("postOrder-job.starts!!!!!!!!!");
		
		Order order = new Order();
		order.setOrder_status(OrderStatusEnum.DSH.getOrderStatus());
		order.setPay_status((byte)1);
		order.setIf_primary(0);
		order.setIf_self(1);
		
		List<Order> list = orderMapper.selectByOthers(order);
		_log.info("待收货订单数量"+list.size());
		for (Order order2 : list) {
			 if (StringUtil.isBlank(order2.getTrans_id()) ){
                continue;
             }
			 ExpressCompany expressCompany = new ExpressCompany();
			 expressCompany.setExpress_name(order2.getTrans_com());
			 List<ExpressCompany> exList = expressCompanyMapper.selectByOthers(expressCompany);
			 if(exList != null && exList.size() > 0){
				 DataTableResult result = postOrderService.post(exList.get(0).getCompany_code(),order2.getTrans_id());
				 if(result.getDraw() == 1){
					 _log.info(order2.getTrans_id() +" is ok");
					 order2.setIf_self(0);
					 orderMapper.updateByPrimaryKeySelective(order2);
				 }
			 }
		}
		_log.info("postOrder-job.end!!!!!!!!!");

	}

}
