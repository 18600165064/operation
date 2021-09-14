package com.data.display.mapper.commodityMapper;

import com.data.display.model.commodity.SupplierAccountShipping;
import com.github.pagehelper.Page;

public interface SupplierAccountShippingMapper {

	/**
	 * 添加
	 * @param supplierAccountShipping
	 * @return
	 */
	Integer addSupplierAccountShipping(SupplierAccountShipping supplierAccountShipping);

	/**
	 * 分页列表
	 * @param supplierAccountShipping
	 * @return
	 */
	Page<SupplierAccountShipping> getSupplierAccountShippingData(SupplierAccountShipping supplierAccountShipping);

	/**
	 * 编辑
	 * @param supplierAccountShipping
	 * @return
	 */
	Integer editSupplierAccountShipping(SupplierAccountShipping supplierAccountShipping);

	/**
	 * 删除
	 * @param sas_id
	 * @return
	 */
	Integer deleteByPrimaryKey(String sas_id);

	/**
	 * 根据ID获取信息
	 * @param sas_id
	 * @return
	 */
	SupplierAccountShipping selectById(Integer sas_id);

}
