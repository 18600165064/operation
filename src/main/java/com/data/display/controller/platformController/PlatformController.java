package com.data.display.controller.platformController;

import com.alibaba.fastjson.JSON;
import com.data.display.mapper.orderMapper.YMGrouperMapper;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.order.YMGrouper;
import com.data.display.model.platform.BonusParents;
import com.data.display.model.user.SysUser;
import com.data.display.model.user.UserInfo;
import com.data.display.service.platformService.PlatformService;
import com.data.display.util.*;
import com.data.display.util.config.WxConfig;
import com.github.pagehelper.Page;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("/platform")
public class PlatformController {

    private static Logger _log = LoggerFactory.getLogger(PlatformController.class);

    @Autowired
    PlatformService platformService;

    @Resource
    YMGrouperMapper grouperMapper;

    @RequestMapping("/statisticsMenu")
    public String statisticsMenu() {
        return "/platform/statisticsMenu";
    }


    @RequestMapping("/personCountMenu")
    public String personCountMenu() {
        return "/platform/personCountMenuTest";
    }


    @RequestMapping("/orderCountMenu")
    public String orderCountMenu() {
        return "/platform/orderCountMenuTest";
    }


    @RequestMapping("/bonusCountMenu")
    public String bonusCountMenu() {
        return "/platform/bonusCountMenuTest";
    }

    @RequestMapping("/bonusParentsMenu")
    public String teamCountMenu() {
        return "/platform/bonusTestMenu";
    }

    @RequestMapping("/bonusSupplerMenu")
    public String SupplerCountMenu() {
        return "/platform/bonusSupplerMenu";
    }

    @RequestMapping("/bonusEstimateMenu")
    public String bonusEstimateMenu() {
        return "/platform/bonusEstimateMenu";
    }

    @RequestMapping("/bonusSupplierEstimateMenu")
    public String bonusSupplierEstimateMenu() {
        return "/platform/bonusSupplierEstimateMenu";
    }

    @RequestMapping("/grouperMenu")
    public String grouperMenu() {
        return "/platform/grouperMenu";
    }

    /**
     * 合伙人明细查看
     */
    @RequestMapping("/bonusParentsMenu10")
    public ModelAndView bonusParentsMenu10() {
        SysUser user = UserUtil.getUserMessage();
        if (user.getNick_name().equals("合伙人明细查看")){
            return new ModelAndView("forward:/platform/bonusParentsMenu2");
        }else
            return new ModelAndView("forward:/");

    }


    /**
     * 首页跳转
     * @return
     */
    @RequestMapping("/bonusCountTest")
    public ModelAndView bonusCountTest() {
        SysUser user = UserUtil.getUserMessage();
        if (user.getUsername().equals("admin") || user.getUsername().equals("boss")){
            return new ModelAndView("forward:/platform/bonusCountMenu2");
        }else
            return new ModelAndView("forward:/");
    }

    @RequestMapping("/bonusPersonCountTest")
    public ModelAndView bonusPersonCountTest() {
        SysUser user = UserUtil.getUserMessage();
        if (user.getUsername().equals("admin") || user.getUsername().equals("boss") || user.getUsername().equals("jiaopingping") || user.getUsername().equals("zhaojiaxin")){
            return new ModelAndView("forward:/platform/bonusParentsMenu2");
        }else
            return new ModelAndView("forward:/");
    }

    @RequestMapping("/bonusSupplerTest")
    public ModelAndView bonusSupplerTest() {
        SysUser user = UserUtil.getUserMessage();
        if (user.getUsername().equals("admin") || user.getUsername().equals("boss") || user.getUsername().equals("jiaopingping") || user.getUsername().equals("zhaojiaxin")){
            return new ModelAndView("forward:/platform/bonusSupplerMenu");
        }else
            return new ModelAndView("forward:/");
    }


    @RequestMapping("/orderCountTest")
    public ModelAndView orderCountTest() {
        SysUser user = UserUtil.getUserMessage();
        if (user.getUsername().equals("admin") || user.getUsername().equals("boss") || user.getUsername().equals("jiaopingping") || user.getUsername().equals("zhaojiaxin")){
            return new ModelAndView("forward:/platform/orderCountMenu2");
        }else
            return new ModelAndView("forward:/");
    }

    @RequestMapping("/personCountTest")
    public ModelAndView personCountTest() {
        SysUser user = UserUtil.getUserMessage();
        if (user.getUsername().equals("admin") || user.getUsername().equals("boss")){
            return new ModelAndView("forward:/platform/personCountMenu2");
        }else
            return new ModelAndView("forward:/");
    }


    @RequestMapping("/bonusCountMenu2")
    public String bonusCountMenu2() {
        return "/platform/bonusCountMenuTest";
    }

    @RequestMapping("/bonusParentsMenu2")
    public String bonusParentsMenu2(Model model) {
        SysUser user = UserUtil.getUserMessage();
        if (user.getNick_name().equals("合伙人明细查看")){
            model.addAttribute("userid",user.getUsername());
        }
        return "/platform/bonusTestMenu";
    }

    @RequestMapping("/orderCountMenu2")
    public String orderCountMenu2() {
        return "/platform/orderCountMenuTest";
    }

    @RequestMapping("/personCountMenu2")
    public String personCountMenu2() {
        return "/platform/personCountMenuTest";
    }
    /**
     * 首页跳转
     * @return
     */



    /**
     * 累计会员人数统计
     * @param
     * @return
     */
    @RequestMapping("/getPersonCountData")
    @ResponseBody
    public String getPersonCountData(String beginDate,String endDate) {
        DataTableResult result = new DataTableResult();
        if (StringUtil.isBlank(beginDate) && StringUtil.isBlank(endDate)){
            beginDate = DateUtil.dateToString(DateUtil.getStartTime(),DateUtil.YYYY_MM_DD_HH_MM_SS);
            endDate = DateUtil.dateToString(DateUtil.getEndTime(),DateUtil.YYYY_MM_DD_HH_MM_SS);
        }
        Map<String,Object> map = platformService.getPersonCountData(beginDate,endDate);
        result.setDraw(1);
        result.setData(map);
        return JSON.toJSONString(result);
    }


    /**
     * 累计订单数统计
     * @param
     * @return
     */
    @RequestMapping("/getOrderCountData")
    @ResponseBody
    public String getOrderCountData(String beginDate,String endDate,String spuid) {
        DataTableResult result = new DataTableResult();
        _log.info("累计订单数统计=================="+spuid);
        if (StringUtil.isBlank(spuid)){
            spuid = WxConfig.TOP_SPU;
        }
        if (StringUtil.isBlank(beginDate) && StringUtil.isBlank(endDate)){
            beginDate = DateUtil.dateToString(DateUtil.getStartTime(),DateUtil.YYYY_MM_DD_HH_MM_SS);
            endDate = DateUtil.dateToString(DateUtil.getEndTime(),DateUtil.YYYY_MM_DD_HH_MM_SS);
        }
        Map<String,Object> map = platformService.getOrderCountData(beginDate,endDate,spuid);
        result.setDraw(1);
        result.setData(map);
        return JSON.toJSONString(result);
    }

    /**
     * 分红池统计
     * @param
     * @return
     */
    @RequestMapping("/getBonusCountData")
    @ResponseBody
    public String getBonusCountData(String beginDate,String endDate,String spuid) {
        DataTableResult result = new DataTableResult();
        _log.info("分红池统计=================="+spuid);
        if (StringUtil.isBlank(spuid)){
            spuid = WxConfig.TOP_SPU;
        }
        if (StringUtil.isBlank(beginDate) && StringUtil.isBlank(endDate)){
            beginDate = DateUtil.dateToString(DateUtil.getStartTime(),DateUtil.YYYY_MM_DD_HH_MM_SS);
            endDate = DateUtil.dateToString(DateUtil.getEndTime(),DateUtil.YYYY_MM_DD_HH_MM_SS);
        }
        Map<String,Object> map = platformService.getBonusCountData(beginDate,endDate,spuid);
        result.setDraw(1);
        result.setData(map);
        return JSON.toJSONString(result);
    }


    /**
     * 合伙人业绩统计
     * @param
     * @return
     */
    @RequestMapping("/getBonusParentsData")
    @ResponseBody
    public String getBonusParentsData(DataTableDTO dataTableDTO,String beginDate,String endDate,String parentsId,String spuid) {
        _log.info("合伙人业绩统计=================="+spuid);
        if (StringUtil.isBlank(spuid)){
            spuid = WxConfig.TOP_SPU;
        }
        Page<BonusParents> list = platformService.getBonusParentsData(dataTableDTO,beginDate,endDate,parentsId,spuid);
        DataTableResult dataTableResult = new DataTableResult(dataTableDTO.getDraw(),list.getTotal(),list.getTotal(),list.getResult());
        return JSON.toJSONString(dataTableResult);
    }


    /**
     * 获取新增会员
     * @param beginDate
     * @param endDate
     * @param parentsId
     * @param spuid
     * @return
     */
    @RequestMapping("/getNewPersionData")
    @ResponseBody
    public String getNewPersionData(String beginDate,String endDate,String parentsId,String spuid) {
        List<UserInfo> list = platformService.getNewPersionData(beginDate,endDate,parentsId,spuid);
        DataTableResult dataTableResult = new DataTableResult(1,(long)list.size(),(long)list.size(),list);
        return JSON.toJSONString(dataTableResult);
    }



    /**
     * 超级合伙人业绩统计
     * @param dataTableDTO
     * @param beginDate
     * @param endDate
     * @param parentsId
     * @return
     */
    @RequestMapping("/getBonusSupplerData")
    @ResponseBody
    public String getBonusSupplerData(DataTableDTO dataTableDTO,String beginDate,String endDate,String parentsId,String spuid) {
        _log.info("超级合伙人业绩统计=================="+spuid);
        if (StringUtil.isBlank(spuid)){
            spuid = WxConfig.TOP_SPU;
        }
        Page<BonusParents> list = platformService.getBonusSupplerData(dataTableDTO,beginDate,endDate,parentsId,spuid);
        DataTableResult dataTableResult = new DataTableResult(dataTableDTO.getDraw(),list.getTotal(),list.getTotal(),list.getResult());
        return JSON.toJSONString(dataTableResult);
    }


    /**
     * 超级合伙人分红预估
     * @param amount
     * @return
     */
    @RequestMapping("/getBonusSupplierEstimateData")
    @ResponseBody
    public String getBonusSupplierEstimateData(String amount,String spuid) {
        DataTableResult result = new DataTableResult();
        _log.info("超级合伙人分红预估=================="+spuid);
        if (StringUtil.isBlank(spuid)){
            spuid = WxConfig.TOP_SPU;
        }
        List<Map<String,Object>> list = platformService.getBonusSupplierEstimateData(amount,spuid);
        result.setDraw(1);
        result.setData(list);
        return JSON.toJSONString(result);
    }


    /**
     * 合伙人分红预估
     * @param amount
     * @return
     */
    @RequestMapping("/getBonusEstimateData")
    @ResponseBody
    public String getBonusEstimateData(String amount,String spuid) {
        DataTableResult result = new DataTableResult();
        _log.info("超级合伙人业绩统计=================="+spuid);
        if (StringUtil.isBlank(spuid)){
            spuid = WxConfig.TOP_SPU;
        }
        List<Map<String,Object>> list = platformService.getBonusEstimateData(amount,spuid);
        result.setDraw(1);
        result.setData(list);
        return JSON.toJSONString(result);
    }

    /**
     * 修改分红池金额
     * @param amount
     * @param type
     * @return
     */
    @RequestMapping("/updatePoolAmount")
    @ResponseBody
    public String updatePoolAmount(String amount,String type,String spuid) {
        DataTableResult result = platformService.updatePoolAmount(amount,type,spuid);
        return JSON.toJSONString(result);
    }




    @RequestMapping("/getPlatformData")
    @ResponseBody
    public String getPlatformData(String strDate) {
        DataTableResult result = new DataTableResult();
        Map<String,Object> map = platformService.getPlatformData(strDate);
        result.setDraw(1);
        result.setData(map);
        return JSON.toJSONString(result);
    }


    /**
     * 获取当日团长信息
     * @param dataTableDTO
     * @param grouper
     * @return
     */
    @RequestMapping("/getGrouperData")
    @ResponseBody
    public String getGrouperData(DataTableDTO dataTableDTO, YMGrouper grouper) {
        String strDate = null;
        if (StringUtil.isBlank(grouper.getBeginDate())){
            strDate = DateUtil.dateToString(DateUtil.getStartTime(),DateUtil.YYYY_MM_DD);
            grouper.setBeginDate(strDate);
        }
        if (StringUtil.isBlank(grouper.getSpuid())){
            grouper.setSpuid(WxConfig.TOP_SPU);
        }
        Page<YMGrouper> list = platformService.getGrouperData(dataTableDTO,grouper);
        DataTableResult dataTableResult = new DataTableResult(dataTableDTO.getDraw(),list.getTotal(),list.getTotal(),list.getResult());
        return JSON.toJSONString(dataTableResult);
    }


    /**
     * 团长信息导出
     * @param request
     * @param response
     * @param grouper
     */
    @RequestMapping("/exportGrouper")
    public void exportGrouper(HttpServletRequest request, HttpServletResponse response, YMGrouper grouper) {
        Page<YMGrouper> list = grouperMapper.selectGrouperByTime(grouper);
        String[] headers = {"ID","电话","姓名","昵称","盒数","推荐人姓名","推荐人昵称"};
        List<Object[]> objList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Object[] obj = new Object[7];
            obj[0] = list.get(i).getUser_id();
            obj[1] = list.get(i).getAddr_mobile();
            obj[2] = list.get(i).getAddr_name();
            obj[3] = list.get(i).getNick_name();
            obj[4] = list.get(i).getNum();
            obj[5] = list.get(i).getAddrname();
            obj[6] = list.get(i).getNickname();
            objList.add(obj);
        }
        Workbook workbook = ExportExeclUtil.makeExcelLargeData(objList,headers,StringUtil.dataRandom());
        ExportExeclUtil.downloadFile(workbook, request, response);
    }


//    @RequestMapping("/exportPrem")
//    public void exportPrem(HttpServletRequest request, HttpServletResponse response,String strDate){
//        if (StringUtil.isBlank(strDate)){
//            strDate = DateUtil.dateToString(new Date(),DateUtil.YYYY_MM_DD);
//        }
//        String fileName = "每日统计";
//        String sheetName[] = {"合伙人业绩统计", "分红池统计", "累计订单数统计", "累计人数统计"};
//
//        List<Map<String,Object>> map1 = platformService.getBonusParentsData2(strDate,null);
//        Map<String,Object> map2 = platformService.getBonusCountData(strDate);
//        Map<String,Object> map3 = platformService.getOrderCountData(strDate);
//        Map<String,Object> map4 = platformService.getPersonCountData(strDate);
//
//        List<LinkedHashMap<String, Object>> linkedHashMaps1 = new ArrayList<>();
//        List<LinkedHashMap<String, Object>> linkedHashMaps2 = new ArrayList<>();
//        List<LinkedHashMap<String, Object>> linkedHashMaps3 = new ArrayList<>();
//        List<LinkedHashMap<String, Object>> linkedHashMaps4 = new ArrayList<>();
//
//
//        for (int i = 0; i < map1.size(); i++) {
//            LinkedHashMap<String, Object> hshMap1 = new LinkedHashMap<>();
//            hshMap1.putAll(map1.get(i));
//            linkedHashMaps1.add(hshMap1);
//        }
//
//        LinkedHashMap<String, Object> hshMap2 = new LinkedHashMap<>();
//        LinkedHashMap<String, Object> hshMap3 = new LinkedHashMap<>();
//        LinkedHashMap<String, Object> hshMap4 = new LinkedHashMap<>();
//
//        hshMap2.putAll(map2);
//        hshMap3.putAll(map3);
//        hshMap4.putAll(map4);
//
//        linkedHashMaps2.add(hshMap2);
//        linkedHashMaps3.add(hshMap3);
//        linkedHashMaps4.add(hshMap4);
//
//
//        List data = new ArrayList();
//        data.add(linkedHashMaps1);
//        data.add(linkedHashMaps2);
//        data.add(linkedHashMaps3);
//        data.add(linkedHashMaps4);
//
//        List columnName = new ArrayList();
//        String siteColumnNames[] = {"合伙人昵称","合伙人ID","累计一级总人数","累计二级总人数","当日新增一级人数","当日新增二级人数","累计总单数","当日新增单数","当日退货单数","团队累计退货率","昨日分红"};//列名
//        String searchEngColumnNames[] = {"当日合伙人分红池新增金额", "当日超级合伙人分红池新增金额", "当日风控池新增金额", "当日新增合伙人数量", "当日新增超级合伙人数量", "累计合伙人总数", "累计超级合伙人总数", "当日合伙人平均分红", "当日超级合伙人平均分红"};//列名
//        String domainColumnNames[] = {"累计总订单数", "累计总支付订单数", "当日新增订单数", "当日下单转化", "当日下单增长比", "当日新增支付订单数", "当日支付人数", "当日支付转化","总销量(盒)","当日总销量(盒)"};//列名
//        String regionColumnNames[] = {"累计总用户数", "累计总支付人数", "当日新增人数", "当日小程序人数", "累计总开团数", "累计成功开团数", "开团成功率", "当日新增总开团数", "当日开团率","累计开团人数","成功开团人数","成功开团人数占比"};//列名
//        columnName.add(siteColumnNames);
//        columnName.add(searchEngColumnNames);
//        columnName.add(domainColumnNames);
//        columnName.add(regionColumnNames);
//
//        List keys = new ArrayList();
//        String siteKeys[] = {"userName", "userId", "todayAll", "twoTodayAll", "today", "twoToday", "orderTodayAll", "orderToday", "refund", "refundProportion","bonusOut"};//map中的key
//        String searchEngKeys[] = {"bonus1", "bonus2", "bonus3", "bonus4", "bonus5", "bonus6", "bonus7", "bonus8", "bonus9"};//map中的key
//        String domainKeys[] = {"num11", "num12", "num13", "num22", "num23", "num14", "num15", "num24","count2","count1"};//map中的key
//        String regionKeys[] = {"num1", "num2", "num3", "num20", "num4", "num5", "num17", "num18", "num21","num9","num10","num19"};//map中的key
//        keys.add(siteKeys);
//        keys.add(searchEngKeys);
//        keys.add(domainKeys);
//        keys.add(regionKeys);
//
//        try{
//            ExportTest.downExcelMoreSheet(data, fileName, sheetName, keys, columnName, response);
//        }catch (Exception e){
//            _log.error("导出错误"+JSON.toJSON(e));
//        }
//
//    }


}
