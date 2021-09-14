package com.data.display.service.commodityService.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.data.display.mapper.commodityMapper.SupplierAccountShippingSettingsMapper;
import com.data.display.model.dto.DataTableResult;
import com.data.display.service.commodityService.SupplierAccountShippingSettingsService;

@Service
public class SupplierAccountShippingSettingsServiceImpl implements SupplierAccountShippingSettingsService{

private static final Logger logger = LoggerFactory.getLogger(SupplierAccountShippingSettingsServiceImpl.class);
	
	@Resource
    private SupplierAccountShippingSettingsMapper supplierAccountShippingSettingsMapper;

	@Override
	public DataTableResult deleteByPrimaryKey(String id) {
		DataTableResult result = new DataTableResult();
		try {
			result.setError("删除成功");
			result.setDraw(supplierAccountShippingSettingsMapper.deleteByPrimaryKey(id));
		} catch (Exception e) {
			result.setError("删除失败");
			logger.error("删除失败", e);
		}
		return result;
	}
	
}
