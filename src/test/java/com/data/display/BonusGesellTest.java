package com.data.display;

import com.data.display.mapper.userMapper.UserAccountBillMapper;
import com.data.display.mapper.userMapper.UserInfoFinaceMapper;
import com.data.display.model.bonus.BonusPartener;
import com.data.display.model.bonus.BonusPool;
import com.data.display.model.bonus.BonusPoolOutBill;
import com.data.display.model.bonus.BonusStockholder;
import com.data.display.model.user.UserAccountBill;
import com.data.display.model.user.UserInfoFinace;
import com.data.display.quartz.BonusUserJob;
import com.data.display.service.IJobAndTriggerService;
import com.data.display.service.bonusService.YMBonusPartenerService;
import com.data.display.service.bonusService.YMBonusPoolOutBillService;
import com.data.display.service.bonusService.YMBonusPoolService;
import com.data.display.service.bonusService.YMBonusStockholderService;
import com.data.display.util.DateUtil;
import com.data.display.util.StringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BonusGesellTest {

    private static Logger _log = LoggerFactory.getLogger(BonusGesellTest.class);

    @Autowired
    YMBonusPoolService bonusPoolService;

    @Autowired
    YMBonusPartenerService bonusPartenerService;

    @Autowired
    YMBonusStockholderService bonusStockholderService;

    @Autowired
    YMBonusPoolOutBillService bonusPoolOutBillService;

    @Autowired
    IJobAndTriggerService jobAndTriggerService;

    @Autowired
    UserInfoFinaceMapper userInfoFinaceMapper;

    @Resource
    UserAccountBillMapper userAccountBillMapper;

    @Test
    public void contextLoads() throws Exception {


        String time = DateUtil.dateToString(DateUtil.getAddMonth(-1),DateUtil.YYYY_MM_DD);
        String month = time.substring(0,7);
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






//        String time = DateUtil.dateToString(DateUtil.getAddDay(-18),DateUtil.YYYY_MM_DD);
//        _log.info("用户分红开始时间============>"+time);
//
//        List<BonusPoolOutBill> billList = bonusPoolOutBillService.getData("1",time);
//
//        for (int i = 0; i < billList.size(); i++) {
//            UserInfoFinace userInfoFinace = new UserInfoFinace();
//            userInfoFinace.setUser_id(billList.get(i).getUser_id());
//            userInfoFinace = userInfoFinaceMapper.selectByUserId(userInfoFinace);
//            if (userInfoFinace != null){
//                //真实分红金额
//                BigDecimal relAMount = billList.get(i).getAmount().subtract(billList.get(i).getDeduct_amount());
//
//                UserAccountBill userAccountBill = new UserAccountBill();
//                userAccountBill.setUser_id(userInfoFinace.getUser_id());
//                userAccountBill.setCreate_time(new Date());
//                userAccountBill.setRemark("编号为 "+userInfoFinace.getUser_id()+"的合伙人，每日日分红 "+relAMount+" 元");
//                userAccountBill.setChange_amt(relAMount);
//                userAccountBill.setType("+");
//                userAccountBill.setWallet(userInfoFinace.getCan_withdraw().add(relAMount));
//                userAccountBillMapper.addUserAccountBillData(userAccountBill);
//
//
//                userInfoFinace.setCan_withdraw(userInfoFinace.getCan_withdraw().add(relAMount));
//                userInfoFinaceMapper.updateUserInfoFinace(userInfoFinace);
//                billList.get(i).setUpdate_time(new Date());
//                billList.get(i).setSettle_time(new Date());
//                billList.get(i).setStatus(1);
//                billList.get(i).setRemark(time+"分红结算");
//                bonusPoolOutBillService.updateOutBill(billList.get(i));
//            }
//        }

//        jobAndTriggerService.deleteJob("bonusJob","bonusJob");
//        jobAndTriggerService.deleteJob("bonusGesellschafterJob","bonusGesellschafterJob");



    }

}

