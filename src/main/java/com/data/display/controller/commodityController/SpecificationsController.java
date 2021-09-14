package com.data.display.controller.commodityController;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.data.display.model.commodity.Specifications;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.service.commodityService.SpecificationsService;
import com.github.pagehelper.Page;

/**
 * 规格
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/specifications")
public class SpecificationsController {

	
	@Autowired
	private SpecificationsService specificationsService;
	
	/**
	 * 列表页
	 * @return
	 */
	@RequestMapping("/specificationsMenu")
    public String specificationsMenu() {
        return "/commodity/specificationsMenu";
    }
	
	/**
	 * 新增页
	 * @return
	 */
	@RequestMapping("/addSpecifications")
    public String addClassIfication(Model model) {
        return "/commodity/addSpecifications";
    }
	
	@RequestMapping("/addClassBySpec")
    public String addClassBySpec(Model model) {
        return "/commodity/addClassBySpec";
    }
	
	
	@RequestMapping("/editSpecifications")
    public String editClassIfication(Model model,Specifications specifications) {
		model.addAttribute("id", specifications.getId());
        return "/commodity/editSpecifications";
    }
	
	
	@RequestMapping("/getData")
    @ResponseBody
    public String getData(DataTableDTO dataTableDTO,Specifications specifications) {
		Page<Specifications> list = specificationsService.getData(dataTableDTO,specifications);
		DataTableResult dataTableResult = new DataTableResult(dataTableDTO.getDraw(),list.getTotal(),list.getTotal(),list.getResult());
        return JSON.toJSONString(dataTableResult);
    }
	
	@RequestMapping("/addspecByClassData")
    @ResponseBody
    public String addspecByClassData(String cid,String sids) {
		DataTableResult dataTableResult = specificationsService.addspecByClassData(cid,sids);
        return JSON.toJSONString(dataTableResult);
    }

	/**
	 * 新增
	 * @param classIfication
	 * @return
	 */
	@RequestMapping("/addSpecificationsData")
    @ResponseBody
    public String addSpecificationsData(Specifications specifications) {
		DataTableResult dataTableResult = specificationsService.addSpecificationsData(specifications);
        return JSON.toJSONString(dataTableResult);
    }
	
	/**
	 * 根据第三级类目ID获取对应的规格
	 * @param cid
	 * @return
	 */
	@RequestMapping("/getAllSpecificationsByCid")
    @ResponseBody
    public String getAllSpecificationsByCid(String cid) {
        List<Specifications> list = specificationsService.getAllSpecificationsByCid(cid);
        return JSON.toJSONString(list);
    }
	

	@RequestMapping("/updateSpecifications")
    @ResponseBody
    public String updateSpecifications(Specifications specifications){
		DataTableResult dataTableResult = specificationsService.updateSpecifications(specifications);
		return JSON.toJSONString(dataTableResult);
    }
	
	/**
	 * 查询所有规格
	 * @param specifications
	 * @return
	 */
	@RequestMapping("/getSpecificationsData")
    @ResponseBody
    public String getSpecificationsData(Specifications specifications) {
        List<Specifications> list = specificationsService.getSpecificationsData(specifications);
        return JSON.toJSONString(list);
    }
	
	@RequestMapping("/selSpecName")
    @ResponseBody
    public String selSpecName() {
        List<Specifications> list = specificationsService.selSpecName();
        return JSON.toJSONString(list);
    }
	
	
	@RequestMapping("/selectById")
    @ResponseBody
    public String selectById(String id) {
		Specifications specifications = specificationsService.selectById(id);
        return JSON.toJSONString(specifications);
    }
	
}
