package com.data.display;

import com.data.display.mapper.bonusMapper.BonusStockholderMapper;
import com.data.display.model.bonus.BonusPool;
import com.data.display.model.bonus.BonusPoolOutBill;
import com.data.display.model.bonus.BonusStockholder;
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
public class BonusGesellschafterTest {

    private static Logger _log = LoggerFactory.getLogger(BonusGesellschafterTest.class);

    @Autowired
    YMBonusPoolService bonusPoolService;

    @Autowired
    YMBonusPartenerService bonusPartenerService;

    @Autowired
    YMBonusStockholderService bonusStockholderService;

    @Autowired
    YMBonusPoolOutBillService bonusPoolOutBillService;

    @Resource
    BonusStockholderMapper bonusStockholderMapper;

    @Test
    public void contextLoads() throws Exception {
        try{
            _log.info("股东分红开始时间============>"+DateUtil.dateToString(new Date(),DateUtil.YYYY_MM_DD));
            String agotime = DateUtil.dateToString(DateUtil.getAddDay(-2),DateUtil.YYYY_MM_DD);
            String yestime = DateUtil.dateToString(DateUtil.getAddDay(-1),DateUtil.YYYY_MM_DD);
            //总金额
            BigDecimal allVerbrauch = BigDecimal.ZERO;
            //批次号
            String num = StringUtil.dataRandom();

            BonusPool bonusPool = new BonusPool();
            List<BonusPool> poolSpuidList = bonusPoolService.getDataBySpuid(bonusPool);
            for (int i = 0; i < poolSpuidList.size(); i++) {
                bonusPool.setPool_type(2);
                bonusPool.setSpuid(poolSpuidList.get(i).getSpuid());
                bonusPool = bonusPoolService.getData(bonusPool);
                if (bonusPool != null){
                    if (bonusPool.getSpuid().equals("ZY-97431990")) {
                        //洗衣片合伙人分红
                        BigDecimal relAmount = this.addOutBill1(bonusPool, agotime, num, allVerbrauch);
                        _log.info("茅台酒股东分红剩余金额为："+relAmount);
                    }if (bonusPool.getSpuid().equals("ZY-24020206")) {
                        //茅台酒合伙人分红
                        BigDecimal relAmount = this.addOutBill2(bonusPool, yestime, num, allVerbrauch);
                        _log.info("洗衣片股东分红剩余金额为："+relAmount);
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }


    /**
     * 茅台酒合伙人分红
     */
    private BigDecimal addOutBill2(BonusPool bonusPool,String yestime,String num,BigDecimal allVerbrauch){
        List<BonusStockholder> list = bonusStockholderMapper.getDataBySpuid(bonusPool.getSpuid(),yestime);
        Integer partenetCount = 0;
        for (int i = 0; i < list.size(); i++) {
            partenetCount += list.get(i).getTotal_pro_sales();
        }
        BigDecimal allCount = new BigDecimal(partenetCount);
        _log.info("茅台酒股东全部销量=============="+allCount);

        //每份所值金额
        BigDecimal eveyOne = BigDecimal.ZERO;
        if (allCount.compareTo(BigDecimal.ZERO) != 0){
            eveyOne = bonusPool.getAmount().divide(allCount,4,BigDecimal.ROUND_FLOOR);
            _log.info("茅台酒每份所值金额=============="+eveyOne);
        }


        for (int j = 0; j < list.size(); j++){
            BigDecimal moreSales = new BigDecimal(list.get(j).getMore_sales());
            BonusPoolOutBill bonusPoolOutBill = new BonusPoolOutBill();
            bonusPoolOutBill.setSpuid(bonusPool.getSpuid());
            bonusPoolOutBill.setPool_type(2);
            bonusPoolOutBill.setDeduct_amount(BigDecimal.ZERO);
            bonusPoolOutBill.setUser_id(list.get(j).getUser_id());
            BigDecimal count = new BigDecimal(list.get(j).getTotal_pro_sales());
            BigDecimal amount = count.multiply(eveyOne);
            bonusPoolOutBill.setAmount(amount);
            bonusPoolOutBill.setPros_num(count);
            bonusPoolOutBill.setEven_bonus(eveyOne);
            bonusPoolOutBill.setBatch_no(num);
            bonusPoolOutBill.setStatus(0);
            bonusPoolOutBill.setCreate_time(new Date());
            bonusPoolOutBill.setMore_amount(moreSales.multiply(eveyOne));
            if (bonusPool.getAmount().compareTo(BigDecimal.ZERO) == 0){
                bonusPoolOutBill.setBonus_ratio(BigDecimal.ZERO);
            }else{
                BigDecimal ratio2 = amount.divide(bonusPool.getAmount(),2,BigDecimal.ROUND_FLOOR);
                bonusPoolOutBill.setBonus_ratio(ratio2);
            }

            allVerbrauch = allVerbrauch.add(amount);

            bonusPoolOutBillService.addPoolOutBill(bonusPoolOutBill);
        }
        BigDecimal relAmount = bonusPool.getAmount().subtract(allVerbrauch);
        bonusPool.setAmount(relAmount);
        _log.info("分红剩余金额=============="+relAmount);
        bonusPoolService.updateBonusPool(bonusPool);
        return relAmount;
    }


    /**
     * 洗衣片股东分红
     */
    private BigDecimal addOutBill1(BonusPool bonusPool,String agotime,String num,BigDecimal allVerbrauch){
        //股东总销量
        Integer stockholderCount = bonusStockholderService.getCountBySpuid(bonusPool.getSpuid(),agotime);
        if (stockholderCount == null){
            stockholderCount = 0;
        }
        //全部销量
        BigDecimal allCount = new BigDecimal(stockholderCount);

        if (allCount.compareTo(BigDecimal.ZERO) == 0){
            return bonusPool.getAmount().subtract(allVerbrauch);
        }

        //每份所值金额
        BigDecimal eveyOne = BigDecimal.ZERO;
        if (allCount.compareTo(BigDecimal.ZERO) != 0){
            eveyOne = bonusPool.getAmount().divide(allCount,4,BigDecimal.ROUND_FLOOR);
            _log.info("洗衣片每份所值金额=============="+eveyOne);
        }

        BonusStockholder bonusStockholder = new BonusStockholder();
        bonusStockholder.setSpuid(bonusPool.getSpuid());
        bonusStockholder.setCreate_time(DateUtil.getAddDay(-2));
        List<BonusStockholder> stockholdersList = bonusStockholderService.getData(bonusStockholder);

        for (int j = 0; j < stockholdersList.size(); j++){
            BigDecimal moreSales = new BigDecimal(stockholdersList.get(j).getMore_sales());
            BonusPoolOutBill bonusPoolOutBill = new BonusPoolOutBill();
            bonusPoolOutBill.setSpuid(bonusPool.getSpuid());
            bonusPoolOutBill.setPool_type(2);
            bonusPoolOutBill.setDeduct_amount(BigDecimal.ZERO);
            bonusPoolOutBill.setUser_id(stockholdersList.get(j).getUser_id());
            BigDecimal count = new BigDecimal(stockholdersList.get(j).getTotal_pro_sales());
            BigDecimal amount = count.multiply(eveyOne);
            bonusPoolOutBill.setAmount(amount);
            bonusPoolOutBill.setPros_num(count);
            bonusPoolOutBill.setEven_bonus(eveyOne);
            bonusPoolOutBill.setBatch_no(num);
            bonusPoolOutBill.setStatus(0);
            bonusPoolOutBill.setCreate_time(new Date());
            bonusPoolOutBill.setMore_amount(moreSales.multiply(eveyOne));
            if (bonusPool.getAmount().compareTo(BigDecimal.ZERO) == 0){
                bonusPoolOutBill.setBonus_ratio(BigDecimal.ZERO);
            }else{
                BigDecimal ratio2 = amount.divide(bonusPool.getAmount(),2,BigDecimal.ROUND_FLOOR);
                bonusPoolOutBill.setBonus_ratio(ratio2);
            }

            allVerbrauch = allVerbrauch.add(amount);

            bonusPoolOutBillService.addPoolOutBill(bonusPoolOutBill);
        }
        BigDecimal relAmount = bonusPool.getAmount().subtract(allVerbrauch);
        bonusPool.setAmount(relAmount);
        _log.info("分红剩余金额=============="+relAmount);
        bonusPoolService.updateBonusPool(bonusPool);
        return relAmount;
    }


}

