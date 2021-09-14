package com.data.display.service.platformService.impl;

import com.data.display.model.dto.DateDTO;
import com.data.display.service.platformService.WxanalysisService;

/**
 * @Author: CYN
 * @Date: 2019/6/4 11:53
 * @Description:
 */
public class WxanalysisServiceFallBack implements WxanalysisService {

    @Override
    public String getMonthlyRetain(String access_token, DateDTO dateDTO) {
        return null;
    }

    @Override
    public String getWeeklyRetain(String access_token, DateDTO dateDTO) {
        return null;
    }

    @Override
    public String getDailyRetain(String access_token, DateDTO dateDTO) {
        return null;
    }

    @Override
    public String getMonthlyVisitTrend(String access_token, DateDTO dateDTO) {
        return null;
    }

    @Override
    public String getWeeklyVisitTrend(String access_token, DateDTO dateDTO) {
        return null;
    }

    @Override
    public String getDailyVisitTrend(String access_token, DateDTO dateDTO) {
        return null;
    }

    @Override
    public String getUserPortrait(String access_token, DateDTO dateDTO) {
        return null;
    }

    @Override
    public String getVisitDistribution(String access_token, DateDTO dateDTO) {
        return null;
    }

    @Override
    public String getVisitPage(String access_token, DateDTO dateDTO) {
        return null;
    }

    @Override
    public String getDailySummary(String access_token, DateDTO dateDTO) {
        return null;
    }
}
