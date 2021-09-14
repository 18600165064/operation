package com.data.display.controller.commodityController;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.data.display.mapper.supplierMapper.SupplierInfoMapper;
import com.data.display.model.commodity.SupplierAccountShipping;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.supplier.SupplierInfo;
import com.data.display.model.user.SysUser;
import com.data.display.service.commodityService.SupplierAccountShippingService;
import com.data.display.util.UserUtil;
import com.github.pagehelper.Page;

@Controller
@RequestMapping("/supplierAccountShipping")
public class SupplierAccountShippingController {

	@Autowired
	private SupplierAccountShippingService supplierAccountShippingService;
	
	@Resource
	private SupplierInfoMapper supplierInfoMapper;
	
	/**
	 * 列表页
	 * @return
	 */
	@RequestMapping("/supplierAccountShippingMenu")
    public String supplierAccountShippingList(Model model,SupplierAccountShipping supplierAccountShipping) {
			model.addAttribute("id", supplierAccountShipping.getS_id());
        return "/commodity/supplierAccountShippingMenu";
    }
	
	/**
	 * 添加页
	 * @return
	 */
	@RequestMapping("/addSupplierAccountShipping")
    public String addSupplierAccountShipping(Model model,SupplierAccountShipping supplierAccountShipping) {
		model.addAttribute("id", supplierAccountShipping.getS_id());
        return "/commodity/addSupplierAccountShipping";
    }
	
	/**
	 * 编辑页
	 * @return
	 */
	@RequestMapping("/editSupplierAccountShipping")
    public String editSupplierAccountShipping(Model model,String id,String s_id) {
		model.addAttribute("id", id);
		model.addAttribute("s_id", s_id);
        return "/commodity/editSupplierAccountShipping";
    }
	
	
	/**
	 * 分页列表
	 * @param dataTableDTO
	 * @param
	 * @return
	 */
	@RequestMapping("/getSupplierAccountShippingData")
    @ResponseBody
    public String getSupplierAccountShippingData(DataTableDTO dataTableDTO,SupplierAccountShipping supplierAccountShipping) {
		SysUser user = UserUtil.getUserMessage();
		SupplierInfo supplierInfo = supplierInfoMapper.selectByUserId(String.valueOf(user.getId()));
		if(supplierInfo != null){
			supplierAccountShipping.setS_id(supplierInfo.getS_id());
		}
        Page<SupplierAccountShipping> list = supplierAccountShippingService.getSupplierAccountShippingData(dataTableDTO,supplierAccountShipping);
        DataTableResult dataTableResult = new DataTableResult(dataTableDTO.getDraw(),list.getTotal(),list.getTotal(),list.getResult());
        return JSON.toJSONString(dataTableResult);
    }
	
	
	/**
	 * 无分页列表(根据登录人获取列表)
	 * @param supplierAccountShipping
	 * @return
	 */
	@RequestMapping("/shippingList")
    @ResponseBody
    public String shippingList(SupplierAccountShipping supplierAccountShipping) {
		SysUser user = UserUtil.getUserMessage();
		SupplierInfo supplierInfo = supplierInfoMapper.selectByUserId(String.valueOf(user.getId()));
		if(supplierInfo != null){
			supplierAccountShipping.setS_id(supplierInfo.getS_id());
		}
        List<SupplierAccountShipping> list = supplierAccountShippingService.shippingList(supplierAccountShipping);
        return JSON.toJSONString(list);
    }
	
	
	/**
	 * 添加
	 * @param
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addSupplierAccountShippingDate")
    @ResponseBody
    public String addSupplierAccountShipping(SupplierAccountShipping supplierAccountShipping,HttpServletRequest request,HttpServletResponse response) {
		DataTableResult dataTableResult = supplierAccountShippingService.addSupplierAccountShipping(supplierAccountShipping);
        return JSON.toJSONString(dataTableResult);
    }
	
	/**
	 * 编辑
	 * @param
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editSupplierAccountShippingData")
    @ResponseBody
    public String editSupplierAccountShippingData(SupplierAccountShipping supplierAccountShipping,HttpServletRequest request,HttpServletResponse response) {
		DataTableResult dataTableResult = supplierAccountShippingService.editSupplierAccountShipping(supplierAccountShipping);
        return JSON.toJSONString(dataTableResult);
    }
	
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/delSupplierAccountShipping")
    @ResponseBody
    public String delSupplierAccountShipping(String id) {
		DataTableResult dataTableResult = supplierAccountShippingService.deleteByPrimaryKey(id);
        return JSON.toJSONString(dataTableResult);
    }
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	@RequestMapping("/selectById")
    @ResponseBody
    public String selectById(String id) {
		SupplierAccountShipping supplierAccountShipping = supplierAccountShippingService.selectById(id);
        return JSON.toJSONString(supplierAccountShipping);
    }
}
