package com.data.display.service.platformService.impl;

import com.alibaba.fastjson.JSONObject;
import com.data.display.mapper.bonusMapper.BonusPartenerMapper;
import com.data.display.mapper.bonusMapper.BonusStockholderMapper;
import com.data.display.mapper.orderMapper.OrderMapper;
import com.data.display.mapper.orderMapper.YMGrouperMapper;
import com.data.display.mapper.platformMapper.PlatformMapper;
import com.data.display.mapper.platformMapper.YMAscriptionMapper;
import com.data.display.model.bonus.BonusPartener;
import com.data.display.model.bonus.BonusPool;
import com.data.display.model.bonus.BonusPoolOutBill;
import com.data.display.model.bonus.BonusStockholder;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.dto.DateDTO;
import com.data.display.model.order.Order;
import com.data.display.model.order.YMGrouper;
import com.data.display.model.platform.BonusParents;
import com.data.display.model.platform.YMAscription;
import com.data.display.model.user.UserInfo;
import com.data.display.service.PayService;
import com.data.display.service.bonusService.YMBonusPartenerService;
import com.data.display.service.bonusService.YMBonusPoolOutBillService;
import com.data.display.service.bonusService.YMBonusPoolService;
import com.data.display.service.bonusService.YMBonusStockholderService;
import com.data.display.service.platformService.PlatformService;
import com.data.display.service.platformService.WxanalysisService;
import com.data.display.service.userService.UserInfoService;
import com.data.display.util.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

@Service
public class PlatformServiceImpl implements PlatformService {

    @Resource
    PlatformMapper platformMapper;

    @Autowired
    WxanalysisService wxanalysisService;

    @Resource
    RedisUtil redisUtil;

    @Resource
    YMAscriptionMapper ascriptionMapper;

    @Resource
    OrderMapper orderMapper;

    @Autowired
    YMBonusPoolService bonusPoolService;

    @Autowired
    YMBonusPartenerService bonusPartenerService;

    @Autowired
    YMBonusStockholderService bonusStockholderService;

    @Autowired
    YMBonusPoolOutBillService bonusPoolOutBillService;

    @Autowired
    private PayService payService;

    @Autowired
    UserInfoService userInfoService;

    @Resource
    private YMGrouperMapper grouperMapper;

    @Resource
    BonusPartenerMapper bonusPartenerMapper;

    @Resource
    BonusStockholderMapper bonusStockholderMapper;


    @Override
    public Map<String, Object> getPlatformData(String strDate) {
        BigDecimal zero = BigDecimal.ZERO;
        BigDecimal ten = new BigDecimal(100);
        Date endTime = null;
        try{
            endTime = DateUtil.stringToDate(strDate,DateUtil.YYYY_MM_DD);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String yesterday = DateUtil.dateToString(DateUtil.getEndTimeByTime(endTime,-1),DateUtil.YYYY_MM_DD);
        String beforeday = DateUtil.dateToString(DateUtil.getEndTimeByTime(endTime,-2),DateUtil.YYYY_MM_DD);
        Map<String, Object> map = platformMapper.getPlatformData(strDate,yesterday,beforeday);
        String newDa = DateUtil.dateToString(new Date(),DateUtil.YYYY_MM_DD);
        //前一天小程序打开次数
        String  result = wxanalysisService.getDailyVisitTrend(this.getToken(), new DateDTO(strDate, strDate));
        JSONObject jo = JSONObject.parseObject(result);
        if (StringUtil.isErrcode(jo)) {
            Object wxSmall = jo.getJSONArray("list").getJSONObject(0).getInteger("visit_uv");
            map.put("num20",wxSmall);
        }else{
            map.put("num20",0);
        }

        //累计成功开团数
        BigDecimal num5 = new BigDecimal(String.valueOf(map.get("num5")));
        //累计总开团数
        BigDecimal num4 = new BigDecimal(String.valueOf(map.get("num4")));
        //开团成功率
        if (num4.compareTo(zero) != 0){
            BigDecimal num17 = num5.divide(num4,2,BigDecimal.ROUND_FLOOR);
            map.put("num17",num17.multiply(ten).intValue()+"%");
        }else{
            map.put("num17",0);
        }
        //当日新增3人团数
        BigDecimal num6 = new BigDecimal(String.valueOf(map.get("num6")));
        //当日新增5人团数
        BigDecimal num7 = new BigDecimal(String.valueOf(map.get("num7")));
        //当日新增8人团数
        BigDecimal num8 = new BigDecimal(String.valueOf(map.get("num8")));
        //当日新增总开团数  num6+num7+num8
        BigDecimal num18 = num6.add(num7).add(num8);
        map.put("num18",num18);

        //成功开团人数
        BigDecimal num9 = new BigDecimal(String.valueOf(map.get("num9")));
        //累计开团人数
        BigDecimal num10 = new BigDecimal(String.valueOf(map.get("num10")));
        //成功开团人数占比  num10/num9
        if (num9.compareTo(zero) != 0) {
            BigDecimal num19 = num10.divide(num9, 2, BigDecimal.ROUND_FLOOR);
            map.put("num19", num19.multiply(ten).intValue()+"%");
        }else{
            map.put("num19",0);
        }

        //当日开团率  当日总开团数/当日支付人数
        //当日支付人数
        BigDecimal num15 = new BigDecimal(String.valueOf(map.get("num15")));
        if (num15.compareTo(zero) != 0){
            BigDecimal num21 = num18.divide(num15,2,BigDecimal.ROUND_FLOOR);
            map.put("num21",num21.multiply(ten).intValue()+"%");
        }else{
            map.put("num21",0);
        }

        //当日下单转化  当日新增订单数/小程序人数
        //当日支付转化
        BigDecimal num20 = new BigDecimal(String.valueOf(map.get("num20")));
        BigDecimal num13 = new BigDecimal(String.valueOf(map.get("num13")));
        if (num20.compareTo(zero) != 0){
            BigDecimal num22 = num13.divide(num20,2,BigDecimal.ROUND_FLOOR);
            map.put("num22",num22.multiply(ten).intValue()+"%");
            BigDecimal num24 = num15.divide(num20,2,BigDecimal.ROUND_FLOOR);
            map.put("num24",num24.multiply(ten).intValue()+"%");
        }else{
            map.put("num22",0);
            map.put("num24",0);
        }

        //当日下单增长比
        BigDecimal num50 = new BigDecimal(String.valueOf(map.get("num50")));
        BigDecimal difference = num13.subtract(num50);
        if(num50.compareTo(zero) != 0){
            BigDecimal num23 = difference.divide(num50,2,BigDecimal.ROUND_FLOOR);
            map.put("num23",num23.multiply(ten).intValue()+"%");
        }else{
            map.put("num23",0);
        }

        //获取当日已支付订单
        List<Order>  orderList = orderMapper.selectPaymented(strDate,"1");
        //获取所有已支付订单
        List<Order>  orderAllList = orderMapper.selectPaymented(strDate,"2");

        //######查询归属关系
        String groupids = PlatFormUtil.GROUPIDS;
        if (StringUtil.isNotBlank(groupids)){
            String ids[] = groupids.split(",");
            for (int i = 0; i < ids.length; i++) {
                Integer x = 0;
                Integer count = 0;
                Integer countAll = 0;
                ArrayList<String> strArray = new ArrayList<String> ();
                strArray.add(0,ids[i]);
                List<YMAscription> asList2 = new ArrayList<>();
                while (x == 0){
                    List<YMAscription> asList = ascriptionMapper.selectByUserId(strArray,"ZY-97431990");
                    ArrayList<String> strArray2 = new ArrayList<String> ();
                    if (asList.size() == 0){
                        x = 1;
                    }else{
                        for (int j = 0; j < asList.size(); j++) {
                            strArray2.add(j,String.valueOf(asList.get(j).getUser_id()));
                        }
                        strArray = strArray2;
                        asList2.addAll(asList);
                    }
                }

                for (int k = 0; k < orderList.size(); k++) {
                    for (int j = 0; j < asList2.size(); j++) {
                        if (orderList.get(k).getUser_id().equals(asList2.get(j).getUser_id())){
                            count ++;
                        }
                    }
                }


                for (int j = 0; j < orderAllList.size(); j++) {
                    for (int k = 0; k < asList2.size(); k++) {
                        if (orderAllList.get(j).getUser_id().equals(asList2.get(k).getUser_id())){
                            countAll ++;
                        }
                    }
                }

                map.put("teamAll"+ids[i],countAll);
                map.put("team"+ids[i],count);

            }
        }

        //每日合伙人分红池新增金额
        BigDecimal bonus1 = new BigDecimal(String.valueOf(map.get("bonus1")));
        //合伙人总数
        BigDecimal bonus6 = new BigDecimal(String.valueOf(map.get("bonus8")));
        //每日超级合伙人分红池新增金额
        BigDecimal bonus2 = new BigDecimal(String.valueOf(map.get("bonus2")));
        //超级合伙人总数
        BigDecimal bonus7 = new BigDecimal(String.valueOf(map.get("bonus9")));

        //合伙人平均分红
        if (bonus6.compareTo(zero) != 0){
            BigDecimal bonus8 = bonus1.divide(bonus6,2,BigDecimal.ROUND_FLOOR);
            map.put("bonus8",bonus8);
        }else{
            map.put("bonus8",0);
        }

        //超级合伙人平均分红
        if (bonus7.compareTo(zero) != 0){
            BigDecimal bonus9 = bonus2.divide(bonus7,2,BigDecimal.ROUND_FLOOR);
            map.put("bonus9",bonus9);
        }else{
            map.put("bonus9",0);
        }



        return map;
    }



    @Override
    public String getToken() {
//        String token = "24_51O0KwgcfzeYCwW8jcCjO9lJpNAeRl7TbKPiWcXDXeC73zHmnHgdj18he6M_Aeyv8lQKWFplMlNaGEnLxJJVs3QOLeW_O0nWPJ_CilK1-1CenBrEu2IoKNep93ccVjJTBHPpyg7ULTDBAXdXTBLbAJAKSC";
        String token = "";
        if (redisUtil.hasKey(RedisConstantUtil.ACCESS_TOKEN)) {
            token = redisUtil.get(RedisConstantUtil.ACCESS_TOKEN).toString();
        } else {
            token = payService.getAccessToken().getData().toString();
        }
        return token;
    }

    /**
     * 累计会员人数统计
     * @param
     * @return
     */
    @Override
    public Map<String, Object> getPersonCountData(String beginDate,String endDate){
        BigDecimal zero = BigDecimal.ZERO;
        BigDecimal ten = new BigDecimal(100);
        Map<String, Object> map = platformMapper.getPersonCountData(beginDate,endDate);
        //前一天小程序打开次数
        String  result = wxanalysisService.getDailyVisitTrend(this.getToken(), new DateDTO(endDate, endDate));
        JSONObject jo = JSONObject.parseObject(result);
        if (StringUtil.isErrcode(jo)) {
            Object wxSmall = jo.getJSONArray("list").getJSONObject(0).getInteger("visit_uv");
            map.put("num20",wxSmall);
        }else{
            map.put("num20",0);
        }

        //累计成功开团数
        BigDecimal num5 = new BigDecimal(String.valueOf(map.get("num5")));
        //累计总开团数
        BigDecimal num4 = new BigDecimal(String.valueOf(map.get("num4")));
        //开团成功率
        if (num4.compareTo(zero) != 0){
            BigDecimal num17 = num5.divide(num4,2,BigDecimal.ROUND_FLOOR);
            map.put("num17",num17.multiply(ten).intValue()+"%");
        }else{
            map.put("num17",0);
        }
        //当日新增3人团数
        BigDecimal num6 = new BigDecimal(String.valueOf(map.get("num6")));
        //当日新增5人团数
        BigDecimal num7 = new BigDecimal(String.valueOf(map.get("num7")));
        //当日新增8人团数
        BigDecimal num8 = new BigDecimal(String.valueOf(map.get("num8")));
        //当日新增总开团数  num6+num7+num8
        BigDecimal num18 = num6.add(num7).add(num8);
        map.put("num18",num18);

        //成功开团人数
        BigDecimal num9 = new BigDecimal(String.valueOf(map.get("num9")));
        //累计开团人数
        BigDecimal num10 = new BigDecimal(String.valueOf(map.get("num10")));
        //成功开团人数占比  num10/num9
        if (num9.compareTo(zero) != 0) {
            BigDecimal num19 = num10.divide(num9, 2, BigDecimal.ROUND_FLOOR);
            map.put("num19", num19.multiply(ten).intValue()+"%");
        }else{
            map.put("num19",0);
        }

        //当日开团率  当日总开团数/当日支付人数
        //当日支付人数
        BigDecimal num15 = new BigDecimal(String.valueOf(map.get("num15")));
        if (num15.compareTo(zero) != 0){
            BigDecimal num21 = num18.divide(num15,2,BigDecimal.ROUND_FLOOR);
            map.put("num21",num21.multiply(ten).intValue()+"%");
        }else{
            map.put("num21",0);
        }

        return map;
    }

    /**
     * 订单数统计
     * @param
     * @return
     */
    @Override
    public Map<String, Object> getOrderCountData(String beginDate,String endDate,String spuid) {
        BigDecimal zero = BigDecimal.ZERO;
        BigDecimal ten = new BigDecimal(100);
        Date endTime = null;
        try{
            endTime = DateUtil.stringToDate(beginDate,DateUtil.YYYY_MM_DD);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String yesterday = DateUtil.dateToString(DateUtil.getEndTimeByTime(endTime,-1),DateUtil.YYYY_MM_DD);
        Map<String, Object> map = platformMapper.getOrderCountData(beginDate,endDate,yesterday,spuid);
        String newDa = DateUtil.dateToString(new Date(),DateUtil.YYYY_MM_DD);
        //前一天小程序打开次数
        String  result = wxanalysisService.getDailyVisitTrend(this.getToken(), new DateDTO(endDate, endDate));
        JSONObject jo = JSONObject.parseObject(result);
        if (StringUtil.isErrcode(jo)) {
            Object wxSmall = jo.getJSONArray("list").getJSONObject(0).getInteger("visit_uv");
            map.put("num20",wxSmall);
        }else{
            map.put("num20",0);
        }



        //当日开团率  当日总开团数/当日支付人数
        //当日支付人数
        BigDecimal num15 = new BigDecimal(String.valueOf(map.get("num15")));

        //当日下单转化  当日新增订单数/小程序人数
        //当日支付转化
        BigDecimal num20 = new BigDecimal(String.valueOf(map.get("num20")));
        BigDecimal num13 = new BigDecimal(String.valueOf(map.get("num13")));
        if (num20.compareTo(zero) != 0){
            BigDecimal num22 = num13.divide(num20,2,BigDecimal.ROUND_FLOOR);
            map.put("num22",num22.multiply(ten).intValue()+"%");
            BigDecimal num24 = num15.divide(num20,2,BigDecimal.ROUND_FLOOR);
            map.put("num24",num24.multiply(ten).intValue()+"%");
        }else{
            map.put("num22",0);
            map.put("num24",0);
        }

        //当日下单增长比
        BigDecimal num50 = new BigDecimal(String.valueOf(map.get("num50")));
        BigDecimal difference = num13.subtract(num50);
        if(num50.compareTo(zero) != 0){
            BigDecimal num23 = difference.divide(num50,2,BigDecimal.ROUND_FLOOR);
            map.put("num23",num23.multiply(ten).intValue()+"%");
        }else{
            map.put("num23",0);
        }

        return map;
    }

    /**
     * 分红池统计
     * @param
     * @return
     */
    @Override
    public Map<String, Object> getBonusCountData(String beginDate,String endDate,String spuid) {
        BigDecimal zero = BigDecimal.ZERO;
        BigDecimal ten = new BigDecimal(100);
        Date endTime = null;
        try{
            endTime = DateUtil.stringToDate(beginDate,DateUtil.YYYY_MM_DD);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String yesterday = DateUtil.dateToString(DateUtil.getEndTimeByTime(endTime,-2),DateUtil.YYYY_MM_DD);
        Map<String, Object> map = platformMapper.getBonusCountData(beginDate,endDate,yesterday,spuid);

        //每日合伙人分红池新增金额
        BigDecimal bonus1 = new BigDecimal(String.valueOf(map.get("bonus1")));
        //合伙人总数
        BigDecimal bonus6 = new BigDecimal(String.valueOf(map.get("bonus8")));
        //每日超级合伙人分红池新增金额
        BigDecimal bonus2 = new BigDecimal(String.valueOf(map.get("bonus2")));
        //超级合伙人总数
        BigDecimal bonus7 = new BigDecimal(String.valueOf(map.get("bonus9")));

        //合伙人平均分红
        if (bonus6.compareTo(zero) != 0){
            BigDecimal bonus8 = bonus1.divide(bonus6,2,BigDecimal.ROUND_FLOOR);
            map.put("bonus8",bonus8);
        }else{
            map.put("bonus8",0);
        }

        //超级合伙人平均分红
        if (bonus7.compareTo(zero) != 0){
            BigDecimal bonus9 = bonus2.divide(bonus7,2,BigDecimal.ROUND_FLOOR);
            map.put("bonus9",bonus9);
        }else{
            map.put("bonus9",0);
        }


        return map;
    }


    /**
     * 合伙人业绩统计
     * @param
     * @return
     */
//    @Override
//    public List<Map<String,Object>> getBonusParentsData2(String strDate, String parentsId) {
//        List<Map<String,Object>> listMap = new ArrayList<>();
//        BigDecimal ten = new BigDecimal(100);
//        try{
//            BonusPool bonusPool = new BonusPool();
//
//            List<BonusPool> poolSpuidList = bonusPoolService.getDataBySpuid(bonusPool);
//            for (int i = 0; i < poolSpuidList.size(); i++) {
//                BonusPartener bonusPartener = new BonusPartener();
//                bonusPartener.setSpuid(poolSpuidList.get(i).getSpuid());
//                if (StringUtil.isNotBlank(parentsId)){
//                    bonusPartener.setUser_id(Integer.parseInt(parentsId));
//                }
//                bonusPartener.setCreate_time(DateUtil.stringToDate(strDate,DateUtil.YYYY_MM_DD));
//                List<BonusPartener> partenersList =  bonusPartenerService.getData(bonusPartener);
//                for (int j = 0; j < partenersList.size(); j++) {
//                    String parId = String.valueOf(partenersList.get(j).getUser_id());
//
//                    UserInfo userInfo = userInfoService.selectById(parId);
//
//                    //获取当日退款数量
//                    List<Order> refundList1 = orderMapper.getRefundData(strDate,parId);
//                    //获取所有退款数量
//                    List<Order> refundList2 = orderMapper.getRefundData(null,parId);
//                    //获取当日订单数量
//                    List<Order> orderList1 = orderMapper.getCountData(strDate,parId);
//                    //获取所有订单数量
//                    List<Order> orderList2 = orderMapper.getCountData(null,parId);
//                    //合伙人当日分红
//                    List<BonusPoolOutBill> outBillList = bonusPoolOutBillService.getData3(parId,strDate);
//
//
//                    Map<String,Object> map = new HashMap<>();
//
//                    //当日新增一级人数
//                    Integer today = 0;
//                    //当日二级人数
//                    Integer twoToday = 0;
//
//
//                    ArrayList<String> strArray = new ArrayList<String> ();
//                    strArray.add(0,String.valueOf(partenersList.get(j).getUser_id()));
//                    List<YMAscription> asList2 = new ArrayList<>();
//                    //一级总人数
//                    List<YMAscription> oneasList = ascriptionMapper.selectByUserId(strArray);
//
//                    ArrayList<String> strArray2 = new ArrayList<String> ();
//                    for (int h = 0; h < oneasList.size(); h++) {
//                        strArray2.add(h,String.valueOf(oneasList.get(h).getUser_id()));
//                        if (DateUtil.dateToString(oneasList.get(h).getCreate_time(),DateUtil.YYYY_MM_DD).equals(strDate)){
//                            today ++;
//                        }
//                    }
//                    //二级总人数
//                    List<YMAscription> twoasList = ascriptionMapper.selectByUserId(strArray2);
//                    for (int k = 0; k < twoasList.size(); k++) {
//                        if (DateUtil.dateToString(twoasList.get(k).getCreate_time(),DateUtil.YYYY_MM_DD).equals(strDate)){
//                            twoToday ++;
//                        }
//                    }
//
//                    //合伙人分红
//                    if (outBillList.size() == 0){
//                        map.put("bonusOut",0);
//                    }else{
//                        map.put("bonusOut",outBillList.get(0).getAmount());
//                    }
//
//                    //合伙人名称
//                    if (userInfo != null){
//                        map.put("userName",userInfo.getNick_name());
//                    }
//                    //合伙人ID
//                    map.put("userId",partenersList.get(j).getUser_id());
//                    //一级累计总人数
//                    map.put("todayAll",oneasList.size());
//                    //二级累计总人数
//                    map.put("twoTodayAll",twoasList.size());
//                    //今日新增一级人数
//                    map.put("today",today);
//                    //今日新增二级人数
//                    map.put("twoToday",twoToday);
//                    //当日退货数量
//                    map.put("refund",refundList1.size());
//                    //当日订单数量
//                    map.put("orderToday",orderList1.size());
//                    //所有订单数量
//                    map.put("orderTodayAll",orderList2.size());
//
//
//
//
//                    BigDecimal a = new BigDecimal(refundList2.size());
//                    BigDecimal b = new BigDecimal(orderList2.size());
//                    if (b.compareTo(BigDecimal.ZERO) != 0){
//                        BigDecimal c = a.divide(b,2,BigDecimal.ROUND_FLOOR);
//                        map.put("refundProportion",c.multiply(ten).intValue()+"%");
//                    }else{
//                        map.put("refundProportion","0%");
//                    }
//
//                    listMap.add(map);
//                }
//            }
//            System.out.println(JSON.toJSON(listMap));
//
//        }catch (Exception e){
//            System.out.println(e);
//        }
//        return listMap;
//    }

    @Override
    public Page<BonusParents> getBonusParentsData(DataTableDTO dataTableDTO, String beginDate, String endDate,String parentsId,String spuid) {
        PageHelper.startPage(dataTableDTO.getPageNum(), dataTableDTO.getLength());
        //查询合伙人
        Page<BonusParents> list = platformMapper.getBonusParentsData(null,null,parentsId,spuid);
        //超级合伙人
        BonusStockholder bonusStockholder = new BonusStockholder();
        bonusStockholder.setSpuid(spuid);
        bonusStockholder.setCreate_time(new Date());
        List<BonusStockholder> stockholdersList = bonusStockholderService.getData(bonusStockholder);

        if (StringUtil.isBlank(beginDate) && StringUtil.isBlank(endDate)){
            beginDate = DateUtil.dateToString(DateUtil.getStartTime(),DateUtil.YYYY_MM_DD_HH_MM_SS);
            endDate = DateUtil.dateToString(DateUtil.getEndTime(),DateUtil.YYYY_MM_DD_HH_MM_SS);
        }
        Date begin = null;
        Date end = null;
        try{
            begin = DateUtil.stringToDate(beginDate,DateUtil.YYYY_MM_DD_HH_MM_SS);
            end = DateUtil.stringToDate(endDate,DateUtil.YYYY_MM_DD_HH_MM_SS);
        }catch (Exception e){

        }
        BigDecimal ten = new BigDecimal(100);
        for (int i = 0; i < list.size(); i++) {

            Integer x = 0;
            YMAscription ascription = new YMAscription();
            ascription.setUser_id(list.get(i).getUser_id());
            ascription.setSpuid(list.get(i).getSpuid());
            while (x == 0){
                ascription = ascriptionMapper.selectByUserId2(ascription);
                if (ascription == null){
                    x = 1;
                }else{
                    for (int j = 0; j < stockholdersList.size(); j++) {
                        if (stockholdersList.get(j).getUser_id().equals(ascription.getUperid())){
                            list.get(i).setStockholderId(stockholdersList.get(j).getUser_id());
                            UserInfo user = userInfoService.selectById(String.valueOf(stockholdersList.get(j).getUser_id()));
                            list.get(i).setStockholderName(user.getNick_name());
                            x = 1;
                            break;
                        }
                    }
                    ascription.setUser_id(ascription.getUperid());
                }
            }

            Integer ax = 0;
            ArrayList<String> groupList = new ArrayList<String> ();
            groupList.add(0,String.valueOf(list.get(i).getUser_id()));
            List<YMAscription> groupList2 = new ArrayList<>();
            while (ax == 0){
                List<YMAscription> groupList3 = ascriptionMapper.selectByUserId(groupList,list.get(i).getSpuid());
                ArrayList<String> groupList4 = new ArrayList<String> ();
                if (groupList3.size() == 0){
                    ax = 1;
                }else{
                    for (int j = 0; j < groupList3.size(); j++) {
                        groupList4.add(j,String.valueOf(groupList3.get(j).getUser_id()));
                    }
                    groupList = groupList4;
                    groupList2.addAll(groupList3);
                }
            }



            String parId = String.valueOf(list.get(i).getUser_id());

            //获取当日退款数量
            List<Order> refundList1 = orderMapper.getRefundData(beginDate,endDate,parId,spuid);
            //获取所有退款数量
            List<Order> refundList2 = orderMapper.getRefundData(null,null,parId,spuid);
            //获取当日订单数量
            List<Order> orderList1 = orderMapper.getCountData(beginDate,endDate,parId,spuid);
            //获取所有订单数量
            List<Order> orderList2 = orderMapper.getCountData(null,null,parId,spuid);
            //合伙人当日分红
            List<BonusPoolOutBill> outBillList = bonusPoolOutBillService.getData3("1",parId,beginDate,endDate,spuid);


            Map<String,Object> map = new HashMap<>();

            //当日新增一级人数
            Integer today = 0;
            //当日二级人数
            Integer twoToday = 0;

            ArrayList<String> strArray = new ArrayList<String> ();
            strArray.add(0,String.valueOf(list.get(i).getUser_id()));
            List<YMAscription> asList2 = new ArrayList<>();
            //一级总人数
            List<YMAscription> oneasList = ascriptionMapper.selectByUserId(strArray,spuid);

            ArrayList<String> strArray2 = new ArrayList<String> ();
            for (int h = 0; h < oneasList.size(); h++) {
                strArray2.add(h,String.valueOf(oneasList.get(h).getUser_id()));
                if (oneasList.get(h).getCreate_time().getTime() >= begin.getTime() && oneasList.get(h).getCreate_time().getTime() <= end.getTime()){
                    today ++;
                }
            }

            //二级总人数
            List<YMAscription> twoasList = new ArrayList<>();
            if (strArray2 != null && strArray2.size() > 0){
                twoasList = ascriptionMapper.selectByUserId(strArray2,spuid);
                for (int k = 0; k < twoasList.size(); k++) {
                    if (twoasList.get(k).getCreate_time().getTime() >= begin.getTime() && twoasList.get(k).getCreate_time().getTime() <= end.getTime()){
                        twoToday ++;
                    }
                }
            }

            //合伙人分红
            if (outBillList.size() == 0){
                list.get(i).setYesterday_bonus(BigDecimal.ZERO);
            }else{
                BigDecimal all = BigDecimal.ZERO;
                for (int j = 0; j < outBillList.size(); j++) {
                    all = all.add(outBillList.get(j).getAmount());
                }
                list.get(i).setYesterday_bonus(all);
            }

            //一级累计总人数
            list.get(i).setOne_count(oneasList.size());
            //二级累计总人数
            list.get(i).setSecond_count(twoasList.size());
            //今日新增一级人数
            list.get(i).setOne_user_count(today);
            //今日新增二级人数
            list.get(i).setSecond_user_count(twoToday);
            //当日退货数量
            list.get(i).setRefund_order_count(refundList1.size());
            //当日订单数量
            list.get(i).setToday_order_count(orderList1.size());
            //所有订单数量
            list.get(i).setOrder_count(orderList2.size());
            //3级之后累计人数
            list.get(i).setThree_user_count(groupList2.size()-oneasList.size()-twoasList.size());


            BigDecimal a = new BigDecimal(refundList2.size());
            BigDecimal b = new BigDecimal(orderList2.size());
            if (b.compareTo(BigDecimal.ZERO) != 0){
                BigDecimal c = a.divide(b,2,BigDecimal.ROUND_FLOOR);
                list.get(i).setRefund_probability(c.multiply(ten).intValue()+"%");
            }else{
                list.get(i).setRefund_probability("0%");
            }

        }

        return list;
    }

    @Override
    public Page<BonusParents> getBonusSupplerData(DataTableDTO dataTableDTO, String beginDate, String endDate, String parentsId,String spuid) {
        PageHelper.startPage(dataTableDTO.getPageNum(), dataTableDTO.getLength());
        Page<BonusParents> list = platformMapper.getBonusSupplerData(null,null,parentsId,spuid);
        Page<BonusParents> parentsList = platformMapper.getBonusParentsData(null,null,parentsId,spuid);
        if (StringUtil.isBlank(beginDate) && StringUtil.isBlank(endDate)){
            beginDate = DateUtil.dateToString(DateUtil.getStartTime(),DateUtil.YYYY_MM_DD_HH_MM_SS);
            endDate = DateUtil.dateToString(DateUtil.getEndTime(),DateUtil.YYYY_MM_DD_HH_MM_SS);
        }

        Page<BonusParents> parentsTodayList = platformMapper.getBonusParentsData(beginDate,endDate,parentsId,spuid);

        Date begin = null;
        Date end = null;
        try{
            begin = DateUtil.stringToDate(beginDate,DateUtil.YYYY_MM_DD_HH_MM_SS);
            end = DateUtil.stringToDate(endDate,DateUtil.YYYY_MM_DD_HH_MM_SS);
        }catch (Exception e){

        }
        BigDecimal ten = new BigDecimal(100);
        for (int i = 0; i < list.size(); i++) {

            String parId = String.valueOf(list.get(i).getUser_id());

            //获取当日退款数量
            List<Order> refundList1 = orderMapper.getRefundData(beginDate,endDate,parId,spuid);
            //获取所有退款数量
            List<Order> refundList2 = orderMapper.getRefundData(null,null,parId,spuid);
            //获取当日订单数量
            List<Order> orderList1 = orderMapper.getCountData(beginDate,endDate,parId,spuid);
            //获取所有订单数量
            List<Order> orderList2 = orderMapper.getCountData(null,null,parId,spuid);
            //超级合伙人当日分红
            List<BonusPoolOutBill> outBillList = bonusPoolOutBillService.getData3("2",parId,beginDate,endDate,spuid);


            Map<String,Object> map = new HashMap<>();

            //当日新增一级人数
            Integer today = 0;
            //当日二级人数
            Integer twoToday = 0;
            //超级合伙人下的合伙人总数
            Integer bonusAllCount = 0;
            //超级合伙人下的当日新增合伙人数
            Integer bonusCount = 0;

            ArrayList<String> strArray = new ArrayList<String> ();
            strArray.add(0,String.valueOf(list.get(i).getUser_id()));
            List<YMAscription> asList2 = new ArrayList<>();
            //一级总人数
            List<YMAscription> oneasList = ascriptionMapper.selectByUserId(strArray,spuid);

            ArrayList<String> strArray2 = new ArrayList<String> ();
            for (int h = 0; h < oneasList.size(); h++) {

                for (int j = 0; j < parentsList.size(); j++) {
                    if (parentsList.get(j).getUser_id().equals(oneasList.get(h).getUser_id())){
                        bonusAllCount ++;
                    }
                }

                for (int j = 0; j < parentsTodayList.size(); j++) {
                    if (parentsTodayList.get(j).getUser_id().equals(oneasList.get(h).getUser_id())){
                        bonusCount ++;
                    }
                }


                strArray2.add(h,String.valueOf(oneasList.get(h).getUser_id()));
                if (oneasList.get(h).getCreate_time().getTime() >= begin.getTime() && oneasList.get(h).getCreate_time().getTime() <= end.getTime()){
                    today ++;
                }
            }
            //二级总人数
            List<YMAscription> twoasList = new ArrayList<>();
            if (strArray2 != null && strArray2.size() > 0){
                twoasList = ascriptionMapper.selectByUserId(strArray2,spuid);
                for (int k = 0; k < twoasList.size(); k++) {

                    for (int j = 0; j < parentsList.size(); j++) {
                        if (parentsList.get(j).getUser_id().equals(twoasList.get(k).getUser_id())){
                            bonusAllCount ++;
                        }
                    }

                    for (int j = 0; j < parentsTodayList.size(); j++) {
                        if (parentsTodayList.get(j).getUser_id().equals(twoasList.get(k).getUser_id())){
                            bonusCount ++;
                        }
                    }

                    if (twoasList.get(k).getCreate_time().getTime() >= begin.getTime() && twoasList.get(k).getCreate_time().getTime() <= end.getTime()){
                        twoToday ++;
                    }
                }
            }

            //合伙人分红
            if (outBillList.size() == 0){
                list.get(i).setYesterday_bonus(BigDecimal.ZERO);
            }else{
                BigDecimal all = BigDecimal.ZERO;
                for (int j = 0; j < outBillList.size(); j++) {
                    all = all.add(outBillList.get(j).getAmount());
                }
                list.get(i).setYesterday_bonus(all);
            }

            //一级累计总人数
            list.get(i).setOne_count(oneasList.size());
            //二级累计总人数
            list.get(i).setSecond_count(twoasList.size());
            //今日新增一级人数
            list.get(i).setOne_user_count(today);
            //今日新增二级人数
            list.get(i).setSecond_user_count(twoToday);
            //当日退货数量
            list.get(i).setRefund_order_count(refundList1.size());
            //当日订单数量
            list.get(i).setToday_order_count(orderList1.size());
            //所有订单数量
            list.get(i).setOrder_count(orderList2.size());
            //总合伙人数量
            list.get(i).setBonusAllCount(bonusAllCount);
            //当日新增合伙人数量
            list.get(i).setBonusCount(bonusCount);


            BigDecimal a = new BigDecimal(refundList2.size());
            BigDecimal b = new BigDecimal(orderList2.size());
            if (b.compareTo(BigDecimal.ZERO) != 0){
                BigDecimal c = a.divide(b,2,BigDecimal.ROUND_FLOOR);
                list.get(i).setRefund_probability(c.multiply(ten).intValue()+"%");
            }else{
                list.get(i).setRefund_probability("0%");
            }

        }

        return list;
    }

    @Override
    public List<Map<String,Object>> getBonusEstimateData(String amount,String spuid) {
        String endDate = DateUtil.dateToString(DateUtil.getAddDay(-2),DateUtil.YYYY_MM_DD_HH_MM_SS);
        String yestime = DateUtil.dateToString(DateUtil.getAddDay(-1),DateUtil.YYYY_MM_DD);
        BigDecimal amount2 = BigDecimal.ZERO;
        if (StringUtil.isNotBlank(amount)){
            amount2 = new BigDecimal(amount);
        }
        List<Map<String,Object>> mapList = new ArrayList<>();
        BonusPool bonusPool = new BonusPool();
        BonusPool bonusPool2 = new BonusPool();
        bonusPool.setPool_type(1);
        bonusPool.setSpuid(spuid);
        bonusPool2 = bonusPoolService.getData(bonusPool);
        if (spuid.equals("ZY-97431990")){
            if (bonusPool2 != null){
                Integer partenetCount = bonusPartenerService.getCountBySpuid(spuid,endDate);
                if (partenetCount == null){
                    partenetCount = 0;
                }
                BigDecimal allCount = new BigDecimal(partenetCount);
                //每份所值金额
                BigDecimal allAmount = bonusPool2.getAmount().add(amount2);
                BigDecimal eveyOne = allAmount.divide(allCount,4,BigDecimal.ROUND_FLOOR);
                BonusPartener bonusPartener = new BonusPartener();
                bonusPartener.setSpuid(spuid);
                bonusPartener.setCreate_time(DateUtil.getAddDay(-2));
                List<BonusPartener> partenersList =  bonusPartenerService.getData(bonusPartener);
                for (int j = 0; j < partenersList.size(); j++){
                    Map<String,Object> map = new HashMap<>();
                    UserInfo userInfo = userInfoService.selectById(String.valueOf(partenersList.get(j).getUser_id()));
                    BigDecimal moreSales = new BigDecimal(partenersList.get(j).getMore_sales());
                    BigDecimal count = new BigDecimal(partenersList.get(j).getTotal_pro_sales());
                    //分红金额
                    BigDecimal amount3 = count.multiply(eveyOne);
                    //增量分红
                    BigDecimal amount4 = moreSales.multiply(eveyOne);
                    if (userInfo != null){
                        map.put("userName",userInfo.getNick_name());
                    }else{
                        map.put("userName","");
                    }
                    map.put("amount3",amount3);
                    map.put("amount4",amount4);
                    mapList.add(map);
                }

            }
        }
        if (spuid.equals("ZY-24020206")){
            List<BonusPartener> parList = bonusPartenerMapper.getDataBySpuid(bonusPool2.getSpuid(),yestime);
            //符合要求合伙人总销量
            Integer partenetCount = 0;
            for (int i = 0; i < parList.size(); i++) {
                partenetCount += parList.get(i).getTotal_pro_sales();
            }
            BigDecimal allCount = new BigDecimal(partenetCount);
            //每份所值金额
            BigDecimal eveyOne = BigDecimal.ZERO;
            if (allCount.compareTo(BigDecimal.ZERO) != 0){
                eveyOne = bonusPool2.getAmount().add(amount2).divide(allCount,4,BigDecimal.ROUND_FLOOR);
            }
            for (int i = 0; i < parList.size(); i++) {
                Map<String,Object> map = new HashMap<>();
                UserInfo userInfo = userInfoService.selectById(String.valueOf(parList.get(i).getUser_id()));
                BigDecimal moreSales = new BigDecimal(parList.get(i).getMore_sales());
                BigDecimal count = new BigDecimal(parList.get(i).getTotal_pro_sales());
                //分红金额
                BigDecimal amount3 = count.multiply(eveyOne);
                //增量分红
                BigDecimal amount4 = moreSales.multiply(eveyOne);
                if (userInfo != null){
                    map.put("userName",userInfo.getNick_name());
                }else{
                    map.put("userName","");
                }
                map.put("amount3",amount3);
                map.put("amount4",amount4);
                mapList.add(map);
            }

        }

        Collections.sort(mapList, new Comparator<Map<String, Object>>() {
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                Double name1 = Double.valueOf(o1.get("amount3").toString()) ;
                Double name2 = Double.valueOf(o2.get("amount3").toString()) ;
                return name2.compareTo(name1);
            }
        });

        return mapList;
    }

    @Override
    public DataTableResult updatePoolAmount(String amount,String type,String spuid) {
        DataTableResult result = new DataTableResult();
        if (StringUtil.isBlank(spuid)){
            result.setError("系统错误");
            result.setDraw(0);
            return result;
        }
        result.setError("增加成功");
        result.setDraw(1);
        BigDecimal amount2 = BigDecimal.ZERO;
        if (StringUtil.isNotBlank(amount)){
            amount2 = new BigDecimal(amount);
        }
        BonusPool bonusPool = new BonusPool();
        BonusPool bonusPool2 = new BonusPool();

        bonusPool.setPool_type(Integer.parseInt(type));
        bonusPool.setSpuid(spuid);
        bonusPool2 = bonusPoolService.getData(bonusPool);

        bonusPool2.setAmount(bonusPool2.getAmount().add(amount2));
        try{
            Integer res = bonusPoolService.updateBonusPool(bonusPool2);
        }catch (Exception e){
            result.setDraw(0);
            result.setError("增加失败");
        }

        return result;
    }

    @Override
    public List<Map<String, Object>> getBonusSupplierEstimateData(String amount,String spuid) {
        String yestime = DateUtil.dateToString(DateUtil.getAddDay(-1),DateUtil.YYYY_MM_DD);
        String endDate = DateUtil.dateToString(DateUtil.getAddDay(-2),DateUtil.YYYY_MM_DD_HH_MM_SS);
        BigDecimal amount2 = BigDecimal.ZERO;
        if (StringUtil.isNotBlank(amount)){
            amount2 = new BigDecimal(amount);
        }
        List<Map<String,Object>> mapList = new ArrayList<>();
        BonusPool bonusPool = new BonusPool();
        BonusPool bonusPool2 = new BonusPool();

        bonusPool.setPool_type(2);
        bonusPool.setSpuid(spuid);
        bonusPool2 = bonusPoolService.getData(bonusPool);
        if (spuid.equals("ZY-97431990")){
            if (bonusPool2 != null){
                Integer stockholderCount = bonusStockholderService.getCountBySpuid(spuid,endDate);
                if (stockholderCount == null){
                    stockholderCount = 0;
                }
                BigDecimal allCount = new BigDecimal(stockholderCount);
                //每份所值金额
                BigDecimal allAmount = bonusPool2.getAmount().add(amount2);
                BigDecimal eveyOne = allAmount.divide(allCount,4,BigDecimal.ROUND_FLOOR);
                BonusStockholder bonusStockholder = new BonusStockholder();
                bonusStockholder.setSpuid(spuid);
                bonusStockholder.setCreate_time(DateUtil.getAddDay(-2));
                List<BonusStockholder> stockholdersList =  bonusStockholderService.getData(bonusStockholder);
                for (int j = 0; j < stockholdersList.size(); j++){
                    Map<String,Object> map = new HashMap<>();
                    UserInfo userInfo = userInfoService.selectById(String.valueOf(stockholdersList.get(j).getUser_id()));
                    BigDecimal moreSales = new BigDecimal(stockholdersList.get(j).getMore_sales());
                    BigDecimal count = new BigDecimal(stockholdersList.get(j).getTotal_pro_sales());
                    //分红金额
                    BigDecimal amount3 = count.multiply(eveyOne);
                    //增量分红
                    BigDecimal amount4 = moreSales.multiply(eveyOne);
                    if (userInfo != null){
                        map.put("userName",userInfo.getNick_name());
                    }else{
                        map.put("userName","");
                    }
                    map.put("amount3",amount3);
                    map.put("amount4",amount4);
                    mapList.add(map);
                }

            }
        }
        if (spuid.equals("ZY-24020206")){
            List<BonusStockholder> list = bonusStockholderMapper.getDataBySpuid(bonusPool2.getSpuid(),yestime);
            Integer partenetCount = 0;
            for (int i = 0; i < list.size(); i++) {
                partenetCount += list.get(i).getTotal_pro_sales();
            }
            BigDecimal allCount = new BigDecimal(partenetCount);

            //每份所值金额
            BigDecimal eveyOne = BigDecimal.ZERO;
            if (allCount.compareTo(BigDecimal.ZERO) != 0){
                eveyOne = bonusPool2.getAmount().add(amount2).divide(allCount,4,BigDecimal.ROUND_FLOOR);
            }
            for (int i = 0; i < list.size(); i++) {
                Map<String,Object> map = new HashMap<>();
                UserInfo userInfo = userInfoService.selectById(String.valueOf(list.get(i).getUser_id()));
                BigDecimal moreSales = new BigDecimal(list.get(i).getMore_sales());
                BigDecimal count = new BigDecimal(list.get(i).getTotal_pro_sales());
                //分红金额
                BigDecimal amount3 = count.multiply(eveyOne);
                //增量分红
                BigDecimal amount4 = moreSales.multiply(eveyOne);
                if (userInfo != null){
                    map.put("userName",userInfo.getNick_name());
                }else{
                    map.put("userName","");
                }
                map.put("amount3",amount3);
                map.put("amount4",amount4);
                mapList.add(map);
            }

        }

        Collections.sort(mapList, new Comparator<Map<String, Object>>() {
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                Double name1 = Double.valueOf(o1.get("amount3").toString()) ;
                Double name2 = Double.valueOf(o2.get("amount3").toString()) ;
                return name2.compareTo(name1);
            }
        });

        return mapList;
    }

    @Override
    public Page<YMGrouper> getGrouperData(DataTableDTO dataTableDTO, YMGrouper grouper) {
        PageHelper.startPage(dataTableDTO.getPageNum(), dataTableDTO.getLength());
        return grouperMapper.selectGrouperByTime(grouper);
    }

    @Override
    public List<UserInfo> getNewPersionData(String beginDate, String endDate, String parentsId, String spuid) {
        List<UserInfo> list = grouperMapper.selectNewGrouper(beginDate,endDate,spuid);
        List<UserInfo> list2 = new ArrayList<>();
        if (list.size() > 0){
            Integer ax = 0;
            ArrayList<String> groupList = new ArrayList<String> ();
            groupList.add(0,parentsId);
            List<YMAscription> groupList2 = new ArrayList<>();
            while (ax == 0){
                List<YMAscription> groupList3 = ascriptionMapper.selectByUserId(groupList,spuid);
                ArrayList<String> groupList4 = new ArrayList<String> ();
                if (groupList3.size() == 0){
                    ax = 1;
                }else{
                    for (int j = 0; j < groupList3.size(); j++) {
                        groupList4.add(j,String.valueOf(groupList3.get(j).getUser_id()));
                    }
                    groupList = groupList4;
                    groupList2.addAll(groupList3);
                }
            }
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < groupList2.size(); j++) {
                    if (list.get(i).getUser_id().equals(groupList2.get(j).getUser_id())){
                        list2.add(list.get(i));
                    }
                }
            }
        }
        return list2;
    }


}
