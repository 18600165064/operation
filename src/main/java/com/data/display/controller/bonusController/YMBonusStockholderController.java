package com.data.display.controller.bonusController;

import com.alibaba.fastjson.JSON;
import com.data.display.model.bonus.BonusStockholder;
import com.data.display.service.bonusService.YMBonusStockholderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 股东
 */
@Controller
@RequestMapping("bonusStockholder")
public class YMBonusStockholderController {

    @Autowired
    YMBonusStockholderService bonusStockholderService;


    /**
     * 获取股东数据
     * @param bonusStockholder
     * @return
     */
    @RequestMapping("/getData")
    @ResponseBody
    public String getData(BonusStockholder bonusStockholder) {
        List<BonusStockholder> list = bonusStockholderService.getData(bonusStockholder);
        return JSON.toJSONString(list);
    }


}
