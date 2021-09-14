package com.data.display.quartz;


import com.alibaba.fastjson.JSON;
import com.data.display.mapper.bonusMapper.BonusPartenerMapper;
import com.data.display.mapper.bonusMapper.BonusStockholderMapper;
import com.data.display.mapper.orderMapper.OrderMapper;
import com.data.display.mapper.userMapper.UserInfoFinaceMapper;
import com.data.display.model.bonus.BonusPartener;
import com.data.display.model.bonus.BonusPool;
import com.data.display.model.bonus.BonusPoolOutBill;
import com.data.display.model.bonus.BonusStockholder;
import com.data.display.model.user.UserInfoFinace;
import com.data.display.service.bonusService.YMBonusPoolOutBillService;
import com.data.display.service.bonusService.YMBonusPoolService;
import com.data.display.util.DateUtil;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 更改股东结算状态
 */
@DisallowConcurrentExecution
public class BonusStockholderSettleJob extends QuartzJobBean {

    private static Logger _log = LoggerFactory.getLogger(BonusStockholderSettleJob.class);

    @Autowired
    YMBonusPoolOutBillService bonusPoolOutBillService;

    @Autowired
    YMBonusPoolService bonusPoolService;


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        try{
            String time2 = DateUtil.dateToString(DateUtil.getAddDay(-18),DateUtil.YYYY_MM_DD);
            _log.info("更改股东结算状态截止时间============>"+time2);

            BonusPool bonusPool = new BonusPool();
            List<BonusPool> poolSpuidList = bonusPoolService.getDataBySpuid(bonusPool);

            List<BonusPoolOutBill> outBillList = bonusPoolOutBillService.getData2("2",time2);
            for (int x = 0; x < poolSpuidList.size(); x++) {
                for (int i = 0; i < outBillList.size(); i++) {
                    outBillList.get(i).setUpdate_time(new Date());
                    outBillList.get(i).setSettle_time(new Date());
                    outBillList.get(i).setStatus(1);
                    outBillList.get(i).setRemark(DateUtil.dateToString(new Date(),DateUtil.YYYY_MM_DD)+"股东分红结算");
                    outBillList.get(i).setMonth(time2);
                    outBillList.get(i).setSpuid(poolSpuidList.get(x).getSpuid());
                    bonusPoolOutBillService.updateOutBillByUserid(outBillList.get(i));
                }
            }
        }catch (Exception e){
            _log.error("更改股东结算状态错误"+ JSON.toJSON(e));
        }

    }

}
