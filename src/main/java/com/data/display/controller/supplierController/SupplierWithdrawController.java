package com.data.display.controller.supplierController;

import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.ioc.aop.Aop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.supplier.SupplierWithdraw;
import com.data.display.service.supplierService.SupplierWithdrawService;
import com.github.pagehelper.Page;

@Controller
@RequestMapping("/supplierWithdraw")
public class SupplierWithdrawController {

	@Autowired
	private SupplierWithdrawService supplierWithdrawService;
	
	
	@RequestMapping("/supplierWithdrawMenu")
    public String supplierInfoMenu() {
        return "/supplier/supplierWithdrawMenu";
    }
	
	/**
	 * 列表
	 * @param dataTableDTO
	 * @param
	 * @return
	 */
	@RequestMapping("/getSupplierWithdrawData")
    @ResponseBody
    public String getSupplierWithdrawData(DataTableDTO dataTableDTO,SupplierWithdraw supplierWithdraw) {
        Page<SupplierWithdraw> list = supplierWithdrawService.getSupplierWithdrawData(dataTableDTO,supplierWithdraw);
        DataTableResult dataTableResult = new DataTableResult(dataTableDTO.getDraw(),list.getTotal(),list.getTotal(),list.getResult());
        return JSON.toJSONString(dataTableResult);
    }
	
	/**
	 * 已经清算
	 * @param
	 * @return
	 */
	@RequestMapping("/liquidationAgo")
    @ResponseBody
    public String liquidationAgo(SupplierWithdraw SupplierWithdraw) {
		DataTableResult dataTableResult = supplierWithdrawService.liquidationAgo(SupplierWithdraw);
        return JSON.toJSONString(dataTableResult);
    }
	
	
	/**
	 * 确认清算
	 * @param SupplierWithdraw
	 * @return
	 */
	@RequestMapping("/submit")
    @ResponseBody
    public String submit(SupplierWithdraw SupplierWithdraw) {
		DataTableResult dataTableResult = supplierWithdrawService.submit(SupplierWithdraw);
        return JSON.toJSONString(dataTableResult);
    }
	
	/**
	 * 供应商一键提现
	 * @return
	 */
	@RequestMapping("/apply")
    @ResponseBody
    @Aop(TransAop.READ_COMMITTED)
    public String apply() {
		DataTableResult dataTableResult = supplierWithdrawService.apply();
        return JSON.toJSONString(dataTableResult);
    }
	
}
