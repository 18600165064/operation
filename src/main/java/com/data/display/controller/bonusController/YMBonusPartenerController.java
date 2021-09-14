package com.data.display.controller.bonusController;

import com.alibaba.fastjson.JSON;
import com.data.display.model.bonus.BonusPartener;
import com.data.display.service.bonusService.YMBonusPartenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * 合伙人
 */

@Controller
@RequestMapping("/bonusPartener")
public class YMBonusPartenerController {

    @Autowired
    YMBonusPartenerService bonusPartenerService;


    /**
     * 合伙人数据
     * @param bonusPartener
     * @return
     */
    @RequestMapping("/getData")
    @ResponseBody
    public String getData(BonusPartener bonusPartener) {
        List<BonusPartener> list = bonusPartenerService.getData(bonusPartener);
        return JSON.toJSONString(list);
    }

}
