package com.data.display.controller.commodityController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.data.display.model.commodity.Shipping;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.service.commodityService.ShippingService;
import com.github.pagehelper.Page;




@Controller
@RequestMapping("/shipping")
public class ShippingController {

	@Autowired
	private ShippingService shippingService; 
	
	/**
	 * 列表页
	 * @return
	 */
	@RequestMapping("/shippingMenu")
    public String shippingList() {
        return "/commodity/shippingMenu";
    }
	
	/**
	 * 添加页
	 * @return
	 */
	@RequestMapping("/addShipping")
    public String addShipping() {
        return "/commodity/addShipping";
    }
	
	/**
	 * 编辑页
	 * @return
	 */
	@RequestMapping("/editShipping")
    public String editShipping() {
        return "/commodity/editShipping";
    }
	
	
	/**
	 * 分页列表
	 * @param dataTableDTO
	 * @param shipping
	 * @return
	 */
	@RequestMapping("/getShippingData")
    @ResponseBody
    public String getShippingData(DataTableDTO dataTableDTO,Shipping shipping) {
        Page<Shipping> list = shippingService.getShippingData(dataTableDTO,shipping);
        DataTableResult dataTableResult = new DataTableResult(dataTableDTO.getDraw(),list.getTotal(),list.getTotal(),list.getResult());
        return JSON.toJSONString(dataTableResult);
    }
	
	/**
	 * 添加
	 * @param shipping
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addShippingDate")
    @ResponseBody
    public String addShipping(Shipping shipping,HttpServletRequest request,HttpServletResponse response) {
		DataTableResult dataTableResult = shippingService.addShipping(shipping);
        return JSON.toJSONString(dataTableResult);
    }
	
	/**
	 * 编辑
	 * @param shipping
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editShippingData")
    @ResponseBody
    public String editShipping(Shipping shipping,HttpServletRequest request,HttpServletResponse response) {
		DataTableResult dataTableResult = shippingService.editShipping(shipping);
        return JSON.toJSONString(dataTableResult);
    }
	
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/delShipping")
    @ResponseBody
    public String delShipping(String id) {
		DataTableResult dataTableResult = shippingService.deleteByPrimaryKey(id);
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
		Shipping shipping = shippingService.selectById(id);
        return JSON.toJSONString(shipping);
    }
	
}
