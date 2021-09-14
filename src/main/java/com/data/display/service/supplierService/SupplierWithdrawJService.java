package com.data.display.service.supplierService;

import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.supplier.SupplierWithdrawJournal;
import com.github.pagehelper.Page;

public interface SupplierWithdrawJService {

	Page<SupplierWithdrawJournal> getSupplierWithdrawJData(DataTableDTO dataTableDTO,SupplierWithdrawJournal supplierWithdrawJournal);

}
