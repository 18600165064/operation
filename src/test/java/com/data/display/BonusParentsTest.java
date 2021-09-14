package com.data.display;

import com.alibaba.fastjson.JSON;
import com.data.display.mapper.orderMapper.OrderMapper;
import com.data.display.mapper.orderMapper.RebateMapper;
import com.data.display.mapper.platformMapper.YMAscriptionMapper;
import com.data.display.mapper.userMapper.UserAccountBillMapper;
import com.data.display.mapper.userMapper.UserInfoFinaceMapper;
import com.data.display.model.bonus.BonusPartener;
import com.data.display.model.bonus.BonusPool;
import com.data.display.model.bonus.BonusPoolOutBill;
import com.data.display.model.order.Order;
import com.data.display.model.order.Rebate;
import com.data.display.model.platform.YMAscription;
import com.data.display.model.user.UserAccountBill;
import com.data.display.model.user.UserInfoFinace;
import com.data.display.model.user.YmAcountBill;
import com.data.display.service.bonusService.YMBonusPartenerService;
import com.data.display.service.bonusService.YMBonusPoolOutBillService;
import com.data.display.service.bonusService.YMBonusPoolService;
import com.data.display.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BonusParentsTest {

    private static Logger _log = LoggerFactory.getLogger(BonusParentsTest.class);

    @Autowired
    YMBonusPoolService bonusPoolService;

    @Autowired
    YMBonusPartenerService bonusPartenerService;

    @Autowired
    YMBonusPoolOutBillService bonusPoolOutBillService;

    @Test
    public void contextLoads() throws Exception {

        try{
            String time = DateUtil.dateToString(DateUtil.getAddMonth(-1),DateUtil.YYYY_MM_DD);
            String month = time.substring(0,7);
            String time2 = DateUtil.dateToString(DateUtil.getAddDay(-18),DateUtil.YYYY_MM_DD);
            _log.info("更改股东结算状态截止时间============>"+time2);
            List<BonusPoolOutBill> outBillList = bonusPoolOutBillService.getData2("2",time2);
            for (int i = 0; i < outBillList.size(); i++) {
                outBillList.get(i).setUpdate_time(new Date());
                outBillList.get(i).setSettle_time(new Date());
                outBillList.get(i).setStatus(1);
                outBillList.get(i).setRemark(time+"股东分红结算");
                outBillList.get(i).setMonth(time2);
                bonusPoolOutBillService.updateOutBillByUserid(outBillList.get(i));
            }
        }catch (Exception e){
            _log.error("更改股东结算状态错误"+ JSON.toJSON(e));
        }

    }

}

