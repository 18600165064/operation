package com.data.display.mapper.orderMapper;

import com.data.display.model.order.Delivery;

public interface DeliveryMapper {

	Delivery selectByNu(Delivery delivery);

	int updateDeliveryByNu(Delivery delivery);
	
	int updateDelivery(Delivery delivery);
	
	int addDelivery(Delivery delivery);
	
}
