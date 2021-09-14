package com.data.display.controller.supplierController;

import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.ioc.aop.Aop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.data.display.mapper.supplierMapper.SupplierInfoMapper;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.supplier.SupplierFinace;
import com.data.display.model.supplier.SupplierInfo;
import com.data.display.model.user.SysUser;
import com.data.display.service.supplierService.SupplierFinaceService;
import com.data.display.util.UserUtil;
import com.github.pagehelper.Page;

@Controller
@RequestMapping("/supplier")
public class SupplierFinaceController {

	@Autowired
	private SupplierFinaceService supplierFinaceService;
	
	@Autowired
	private SupplierInfoMapper supplierInfoMapper;
	
	
	
	/**
	 * 供应商订单
	 * @return
	 */
	@RequestMapping("/supplierOrderMenu")
    public String supplierOrderMenu(Model model) {
		SysUser user = UserUtil.getUserMessage();
		SupplierInfo supplierInfo = supplierInfoMapper.selectByUserId(String.valueOf(user.getId()));
		if(supplierInfo != null){
			model.addAttribute("sid",supplierInfo.getS_id());
		}
        return "/supplier/supplierOrderMenu";
    }
	
	
	@RequestMapping("/supplierFinaceMenu")
    public String supplierFinaceMenu() {
        return "/supplier/supplierFinaceMenu";
    }
	
	
	@RequestMapping("/getSupplierFinaceData")
    @ResponseBody
    public String getSupplierFinaceData(DataTableDTO dataTableDTO,SupplierFinace supplierFinace) {
        Page<SupplierFinace> list = supplierFinaceService.getSupplierFinaceData(dataTableDTO,supplierFinace);
        DataTableResult dataTableResult = new DataTableResult(dataTableDTO.getDraw(),list.getTotal(),list.getTotal(),list.getResult());
        return JSON.toJSONString(dataTableResult);
    }
	
	@RequestMapping("/withdrawal")
    @ResponseBody
    public String withdrawal(String id,String deduction_money) {
		 DataTableResult dataTableResult = supplierFinaceService.withdrawal(id,deduction_money);
        return JSON.toJSONString(dataTableResult);
    }
	
}
