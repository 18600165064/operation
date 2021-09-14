package com.data.display.mapper.commodityMapper;

import com.data.display.model.commodity.SupplierSettle;
import com.github.pagehelper.Page;

public interface SupplierSettleMapper {

	/**
	 * 添加
	 * @param supplierSettle
	 * @return
	 */
	Integer addSupplierSettleDate(SupplierSettle supplierSettle);

	/**
	 * 分页列表
	 * @param supplierSettle
	 * @return
	 */
	Page<SupplierSettle> getSupplierSettleData(SupplierSettle supplierSettle);

	/**
	 * 编辑
	 * @param supplierSettle
	 * @return
	 */
	Integer editSupplierSettleDate(SupplierSettle supplierSettle);

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
	SupplierSettle selectById(Integer sas_id);

}
