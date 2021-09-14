package com.data.display.controller.supplierController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.supplier.SupplierInfo;
import com.data.display.service.supplierService.SupplierInfoService;
import com.github.pagehelper.Page;

@Controller
@RequestMapping("/supplierInfo")
public class SupplierInfoController {

	@Autowired
	private SupplierInfoService supplierInfoService;
	
	
	@RequestMapping("/supplierInfoMenu")
    public String supplierInfoMenu() {
        return "/supplier/supplierInfoMenu";
    }
	
	
	@RequestMapping("/editSupplierInfo")
    public String editSupplierInfo(Model model,SupplierInfo supplierInfo) {
			model.addAttribute("id", supplierInfo.getS_id());
        return "/supplier/editSupplierInfo";
    }
	
	
	@RequestMapping("/getSupplierInfoData")
    @ResponseBody
    public String getSupplierInfoData(DataTableDTO dataTableDTO,SupplierInfo supplierInfo) {
        Page<SupplierInfo> list = supplierInfoService.getSupplierInfoData(dataTableDTO,supplierInfo);
        DataTableResult dataTableResult = new DataTableResult(dataTableDTO.getDraw(),list.getTotal(),list.getTotal(),list.getResult());
        return JSON.toJSONString(dataTableResult);
    }
	
	
	@RequestMapping("/selectById")
    @ResponseBody
    public String selectById(String id) {
		SupplierInfo supplierInfo = supplierInfoService.selectById(id);
        return JSON.toJSONString(supplierInfo);
    }
	
	@RequestMapping("/updateSupplierInfo")
    @ResponseBody
    public String updateSupplierInfo(SupplierInfo supplierInfo) {
		  DataTableResult dataTableResult = supplierInfoService.updateSupplierInfo(supplierInfo);
        return JSON.toJSONString(dataTableResult);
    }
	
	
	
}
