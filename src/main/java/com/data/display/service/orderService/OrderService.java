package com.data.display.service.orderService;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.order.Order;
import com.github.pagehelper.Page;

public interface OrderService {

	Page<Order> getOrderData(DataTableDTO dataTableDTO,Order order);

	DataTableResult editOrderById(Order order);

	Order selectById(String id);

	DataTableResult submitRefund(Order order, BigDecimal amount);

	Page<Order> getOrderByAfterStatus(DataTableDTO dataTableDTO, Order order);

	Page<Order> getOrderByAfterStatus2(DataTableDTO dataTableDTO, Order order);

	Map<String,Object> getOtherData(String id);

	List<Map<String,Object>> exportPrem(String s_id,String spuid);

	List<Order> selectByOthers(Order order);

	int updateByPrimaryKey(Order order);

    DataTableResult selectRefundPrice(String id);

    List<Map<String, Object>> exportRefund();

}
