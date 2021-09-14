package com.data.display.quartz;

import com.data.display.mapper.commodityMapper.SpuCommodityMapper;
import com.data.display.model.commodity.SpuCommodity;
import org.nutz.json.Json;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;
import java.util.List;

@DisallowConcurrentExecution
public class SpuSaleAmountJob extends QuartzJobBean {

    private static Logger _log = LoggerFactory.getLogger(SpuSaleAmountJob.class);

    @Resource
    private SpuCommodityMapper spuCommodityMapper;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        List<SpuCommodity> spuCommodityList = spuCommodityMapper.selectAll();
        _log.info("上架状态的产品数量"+ spuCommodityList.size());
        for (int i = 0; i < spuCommodityList.size(); i++) {
            int temp =(int)((70)*Math.random()+80);
            int count = spuCommodityList.get(i).getSale_amount();
            spuCommodityList.get(i).setSale_amount(count+temp);
            spuCommodityMapper.updateByPrimaryKeySelective(spuCommodityList.get(i));
        }
    }
}
