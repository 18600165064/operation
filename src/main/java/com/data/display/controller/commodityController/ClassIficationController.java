package com.data.display.controller.commodityController;

import java.awt.image.BufferedImage;
import java.util.List;

import com.data.display.util.OSSClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.data.display.model.commodity.ClassIfication;
import com.data.display.model.commodity.Product;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.service.commodityService.ClassIficationService;
import com.data.display.util.StringUtil;
import com.github.pagehelper.Page;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 分类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/classIfication")
public class ClassIficationController {

	@Autowired
	private ClassIficationService classIficationService;


	@RequestMapping(value="/upload",method= RequestMethod.POST)
	@ResponseBody
	public String upload(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile[] file) {
		DataTableResult result =  new DataTableResult();
		OSSClientUtil ossUtil = new OSSClientUtil();
		String images = "";
		try {
			for (MultipartFile myfile : file) {
				BufferedImage realImg = ImageIO.read(myfile.getInputStream());
				if (realImg== null) {//如果image=null 表示上传的不是图片格式
					result.setDraw(0);
					result.setError("图片错误");
					return JSON.toJSONString(result);
				}
					images = OSSClientUtil.SP_IMAGE_URL + ossUtil.uploadImg2Oss(myfile,OSSClientUtil.spImageFiledir);
			}
		} catch (Exception e) {
			result.setError("系统错误");
		}finally {
			ossUtil.destory();
		}
		result.setDraw(1);
		result.setData(images);
		return JSON.toJSONString(result);
	}


	/**
	 * 列表页
	 * @return
	 */
	@RequestMapping("/classIficationMenu")
    public String classIficationList() {
        return "/commodity/classIficationMenu";
    }
	
	@RequestMapping("/classIficationMenu2")
    public String classIficationList2() {
        return "/commodity/classIficationMenu2";
    }
	
	/**
	 * 新增页
	 * @return
	 */
	@RequestMapping("/addClassIfication")
    public String addClassIfication(Model model,String status) {
		model.addAttribute("status", status);
        return "/commodity/addClassIfication";
    }
	
	@RequestMapping("/editClassIfication")
    public String editClassIfication(Model model,ClassIfication classIfication) {
		model.addAttribute("id", classIfication.getId());
        return "/commodity/editClassIfication";
    }
	
	@RequestMapping("/selectById")
    @ResponseBody
    public String selectById(String id) {
		String id2 = id.replaceAll(",","");
		ClassIfication classIfication = classIficationService.selectById(id2);
        return JSON.toJSONString(classIfication);
    }
	
	@RequestMapping("/getData")
    @ResponseBody
    public String getData(DataTableDTO dataTableDTO,ClassIfication classIfication) {
		if(classIfication.getCate_level() == null){
			classIfication.setCate_level(1);
		}
		Page<ClassIfication> list = classIficationService.getData(dataTableDTO,classIfication);
		DataTableResult dataTableResult = new DataTableResult(dataTableDTO.getDraw(),list.getTotal(),list.getTotal(),list.getResult());
        return JSON.toJSONString(dataTableResult);
    }
	
	/**
	 * 根据级别及父级ID获取列表
	 * @param
	 * @param classIfication
	 * @return
	 */
	@RequestMapping("/getClassIficationData")
    @ResponseBody
    public String getClassIficationData(ClassIfication classIfication) {
        List<ClassIfication> list = classIficationService.getClassIficationData(classIfication);
        return JSON.toJSONString(list);
    }
	
	/**
	 * 新增
	 * @param classIfication
	 * @return
	 */
	@RequestMapping("/addClassIficationData")
    @ResponseBody
    public String addClassIficationData(ClassIfication classIfication,String sids) {
		DataTableResult dataTableResult = classIficationService.addClassIficationData(classIfication,sids);
        return JSON.toJSONString(dataTableResult);
    }
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteByPrimaryKey")
    @ResponseBody
    public String deleteByPrimaryKey(String id){
		DataTableResult dataTableResult = classIficationService.deleteByPrimaryKey(id);
		return JSON.toJSONString(dataTableResult);
    }
	
	
	@RequestMapping("/updateClass")
    @ResponseBody
    public String updateClass(ClassIfication classIfication){
		DataTableResult dataTableResult = classIficationService.updateClass(classIfication);
		return JSON.toJSONString(dataTableResult);
    }
	
	
}
