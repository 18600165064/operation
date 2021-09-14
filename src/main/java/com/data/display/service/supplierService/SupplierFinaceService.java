package com.data.display.service.supplierService;

import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.supplier.SupplierFinace;
import com.github.pagehelper.Page;

public interface SupplierFinaceService {

	Page<SupplierFinace> getSupplierFinaceData(DataTableDTO dataTableDTO, SupplierFinace supplierFinace);

	DataTableResult withdrawal(String id, String deduction_money);

}
