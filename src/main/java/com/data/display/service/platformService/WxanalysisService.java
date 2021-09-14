package com.data.display.service.platformService;

import com.data.display.model.dto.DateDTO;
import com.data.display.service.platformService.impl.WxanalysisServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: CYN
 * @Date: 2019/6/4 11:52
 * @Description: 微信数据分析接口
 */
@FeignClient(value = "WxAnalysis", url = "https://api.weixin.qq.com/datacube/", fallback = WxanalysisServiceFallBack.class)
public interface WxanalysisService {

    /**
     * 获取用户访问小程序月留存 POST
     */
    @RequestMapping(method = RequestMethod.POST, value = "getweanalysisappidmonthlyretaininfo?access_token={access_token}", consumes = "application/json")
    String getMonthlyRetain(@PathVariable("access_token") String access_token, @RequestBody DateDTO dateDTO);

    /**
     * 获取用户访问小程序周留存 POST
     */
    @RequestMapping(method = RequestMethod.POST, value = "getweanalysisappidweeklyretaininfo?access_token={access_token}", consumes = "application/json")
    String getWeeklyRetain(@PathVariable("access_token") String access_token, @RequestBody DateDTO dateDTO);

    /**
     * 获取用户访问小程序日留存 POST
     */
    @RequestMapping(method = RequestMethod.POST, value = "getweanalysisappiddailyretaininfo?access_token={access_token}", consumes = "application/json")
    String getDailyRetain(@PathVariable("access_token") String access_token, @RequestBody DateDTO dateDTO);

    /**
     * 获取用户访问小程序数据月趋势 POST
     */
    @RequestMapping(method = RequestMethod.POST, value = "getweanalysisappidmonthlyvisittrend?access_token={access_token}", consumes = "application/json")
    String getMonthlyVisitTrend(@PathVariable("access_token") String access_token, @RequestBody DateDTO dateDTO);

    /**
     * 获取用户访问小程序数据周趋势 POST
     */
    @RequestMapping(method = RequestMethod.POST, value = "getweanalysisappidweeklyvisittrend?access_token={access_token}", consumes = "application/json")
    String getWeeklyVisitTrend(@PathVariable("access_token") String access_token, @RequestBody DateDTO dateDTO);

    /**
     * 获取用户访问小程序数据日趋势 POST
     */
    @RequestMapping(method = RequestMethod.POST, value = "getweanalysisappiddailyvisittrend?access_token={access_token}", consumes = "application/json")
    String getDailyVisitTrend(@PathVariable("access_token") String access_token, @RequestBody DateDTO dateDTO);

    /**
     * 获取小程序新增或活跃用户的画像分布数据。时间范围支持昨天、最近7天、最近30天。其中，
     * 新增用户数为时间范围内首次访问小程序的去重用户数，
     * 活跃用户数为时间范围内访问过小程序的去重用户数。 POST
     */
    @RequestMapping(method = RequestMethod.POST, value = "getweanalysisappiduserportrait?access_token={access_token}", consumes = "application/json")
    String getUserPortrait(@PathVariable("access_token") String access_token, @RequestBody DateDTO dateDTO);

    /**
     * 获取用户小程序访问分布数据  POST
     */
    @RequestMapping(method = RequestMethod.POST, value = "getweanalysisappidvisitdistribution?access_token={access_token}", consumes = "application/json")
    String getVisitDistribution(@PathVariable("access_token") String access_token, @RequestBody DateDTO dateDTO);

    /**
     * 访问页面。目前只提供按 page_visit_pv 排序的 top200。  POST
     */
    @RequestMapping(method = RequestMethod.POST, value = "getweanalysisappidvisitpage?access_token={access_token}", consumes = "application/json")
    String getVisitPage(@PathVariable("access_token") String access_token, @RequestBody DateDTO dateDTO);

    /**
     * 获取用户访问小程序数据概况  POST
     */
    @RequestMapping(method = RequestMethod.POST, value = "getweanalysisappiddailysummarytrend?access_token={access_token}", consumes = "application/json")
    String getDailySummary(@PathVariable("access_token") String access_token, @RequestBody DateDTO dateDTO);
}
