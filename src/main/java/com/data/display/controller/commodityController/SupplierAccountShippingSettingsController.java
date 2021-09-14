package com.data.display.controller.commodityController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.data.display.model.dto.DataTableResult;
import com.data.display.service.commodityService.SupplierAccountShippingSettingsService;

@Controller
@RequestMapping("/supplierAccountShippingSettings")
public class SupplierAccountShippingSettingsController {

	@Autowired
	private SupplierAccountShippingSettingsService supplierAccountShippingSettingsService; 
	
	
	@RequestMapping("/delSupplierAccountShippingSettings")
    @ResponseBody
    public String delSupplierAccountShippingSettings(String id) {
		DataTableResult dataTableResult = supplierAccountShippingSettingsService.deleteByPrimaryKey(id);
        return JSON.toJSONString(dataTableResult);
    }
	
}
