package com.data.display.quartz;


import com.alibaba.fastjson.JSON;
import com.data.display.mapper.bonusMapper.BonusPartenerMapper;
import com.data.display.mapper.bonusMapper.BonusStockholderMapper;
import com.data.display.mapper.orderMapper.OrderMapper;
import com.data.display.mapper.userMapper.UserAccountBillMapper;
import com.data.display.mapper.userMapper.UserInfoFinaceMapper;
import com.data.display.model.bonus.BonusPartener;
import com.data.display.model.bonus.BonusPool;
import com.data.display.model.bonus.BonusPoolOutBill;
import com.data.display.model.bonus.BonusStockholder;
import com.data.display.model.user.UserAccountBill;
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
 * 初始化 用户可提现次数
 */


@DisallowConcurrentExecution
public class UserWithdrawTimesJob extends QuartzJobBean {

    private static Logger _log = LoggerFactory.getLogger(UserWithdrawTimesJob.class);

    @Resource
    private UserInfoFinaceMapper userInfoFinaceMapper;

    @Autowired
    YMBonusPoolOutBillService bonusPoolOutBillService;

    @Resource
    BonusPartenerMapper bonusPartenerMapper;

    @Resource
    BonusStockholderMapper bonusStockholderMapper;

    @Resource
    OrderMapper orderMapper;

    @Autowired
    YMBonusPoolService bonusPoolService;


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        /**
         * 更改用户提现次数
         */
        try{
            UserInfoFinace userInfoFinace = new UserInfoFinace();
            List<UserInfoFinace> list = userInfoFinaceMapper.getData(userInfoFinace);
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setCan_withdraw_times(4);
                userInfoFinaceMapper.updateUserInfoFinace(list.get(i));
            }
        }catch (Exception e){
            _log.error("更改用户提现次数错误"+ JSON.toJSON(e));
        }



        /**
         * 合伙人、股东降级
         */
//        try{
//            String date = DateUtil.dateToString(DateUtil.getAddMonth(-2),DateUtil.YYYY_MM_DD);
//            String date2 = DateUtil.dateToString(DateUtil.getAddMonth(-1),DateUtil.YYYY_MM_DD);
//            _log.info("当前时间为===========>"+DateUtil.dateToString(new Date(),DateUtil.YYYY_MM_DD_HH_MM_SS));
//            _log.info("合伙人、股东符合条件时间为"+date.substring(0,7));
//            _log.info("合伙人、股东订单符合条件时间为"+date2.substring(0,7));
//
//            BonusPartener bonusPartener = new BonusPartener();
//            bonusPartener.setCreate_time(DateUtil.getAddMonth(-2));
//            //合伙人
//            List<BonusPartener> list = bonusPartenerMapper.getData3(bonusPartener);
//            _log.info("合格的合伙人数量为"+list.size());
//            for (int i = 0; i < list.size(); i++) {
//               Integer parCount = orderMapper.getLastMonthData(date2.substring(0,7),String.valueOf(list.get(i).getUser_id()),null);
//               if (parCount < 30){
//                   BigDecimal stoc = this.stockRatio(list.get(i).getStock_ratio());
//                   list.get(i).setStock_ratio(stoc);
//                   _log.info("合伙人股权修改前为"+list.get(i).getStock_ratio());
//                   bonusPartenerMapper.updatePar(list.get(i));
//               }
//            }
//
//
//            //股东
//            BonusStockholder bonusStockholder = new BonusStockholder();
//            bonusStockholder.setCreate_time(DateUtil.getAddMonth(-2));
//            List<BonusStockholder> stocList = bonusStockholderMapper.getData3(bonusStockholder);
//            _log.info("合格的股东数量为"+stocList.size());
//            for (int i = 0; i < stocList.size(); i++) {
//                Integer stocCount = orderMapper.getLastMonthData(date2.substring(0,7),null,String.valueOf(stocList.get(i).getUser_id()));
//                if (stocCount < 300){
//                    BigDecimal stoc = this.stockRatio(stocList.get(i).getStock_ratio());
//                    stocList.get(i).setStock_ratio(stoc);
//                    _log.info("股东股权修改前为"+list.get(i).getStock_ratio());
//                    bonusStockholderMapper.updateStoc(stocList.get(i));
//                }
//            }
//
//        }catch (Exception e){
//            _log.error("合伙人、股东降级错误"+ JSON.toJSON(e));
//        }

    }


//    private BigDecimal stockRatio(BigDecimal stockRatio){
//        BigDecimal newStoc = BigDecimal.ZERO;
//        if (stockRatio.compareTo(new BigDecimal(1)) == 0){
//            newStoc = new BigDecimal(0.9);
//            return newStoc;
//        }else if (stockRatio.compareTo(new BigDecimal(0.9)) == 0){
//            newStoc = new BigDecimal(0.7);
//            return newStoc;
//        }else if (stockRatio.compareTo(new BigDecimal(0.7)) == 0){
//            newStoc = new BigDecimal(0.5);
//            return newStoc;
//        }else{
//            newStoc = new BigDecimal(0);
//            return newStoc;
//        }
//    }

}
