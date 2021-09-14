package com.data.display.mapper.supplierMapper;

import com.data.display.model.supplier.SupplierInfo;
import com.github.pagehelper.Page;

public interface SupplierInfoMapper {
	/**
	 * 添加
	 * @param supplierInfo
	 * @return
	 */
	Integer addSupplierInfo(SupplierInfo supplierInfo);
	/**
	 * 修改
	 * @param supplierInfo
	 * @return
	 */
	Integer updateSupplierInfo(SupplierInfo supplierInfo);
	
	Integer updateSupplierInfoByUserId(SupplierInfo supplierInfo);
	/**
	 * 分页列表
	 * @param supplierInfo
	 * @return
	 */
	Page<SupplierInfo> getSupplierInfoData(SupplierInfo supplierInfo);
	
	SupplierInfo selectById(String id);
	
	SupplierInfo selectByUserId(String userId);
}
