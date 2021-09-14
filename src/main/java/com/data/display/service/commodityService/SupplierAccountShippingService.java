package com.data.display.service.commodityService;

import java.util.List;

import com.data.display.model.commodity.SupplierAccountShipping;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.github.pagehelper.Page;

public interface SupplierAccountShippingService {

	Page<SupplierAccountShipping> getSupplierAccountShippingData(DataTableDTO dataTableDTO,
			SupplierAccountShipping supplierAccountShipping);

	DataTableResult addSupplierAccountShipping(SupplierAccountShipping supplierAccountShipping);

	DataTableResult editSupplierAccountShipping(SupplierAccountShipping supplierAccountShipping);

	DataTableResult deleteByPrimaryKey(String id);

	SupplierAccountShipping selectById(String id);

	List<SupplierAccountShipping> shippingList(SupplierAccountShipping supplierAccountShipping);

}
