package com.data.display.controller.commonController;


import com.alibaba.fastjson.JSON;
import com.data.display.model.dto.DataTableResult;
import com.data.display.service.PayService;
import com.data.display.service.commonService.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/common")
public class CommonController {

    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private CommonService commonService;

    @Autowired
    private PayService payService;

    @RequestMapping("/commonTest")
    public void commonTest(){
        commonService.refundTest();
    }

    @RequestMapping("/payOrder")
    public String payOrder(){
        return "/common/payOrder";
    }

    @RequestMapping("/getSkuData")
    @ResponseBody
    public String getSkuData(String orderNo) {
        log.info("支付单号==========="+orderNo);
        DataTableResult dataTableResult = payService.balanceQuery(orderNo);
        log.info("返回结果=========="+JSON.toJSONString(dataTableResult));
        return JSON.toJSONString(dataTableResult);
    }

}
