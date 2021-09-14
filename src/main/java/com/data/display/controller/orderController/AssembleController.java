package com.data.display.controller.orderController;

import com.alibaba.fastjson.JSON;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.order.Assemble;
import com.data.display.model.order.Order;
import com.data.display.service.orderService.AssembleService;
import com.github.pagehelper.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/assemble")
public class AssembleController {

    private static Logger _log = LoggerFactory.getLogger(AssembleController.class);

    @Autowired
    private AssembleService assembleService;

    @RequestMapping("/assembleMenu")
    public String assembleList() {
        return "/order/assembleMenu";
    }


    @RequestMapping("/getAssembleData")
    @ResponseBody
    public String getAssembleData(DataTableDTO dataTableDTO,Assemble assemble) {
        Page<Assemble> list = assembleService.getAssembleData(dataTableDTO,assemble);
        DataTableResult dataTableResult = new DataTableResult(dataTableDTO.getDraw(),list.getTotal(),list.getTotal(),list.getResult());
        return JSON.toJSONString(dataTableResult);
    }

}
