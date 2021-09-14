package com.data.display.service.supplierService;

import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.supplier.SupplierInfo;
import com.github.pagehelper.Page;

public interface SupplierInfoService {

	Page<SupplierInfo> getSupplierInfoData(DataTableDTO dataTableDTO, SupplierInfo supplierInfo);

	SupplierInfo selectById(String id);

	DataTableResult updateSupplierInfo(SupplierInfo supplierInfo);

}
