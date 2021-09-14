package com.data.display.controller.orderController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.order.OrderSettle;
import com.data.display.service.orderService.OrderSettleService;
import com.github.pagehelper.Page;

@Controller
@RequestMapping("/orderSettle")
public class OrderSettleController {

	@Autowired
	private OrderSettleService orderSettleService;
	
	/**
	 * 跳转到订单列表页
	 * 
	 * @return
	 */
	@RequestMapping("/orderSettleMenu")
    public String orderSettle() {
        return "/order/orderSettleMenu";
    }
	
	
	@RequestMapping("/getOrderSettleData")
    @ResponseBody
    public String getOrderSettleData(DataTableDTO dataTableDTO,OrderSettle orderSettle) {
        Page<OrderSettle> list = orderSettleService.getOrderSettleData(dataTableDTO,orderSettle);
        DataTableResult dataTableResult = new DataTableResult(dataTableDTO.getDraw(),list.getTotal(),list.getTotal(),list.getResult());
        return JSON.toJSONString(dataTableResult);
    }
	
}
