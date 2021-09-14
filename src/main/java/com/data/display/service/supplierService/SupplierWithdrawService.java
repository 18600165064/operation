package com.data.display.service.supplierService;

import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.supplier.SupplierWithdraw;
import com.github.pagehelper.Page;

public interface SupplierWithdrawService {

	Page<SupplierWithdraw> getSupplierWithdrawData(DataTableDTO dataTableDTO, SupplierWithdraw supplierWithdraw);

	DataTableResult liquidationAgo(SupplierWithdraw supplierWithdraw);

	DataTableResult submit(SupplierWithdraw supplierWithdraw);

	DataTableResult apply();

}
