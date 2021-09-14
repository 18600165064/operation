package com.data.display.controller.commodityController;

import com.alibaba.fastjson.JSON;
import com.data.display.mapper.supplierMapper.SupplierInfoMapper;
import com.data.display.model.commodity.SupplierSettle;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.supplier.SupplierInfo;
import com.data.display.model.user.SysUser;
import com.data.display.service.commodityService.SupplierSettleService;
import com.data.display.util.UserUtil;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/supplierSettle")
public class SupplierSettleController {

	@Autowired
	private SupplierSettleService supplierSettleService;
	
	@Resource
	private SupplierInfoMapper supplierInfoMapper;
	
	/**
	 * 列表页
	 * @return
	 */
	@RequestMapping("/supplierSettleMenu")
    public String supplierSettleMenu(Model model, SupplierSettle supplierSettle) {
			model.addAttribute("id", supplierSettle.getS_id());
        return "/commodity/supplierSettleMenu";
    }
	
	/**
	 * 添加页
	 * @return
	 */
	@RequestMapping("/addSupplierSettle")
    public String addSupplierAccountShipping(Model model,SupplierSettle supplierSettle) {
		model.addAttribute("id", supplierSettle.getS_id());
        return "/commodity/addSupplierSettle";
    }
	
	/**
	 * 编辑页
	 * @return
	 */
	@RequestMapping("/editSupplierSettle")
    public String editSupplierAccountShipping(Model model,String id,String s_id) {
		model.addAttribute("id", id);
		model.addAttribute("s_id", s_id);
        return "/commodity/editSupplierSettle";
    }
	
	
	/**
	 * 分页列表
	 * @param dataTableDTO
	 * @param
	 * @return
	 */
	@RequestMapping("/getSupplierSettleData")
    @ResponseBody
    public String getSupplierSettleData(DataTableDTO dataTableDTO,SupplierSettle supplierSettle) {
		SysUser user = UserUtil.getUserMessage();
		SupplierInfo supplierInfo = supplierInfoMapper.selectByUserId(String.valueOf(user.getId()));
		if(supplierInfo != null){
			supplierSettle.setS_id(supplierInfo.getS_id());
		}
        Page<SupplierSettle> list = supplierSettleService.getSupplierSettleData(dataTableDTO,supplierSettle);
        DataTableResult dataTableResult = new DataTableResult(dataTableDTO.getDraw(),list.getTotal(),list.getTotal(),list.getResult());
        return JSON.toJSONString(dataTableResult);
    }
	
	
	/**
	 * 无分页列表(根据登录人获取列表)
	 * @return
	 */
	@RequestMapping("/supplierSettleList")
    @ResponseBody
    public String shippingList(SupplierSettle supplierSettle) {
		SysUser user = UserUtil.getUserMessage();
		SupplierInfo supplierInfo = supplierInfoMapper.selectByUserId(String.valueOf(user.getId()));
		if(supplierInfo != null){
			supplierSettle.setS_id(supplierInfo.getS_id());
		}
        List<SupplierSettle> list = supplierSettleService.supplierSettleList(supplierSettle);
        return JSON.toJSONString(list);
    }
	
	
	/**
	 * 添加
	 * @param
	 * @return
	 */
	@RequestMapping(value="/addSupplierSettleDate")
    @ResponseBody
    public String addSupplierSettleDate(SupplierSettle supplierSettle) {
		DataTableResult dataTableResult = supplierSettleService.addSupplierSettleDate(supplierSettle);
        return JSON.toJSONString(dataTableResult);
    }
	
	/**
	 * 编辑
	 * @param
	 * @return
	 */
	@RequestMapping(value="/editSupplierSettleDate")
    @ResponseBody
    public String editSupplierSettleDate(SupplierSettle supplierSettle) {
		DataTableResult dataTableResult = supplierSettleService.editSupplierSettleDate(supplierSettle);
        return JSON.toJSONString(dataTableResult);
    }
	
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/delSupplierSettleDate")
    @ResponseBody
    public String delSupplierSettleDate(String id) {
		DataTableResult dataTableResult = supplierSettleService.deleteByPrimaryKey(id);
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
		SupplierSettle supplierSettle = supplierSettleService.selectById(id);
        return JSON.toJSONString(supplierSettle);
    }
}
