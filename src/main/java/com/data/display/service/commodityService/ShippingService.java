package com.data.display.service.commodityService;

import com.data.display.model.commodity.Shipping;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.github.pagehelper.Page;

public interface ShippingService {

	DataTableResult addShipping(Shipping shipping);

	Page<Shipping> getShippingData(DataTableDTO dataTableDTO, Shipping shipping);

	DataTableResult editShipping(Shipping shipping);

	DataTableResult deleteByPrimaryKey(String id);

	Shipping selectById(String id);

}
