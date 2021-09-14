package com.data.display.quartz;

import com.data.display.mapper.userMapper.UserAccountBillMapper;
import com.data.display.mapper.userMapper.UserInfoFinaceMapper;
import com.data.display.model.bonus.BonusPoolOutBill;
import com.data.display.model.user.UserAccountBill;
import com.data.display.model.user.UserInfoFinace;
import com.data.display.service.bonusService.YMBonusPoolOutBillService;
import com.data.display.util.DateUtil;
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
 * 用户分红 转到可提现钱包
 */
public class BonusUserJob extends QuartzJobBean {

    private static Logger _log = LoggerFactory.getLogger(BonusUserJob.class);

    @Autowired
    YMBonusPoolOutBillService bonusPoolOutBillService;

    @Autowired
    UserInfoFinaceMapper userInfoFinaceMapper;

    @Resource
    UserAccountBillMapper userAccountBillMapper;


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        String time = DateUtil.dateToString(DateUtil.getAddDay(-18),DateUtil.YYYY_MM_DD);
        _log.info("用户分红开始时间============>"+time);

        List<BonusPoolOutBill> billList = bonusPoolOutBillService.getData("1",time);

        for (int i = 0; i < billList.size(); i++) {
            UserInfoFinace userInfoFinace = new UserInfoFinace();
            userInfoFinace.setUser_id(billList.get(i).getUser_id());
            userInfoFinace = userInfoFinaceMapper.selectByUserId(userInfoFinace);
            if (userInfoFinace != null){
                //真实分红金额
                BigDecimal relAMount = billList.get(i).getAmount().subtract(billList.get(i).getDeduct_amount());

                UserAccountBill userAccountBill = new UserAccountBill();
                userAccountBill.setUser_id(userInfoFinace.getUser_id());
                userAccountBill.setCreate_time(new Date());
                userAccountBill.setRemark("编号为 "+userInfoFinace.getUser_id()+"的合伙人，每日日分红 "+relAMount+" 元");
                userAccountBill.setChange_amt(relAMount);
                userAccountBill.setType("+");
                userAccountBill.setWallet(userInfoFinace.getCan_withdraw().add(relAMount));
                userAccountBillMapper.addUserAccountBillData(userAccountBill);


                userInfoFinace.setCan_withdraw(userInfoFinace.getCan_withdraw().add(relAMount));
                userInfoFinaceMapper.updateUserInfoFinace(userInfoFinace);
                billList.get(i).setUpdate_time(new Date());
                billList.get(i).setSettle_time(new Date());
                billList.get(i).setStatus(1);
                billList.get(i).setRemark(time+"分红结算");
                bonusPoolOutBillService.updateOutBill(billList.get(i));
            }
        }

    }
}
