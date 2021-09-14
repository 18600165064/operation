package com.data.display.mapper.supplierMapper;

import com.data.display.model.supplier.SupplierFinace;
import com.github.pagehelper.Page;

public interface SupplierFinaceMapper {

	Page<SupplierFinace> getSupplierFinaceData(SupplierFinace supplierFinace);

	SupplierFinace selectByOthers(SupplierFinace supplierFinace);

	SupplierFinace selectById(String id);
	
	int addSupplierFinace(SupplierFinace supplierFinance);

	int updateSupplierBySid(SupplierFinace supplierFinace);
	
	int updateSupplierById(SupplierFinace supplierFinace);

}
