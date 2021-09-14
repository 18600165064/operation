package com.data.display.controller.bonusController;

import com.alibaba.fastjson.JSON;
import com.data.display.model.bonus.BonusPool;
import com.data.display.service.bonusService.YMBonusPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 分红池
 */

@Controller
@RequestMapping("/bonusPool")
public class YMBonusPoolController {

    @Autowired
    YMBonusPoolService bonusPoolService;

    /**
     * 分红池数据
     * @param bonusPool
     * @return
     */
    @RequestMapping("/getData")
    @ResponseBody
    public String getData(BonusPool bonusPool) {
        bonusPool = bonusPoolService.getData(bonusPool);
        return JSON.toJSONString(bonusPool);
    }

}
