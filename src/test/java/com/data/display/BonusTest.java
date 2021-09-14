package com.data.display;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.data.display.mapper.bonusMapper.BonusPartenerMapper;
import com.data.display.mapper.richMapper.InformationMapper;
import com.data.display.mapper.userMapper.UserInfoMapper;
import com.data.display.model.bonus.BonusPartener;
import com.data.display.model.bonus.BonusPool;
import com.data.display.model.bonus.BonusPoolOutBill;
import com.data.display.model.rich.Information;
import com.data.display.model.rich.WxFormId;
import com.data.display.model.rich.WxNotice;
import com.data.display.model.user.UserInfo;
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
public class BonusTest {

    private static Logger _log = LoggerFactory.getLogger(BonusTest.class);

    @Autowired
    YMBonusPoolService bonusPoolService;

    @Autowired
    YMBonusPartenerService bonusPartenerService;

    @Autowired
    YMBonusStockholderService bonusStockholderService;

    @Autowired
    YMBonusPoolOutBillService bonusPoolOutBillService;

    @Resource
    BonusPartenerMapper bonusPartenerMapper;

    @Resource
    InformationMapper informationMapper;

    @Resource
    UserInfoMapper userInfoMapper;

    @Resource
    private RedisUtil redisUtil;

    @Autowired
    private PayService payService;

    @Test
    public void contextLoads() throws Exception {
        try{
            String yestime = DateUtil.dateToString(DateUtil.getAddDay(-1),DateUtil.YYYY_MM_DD);
            String agotime = DateUtil.dateToString(DateUtil.getAddDay(-2),DateUtil.YYYY_MM_DD);
            //总金额
            BigDecimal allVerbrauch = BigDecimal.ZERO;
            BonusPool bonusPool2 = new BonusPool();
            //批次号
            String num = StringUtil.dataRandom();
            //分红池
            BonusPool bonusPool = new BonusPool();
            List<BonusPool> poolSpuidList = bonusPoolService.getDataBySpuid(bonusPool);
            _log.info("根据spu区分的奖池数量为"+poolSpuidList.size());
            for (int i = 0; i < poolSpuidList.size(); i++) {
                bonusPool.setPool_type(1);
                bonusPool.setSpuid(poolSpuidList.get(i).getSpuid());
                bonusPool2 = bonusPoolService.getData(bonusPool);
                if (bonusPool2 != null){
                    if (bonusPool2.getSpuid().equals("ZY-97431990")){
                        //洗衣片分红机制
                        BigDecimal relAmount = this.addOutBill1(bonusPool2,allVerbrauch,agotime,num);
                        _log.info("洗衣片分红剩余金额为："+relAmount);
                    }if (bonusPool2.getSpuid().equals("ZY-24020206")){
                        //茅台酒分红机制
                        //根据酒的spu查询前一天有订单的合伙人  然后对这些人进行分红
                        BigDecimal relAmount = this.addOutBill2(bonusPool2,allVerbrauch,yestime,num);
                        _log.info("茅台酒分红剩余金额为："+relAmount);
                    }
                }

            }

        }catch (Exception e){
            System.out.println(e);
        }

    }


    /**
     * 茅台酒分红机制
     * @param bonusPool2
     * @param allVerbrauch
     * @param yestime
     * @param num
     * @return
     */
    private BigDecimal addOutBill2(BonusPool bonusPool2,BigDecimal allVerbrauch,String yestime,String num){
        List<BonusPartener> parList = bonusPartenerMapper.getDataBySpuid(bonusPool2.getSpuid(),yestime);
        _log.info("茅台酒合格的合伙人数量为"+parList.size());
        //符合要求合伙人总销量
        Integer partenetCount = 0;
        for (int i = 0; i < parList.size(); i++) {
            partenetCount += parList.get(i).getTotal_pro_sales();
        }
        BigDecimal allCount = new BigDecimal(partenetCount);
        _log.info("茅台酒全部销量=============="+allCount);

        //每份所值金额
        BigDecimal eveyOne = BigDecimal.ZERO;
        if (allCount.compareTo(BigDecimal.ZERO) != 0){
            eveyOne = bonusPool2.getAmount().divide(allCount,4,BigDecimal.ROUND_FLOOR);
            _log.info("茅台酒每份所值金额=============="+eveyOne);
        }

        for (int i = 0; i < parList.size(); i++) {
            BigDecimal moreSales = new BigDecimal(parList.get(i).getMore_sales());
            BonusPoolOutBill bonusPoolOutBill = new BonusPoolOutBill();
            bonusPoolOutBill.setSpuid(parList.get(i).getSpuid());
            bonusPoolOutBill.setPool_type(1);
            bonusPoolOutBill.setUser_id(parList.get(i).getUser_id());
            BigDecimal count = new BigDecimal(parList.get(i).getTotal_pro_sales());
            BigDecimal amount = count.multiply(eveyOne);
            bonusPoolOutBill.setAmount(amount);
            bonusPoolOutBill.setDeduct_amount(BigDecimal.ZERO);
            bonusPoolOutBill.setPros_num(count);
            bonusPoolOutBill.setEven_bonus(eveyOne);
            bonusPoolOutBill.setBatch_no(num);
            bonusPoolOutBill.setStatus(0);
            bonusPoolOutBill.setCreate_time(new Date());
            bonusPoolOutBill.setMore_amount(moreSales.multiply(eveyOne));
            if (bonusPool2.getAmount().compareTo(BigDecimal.ZERO) == 0){
                bonusPoolOutBill.setBonus_ratio(BigDecimal.ZERO);
            }else{
                BigDecimal ratio2 = amount.divide(bonusPool2.getAmount(),2,BigDecimal.ROUND_FLOOR);
                bonusPoolOutBill.setBonus_ratio(ratio2);
            }

            allVerbrauch = allVerbrauch.add(amount);

            bonusPoolOutBillService.addPoolOutBill(bonusPoolOutBill);

            Information information2 = new Information();
            information2.setCreate_time(new Date());
            information2.setJump_page(0);
            information2.setStatus(0);
            information2.setUser_id(bonusPoolOutBill.getUser_id());
            information2.setTitle("合伙人每日分红");
            information2.setSub_title("您今日的分红金额为："+bonusPoolOutBill.getAmount()+"元");
            information2.setMessage("您今日的分红金额为："+bonusPoolOutBill.getAmount()+"元");
            informationMapper.insertYmInformation(information2);
            try{
                String time = DateUtil.dateToString(bonusPoolOutBill.getCreate_time(),DateUtil.YYYY_MM_DD_HH_MM_SS);
                UserInfo userInfo = userInfoMapper.selectById(String.valueOf(bonusPoolOutBill.getUser_id()));
                WxNotice wxNotice2 = new WxNotice();
                wxNotice2.setOpenid(userInfo.getOpen_id_small());
                wxNotice2.setTemplate_id(WxConfig.BONUS_JOB_TEMPLATE_ID);
                wxNotice2.addKeyWords(bonusPoolOutBill.getAmount()+"元","#173177");
                wxNotice2.addKeyWords(time,"#173177");
                wxNotice2.addKeyWords("合伙人每日分红","#173177");
                this.sendMessageWxSmallV2(wxNotice2,String.valueOf(bonusPoolOutBill.getUser_id()));
            }catch (Exception e) {
                _log.error("合伙人每日分红消息发送失败", e);
            }
        }

        BigDecimal relAmount = bonusPool2.getAmount().subtract(allVerbrauch);
        _log.info("分红剩余金额=============="+relAmount);
        bonusPool2.setAmount(relAmount);
        bonusPoolService.updateBonusPool(bonusPool2);
        _log.info("=============每日分红结束================");

        return relAmount;
    }


    /**
     * 洗衣片分红机制
     * @param bonusPool2
     * @param allVerbrauch
     * @param agotime
     * @param num
     */
    private BigDecimal addOutBill1(BonusPool bonusPool2,BigDecimal allVerbrauch,String agotime,String num){
        //合伙人总销量
        Integer partenetCount = bonusPartenerService.getCountBySpuid(bonusPool2.getSpuid(),agotime);
        if (partenetCount == null){
            partenetCount = 0;
        }
        //全部销量
        BigDecimal allCount = new BigDecimal(partenetCount);
        _log.info("全部销量=============="+allCount);

        //每份所值金额
        BigDecimal eveyOne = bonusPool2.getAmount().divide(allCount,4,BigDecimal.ROUND_FLOOR);
        _log.info("每份所值金额=============="+eveyOne);


        //合伙人
        BonusPartener bonusPartener = new BonusPartener();
        bonusPartener.setSpuid(bonusPool2.getSpuid());
        bonusPartener.setCreate_time(DateUtil.getAddDay(-2));
        List<BonusPartener> partenersList =  bonusPartenerService.getData(bonusPartener);
        _log.info("合格的合伙人数量为"+partenersList.size());
        for (int j = 0; j < partenersList.size(); j++){
            BigDecimal moreSales = new BigDecimal(partenersList.get(j).getMore_sales());
            BonusPoolOutBill bonusPoolOutBill = new BonusPoolOutBill();
            bonusPoolOutBill.setSpuid(partenersList.get(j).getSpuid());
            bonusPoolOutBill.setPool_type(1);
            bonusPoolOutBill.setUser_id(partenersList.get(j).getUser_id());
            BigDecimal count = new BigDecimal(partenersList.get(j).getTotal_pro_sales());
            BigDecimal amount = count.multiply(eveyOne);
            bonusPoolOutBill.setAmount(amount);
            bonusPoolOutBill.setDeduct_amount(BigDecimal.ZERO);
            bonusPoolOutBill.setPros_num(count);
            bonusPoolOutBill.setEven_bonus(eveyOne);
            bonusPoolOutBill.setBatch_no(num);
            bonusPoolOutBill.setStatus(0);
            bonusPoolOutBill.setCreate_time(new Date());
            bonusPoolOutBill.setMore_amount(moreSales.multiply(eveyOne));
            if (bonusPool2.getAmount().compareTo(BigDecimal.ZERO) == 0){
                bonusPoolOutBill.setBonus_ratio(BigDecimal.ZERO);
            }else{
                BigDecimal ratio2 = amount.divide(bonusPool2.getAmount(),2,BigDecimal.ROUND_FLOOR);
                bonusPoolOutBill.setBonus_ratio(ratio2);
            }

            allVerbrauch = allVerbrauch.add(amount);

            bonusPoolOutBillService.addPoolOutBill(bonusPoolOutBill);

            Information information2 = new Information();
            information2.setCreate_time(new Date());
            information2.setJump_page(0);
            information2.setStatus(0);
            information2.setUser_id(bonusPoolOutBill.getUser_id());
            information2.setTitle("合伙人每日分红");
            information2.setSub_title("您今日的分红金额为："+bonusPoolOutBill.getAmount()+"元");
            information2.setMessage("您今日的分红金额为："+bonusPoolOutBill.getAmount()+"元");
            informationMapper.insertYmInformation(information2);
            try{
                String time = DateUtil.dateToString(bonusPoolOutBill.getCreate_time(),DateUtil.YYYY_MM_DD_HH_MM_SS);
                UserInfo userInfo = userInfoMapper.selectById(String.valueOf(bonusPoolOutBill.getUser_id()));
                WxNotice wxNotice2 = new WxNotice();
                wxNotice2.setOpenid(userInfo.getOpen_id_small());
                wxNotice2.setTemplate_id(WxConfig.BONUS_JOB_TEMPLATE_ID);
                wxNotice2.addKeyWords(bonusPoolOutBill.getAmount()+"元","#173177");
                wxNotice2.addKeyWords(time,"#173177");
                wxNotice2.addKeyWords("合伙人每日分红","#173177");
                this.sendMessageWxSmallV2(wxNotice2,String.valueOf(bonusPoolOutBill.getUser_id()));
            }catch (Exception e) {
                _log.error("合伙人每日分红消息发送失败", e);
            }

        }

        BigDecimal relAmount = bonusPool2.getAmount().subtract(allVerbrauch);
        _log.info("分红剩余金额=============="+relAmount);
        bonusPool2.setAmount(relAmount);
        bonusPoolService.updateBonusPool(bonusPool2);
        _log.info("=============每日分红结束================");

        return relAmount;
    }



    /**
     * 发送微信服务消息
     * @param wxNotice
     * @param userId
     */
    public void sendMessageWxSmallV2(WxNotice wxNotice,String userId) {

        String key = userId+":formIds";
        if (!redisUtil.hasKey(key)) {
            throw new RuntimeException("无 formId");
        }

        final Object o = redisUtil.leftPop(key);
        if (o == null) {
            throw new RuntimeException("无 formId");
        }
        WxFormId wxFormId = JSON.parseObject(o.toString(), WxFormId.class);
        while (wxFormId.getExpierTime() < System.currentTimeMillis()/1000){
            final Object o1 = redisUtil.leftPop(key);
            if (o1 == null) {
                throw new RuntimeException("无 formId");
            }
            wxFormId = JSON.parseObject(o1.toString(), WxFormId.class);
        }

        String token = "";
        if (redisUtil.hasKey(RedisConstantUtil.ACCESS_TOKEN)) {
            token = redisUtil.get(RedisConstantUtil.ACCESS_TOKEN).toString();
        } else {
            token = payService.getAccessToken().getData().toString();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("touser", wxNotice.getOpenid());
        jsonObject.put("template_id", wxNotice.getTemplate_id());
        jsonObject.put("form_id", wxFormId.getFormId());
        jsonObject.put("data", wxNotice.getData());
        _log.debug("sendMessageWxSmall:" + jsonObject.toString());
        NutMap reHeader = new NutMap();
        reHeader.put("Content-Type", "application/json");
        Header header = Header.create(reHeader);
        Response resp = Http.post3(WxConfig.WX_TEMPLATE + token, jsonObject.toJavaObject((Object.class)), header, 10000);
        _log.debug(resp.getContent());
    }


}

