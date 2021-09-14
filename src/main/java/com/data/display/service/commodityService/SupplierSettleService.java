package com.data.display.service.commodityService;

import com.data.display.model.commodity.SupplierSettle;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.github.pagehelper.Page;
import java.util.List;

public interface SupplierSettleService {

	Page<SupplierSettle> getSupplierSettleData(DataTableDTO dataTableDTO,SupplierSettle supplierSettle);

	DataTableResult addSupplierSettleDate(SupplierSettle supplierSettle);

	DataTableResult editSupplierSettleDate(SupplierSettle supplierSettle);

	DataTableResult deleteByPrimaryKey(String id);

	SupplierSettle selectById(String id);

	List<SupplierSettle> supplierSettleList(SupplierSettle supplierSettle);

}
