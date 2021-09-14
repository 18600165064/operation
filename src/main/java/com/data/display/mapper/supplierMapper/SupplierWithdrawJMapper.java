package com.data.display.mapper.supplierMapper;

import com.data.display.model.supplier.SupplierWithdrawJournal;
import com.github.pagehelper.Page;

public interface SupplierWithdrawJMapper {

	Page<SupplierWithdrawJournal> getSupplierWithdrawJData(SupplierWithdrawJournal supplierWithdrawJournal);

	SupplierWithdrawJournal selectById(String valueOf);

	int addSupplierWithdrawJ(SupplierWithdrawJournal supplierWithdrawJournal);
	
	int updateSupplierWithdrawJ(SupplierWithdrawJournal supplierWithdrawJournal);

}
