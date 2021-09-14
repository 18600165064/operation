package com.data.display.mapper.commodityMapper;

import java.util.List;
import com.data.display.model.commodity.SupplierAccountShippingSettings;

public interface SupplierAccountShippingSettingsMapper {

	List<SupplierAccountShippingSettings> getSupplierAccountShippingSettingsData(SupplierAccountShippingSettings supplierAccountShippingSettings);
	
	Integer addSupplierAccountShippingSettings(SupplierAccountShippingSettings supplierAccountShippingSettings);

	Integer deleteByShippingId(String id);
	
	SupplierAccountShippingSettings selectById(Integer id);

	Integer editSupplierAccountShippingSettings(SupplierAccountShippingSettings supplierAccountShippingSettings);

	Integer deleteByPrimaryKey(String id);
}
