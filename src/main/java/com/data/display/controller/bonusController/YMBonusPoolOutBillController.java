package com.data.display.controller.bonusController;


import com.data.display.service.bonusService.YMBonusPoolOutBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("bonusPoolOutBill")
public class YMBonusPoolOutBillController {

    @Autowired
    YMBonusPoolOutBillService bonusPoolOutBillService;

}
