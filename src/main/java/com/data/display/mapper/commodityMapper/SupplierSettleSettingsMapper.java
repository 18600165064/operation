package com.data.display.mapper.commodityMapper;

import com.data.display.model.commodity.SupplierSettleSettings;
import java.util.List;

public interface SupplierSettleSettingsMapper {

	List<SupplierSettleSettings> getSupplierSettleSettingsData(SupplierSettleSettings supplierSettleSettings);
	
	Integer addSupplierSettleSettings(SupplierSettleSettings supplierSettleSettings);

	Integer deleteByShippingId(String id);

	SupplierSettleSettings selectById(Integer id);

	Integer editSupplierSettleSettings(SupplierSettleSettings supplierSettleSettings);

	Integer deleteByPrimaryKey(String id);
}
