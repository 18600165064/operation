package com.data.display.quartz;

import javax.annotation.Resource;

import org.nutz.json.Json;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import com.data.display.mapper.commodityMapper.ProductMapper;
import com.data.display.mapper.commodityMapper.SpuCommodityMapper;
import com.data.display.model.commodity.SpuCommodity;
import com.data.display.service.IJobAndTriggerService;
import com.data.display.util.BaseJob;

/**
 * 商品维护任务
 * @author l
 *
 */
@Component
@Configuration
@EnableScheduling
public class ProductMaintainJob implements BaseJob{

	 private static Logger _log = LoggerFactory.getLogger(ProductMaintainJob.class);
	
	 @Resource
     private SpuCommodityMapper spuCommodityMapper;
	 
	 @Resource
     private ProductMapper productMapper; 
	 
	 @Autowired
	 private IJobAndTriggerService jobAndTriggerService;
	 
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			int type = 0;
			SpuCommodity spuCommodity = new SpuCommodity();
			Object pid = context.getJobDetail().getJobDataMap().get("id");
			spuCommodity.setId(Integer.parseInt(String.valueOf(pid)));
			spuCommodity.setMaintain_status(2);
			spuCommodity.setOn_sale(1);
			_log.info("商品详情修改前============"+ Json.toJson(spuCommodity));
			type = spuCommodityMapper.editOnSale(spuCommodity);
			_log.info("商品详情修改后============"+ Json.toJson(spuCommodity));
			if(type == 1){
				jobAndTriggerService.deleteJob("com.data.display.quartz.ProductMaintainJob","adminGroup-"+spuCommodity.getId()+"");
			}
		} catch (Exception e) {
			_log.error("维护商品任务错误", e);
		}
	}

	

}
