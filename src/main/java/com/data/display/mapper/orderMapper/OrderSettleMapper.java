package com.data.display.mapper.orderMapper;

import java.util.List;

import com.data.display.model.order.OrderSettle;
import com.github.pagehelper.Page;

public interface OrderSettleMapper {

	Page<OrderSettle> getOrderSettleData(OrderSettle orderSettle);

	Integer updateOrderSettle(OrderSettle orderSettle);
	
	Integer updateOrderSettleOthers(OrderSettle orderSettle);

	int count(OrderSettle orderSettle);

	int addOrderSettle(OrderSettle orderSettle);
	
	Object countBySettleStatus(OrderSettle orderSettle);

	List<OrderSettle> getOrderSettleList(OrderSettle orderSettle);

}
