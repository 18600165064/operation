package com.data.display.mapper.supplierMapper;

import com.data.display.model.supplier.SupplierWithdraw;
import com.github.pagehelper.Page;

public interface SupplierWithdrawMapper {

	Page<SupplierWithdraw> getSupplierWithdrawData(SupplierWithdraw supplierWithdraw);

	SupplierWithdraw selectById(String id);

	Integer editSupplierWithdraw(SupplierWithdraw supplierWithdraw);

	void addSupplierWithdraw(SupplierWithdraw withdraw);
	
}
