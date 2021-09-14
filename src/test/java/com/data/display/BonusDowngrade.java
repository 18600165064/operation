package com.data.display;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.data.display.mapper.bonusMapper.BonusPartenerMapper;
import com.data.display.mapper.bonusMapper.BonusStockholderMapper;
import com.data.display.mapper.orderMapper.OrderMapper;
import com.data.display.mapper.richMapper.InformationMapper;
import com.data.display.mapper.userMapper.UserInfoMapper;
import com.data.display.model.bonus.BonusPartener;
import com.data.display.model.bonus.BonusPool;
import com.data.display.model.bonus.BonusPoolOutBill;
import com.data.display.model.bonus.BonusStockholder;
import com.data.display.model.rich.WxFormId;
import com.data.display.model.rich.WxNotice;
import com.data.display.service.PayService;
import com.data.display.service.bonusService.YMBonusPartenerService;
import com.data.display.service.bonusService.YMBonusPoolOutBillService;
import com.data.display.service.bonusService.YMBonusPoolService;
import com.data.display.service.bonusService.YMBonusStockholderService;
import com.data.display.util.DateUtil;
import com.data.display.util.RedisConstantUtil;
import com.data.display.util.RedisUtil;
import com.data.display.util.StringUtil;
import com.data.display.util.config.WxConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.http.Header;
import org.nutz.http.Http;
import org.nutz.http.Response;
import org.nutz.lang.util.NutMap;
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
public class BonusDowngrade {

    private static Logger _log = LoggerFactory.getLogger(BonusDowngrade.class);

    @Resource
    BonusPartenerMapper bonusPartenerMapper;

    @Resource
    BonusStockholderMapper bonusStockholderMapper;

    @Resource
    OrderMapper orderMapper;

    @Test
    public void contextLoads() throws Exception {
        try{
            /**
             * 合伙人、股东降级
             */
            try{
                String date = DateUtil.dateToString(DateUtil.getAddMonth(-2),DateUtil.YYYY_MM_DD);
                String date2 = DateUtil.dateToString(DateUtil.getAddMonth(-1),DateUtil.YYYY_MM_DD);
                _log.info("当前时间为===========>"+DateUtil.dateToString(new Date(),DateUtil.YYYY_MM_DD_HH_MM_SS));
                _log.info("合伙人、股东符合条件时间为"+date.substring(0,7));
                _log.info("合伙人、股东订单符合条件时间为"+date2.substring(0,7));

                BonusPartener bonusPartener = new BonusPartener();
                bonusPartener.setCreate_time(DateUtil.getAddMonth(-2));
                //合伙人
                List<BonusPartener> list = bonusPartenerMapper.getData3(bonusPartener);
                for (int i = 0; i < list.size(); i++) {
                    Integer parCount = orderMapper.getLastMonthData(date2.substring(0,7),String.valueOf(list.get(i).getUser_id()),null);
                    if (parCount < 30){
                        BigDecimal stoc = this.stockRatio(list.get(i).getStock_ratio());
                        list.get(i).setStock_ratio(stoc);
                        bonusPartenerMapper.updatePar(list.get(i));
                    }
                }


                //股东
                BonusStockholder bonusStockholder = new BonusStockholder();
                bonusStockholder.setCreate_time(DateUtil.getAddMonth(-2));
                List<BonusStockholder> stocList = bonusStockholderMapper.getData3(bonusStockholder);
                for (int i = 0; i < stocList.size(); i++) {
                    Integer stocCount = orderMapper.getLastMonthData(date2.substring(0,7),null,String.valueOf(stocList.get(i).getUser_id()));
                    if (stocCount < 300){
                        BigDecimal stoc = this.stockRatio(stocList.get(i).getStock_ratio());
                        stocList.get(i).setStock_ratio(stoc);
                        bonusStockholderMapper.updateStoc(stocList.get(i));
                    }
                }

            }catch (Exception e){
                _log.error("合伙人、股东降级错误"+ JSON.toJSON(e));
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }


    private BigDecimal stockRatio(BigDecimal stockRatio){
        BigDecimal newStoc = BigDecimal.ZERO;
        if (stockRatio.compareTo(new BigDecimal(1)) == 0){
            newStoc = new BigDecimal(0.9);
            return newStoc;
        }else if (stockRatio.compareTo(new BigDecimal(0.9)) == 0){
            newStoc = new BigDecimal(0.7);
            return newStoc;
        }else if (stockRatio.compareTo(new BigDecimal(0.7)) == 0){
            newStoc = new BigDecimal(0.5);
            return newStoc;
        }else{
            newStoc = new BigDecimal(0);
            return newStoc;
        }
    }


}

