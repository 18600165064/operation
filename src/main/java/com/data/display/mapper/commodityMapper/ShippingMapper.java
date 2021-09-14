package com.data.display.mapper.commodityMapper;

import com.data.display.model.commodity.Shipping;
import com.github.pagehelper.Page;

public interface ShippingMapper {

	Integer addShipping(Shipping shipping);

	Page<Shipping> getShippingData(Shipping shipping);

	Integer deleteByPrimaryKey(String id);

	Integer editShipping(Shipping shipping);

	Shipping selectById(String id);

}
