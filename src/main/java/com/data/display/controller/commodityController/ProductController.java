
package com.data.display.controller.commodityController;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.display.mapper.commodityMapper.*;
import com.data.display.model.commodity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.data.display.mapper.supplierMapper.SupplierInfoMapper;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.supplier.SupplierInfo;
import com.data.display.model.user.SysUser;
import com.data.display.service.commodityService.ProductService;
import com.data.display.util.OSSClientUtil;
import com.data.display.util.StringUtil;
import com.data.display.util.UserUtil;
import com.github.pagehelper.Page;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



/**
 * SKU
 * @author l
 *
 */
@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Resource
    private ProductMapper productMapper;
	
	@Resource
	private SupplierAccountShippingMapper supplierAccountShippingMapper;

	@Resource
	private SupplierAccountShippingSettingsMapper supplierAccountShippingSettingsMapper;

	@Resource
	private SupplierSettleMapper supplierSettleMapper;

	@Resource
	private SupplierSettleSettingsMapper supplierSettleSettingsMapper;

	@Resource
	private SupplierInfoMapper supplierInfoMapper;
	
	/**
	 * 服务商sku列表页
	 * @return
	 */
	@RequestMapping("/skuMenu")
    public String skuMenu() {
        return "/commodity/skuMenu";
    }
	
	/**
	 * 供应商sku
	 * @return
	 */
	@RequestMapping("/supplierSkuMenu")
    public String supplierSpuMenu() {
        return "/supplier/supplierSkuMenu";
    }

	/**
	 * 服务商查看
	 * @param
	 * @return
	 */
	@RequestMapping("/seecontent")
	public String seecontent(Model model,String id) {
		model.addAttribute("id", id);
		return "/commodity/seecontentSku";
	}


	/**
	 * 审核
	 * @return
	 */
	@RequestMapping("/skuMenu2")
    public String skuMenu2() {
        return "/commodity/skuMenu2";
    }
	
	/**
	 * 服务商新增页
	 * @param model
	 * @param product
	 * @return
	 */
	@RequestMapping("/addProduct")
    public String addProduct(Model model,Product product) {
		model.addAttribute("spuid", product.getSpuid());
        return "/commodity/addSkuCommodity";
    }
	
	/**
	 * 服务商修改页
	 * @param model
	 * @param product
	 * @return
	 */
	@RequestMapping("/editSupplierSku")
    public String editSupplierSku(Model model,Product product) {
		model.addAttribute("id", product.getId());
        return "/supplier/editSupplierSku";
    }
	
	
	@RequestMapping("/editSku")
    public String editSku(Model model,Product product,String status) {
		model.addAttribute("id", product.getId());
		model.addAttribute("status", status);
        return "/commodity/editSkuCommodity";
    }
	
	/**
	 * 审核页
	 * @param model
	 * @param product
	 * @return
	 */
	@RequestMapping("/examineSku")
    public String examineSku(Model model,Product product) {
		model.addAttribute("id", product.getId());
        return "/commodity/examineSkuCommodity";
    }
	
	/**
	 * 获取数据
	 * @param dataTableDTO
	 * @param product
	 * @return
	 */
	@RequestMapping("/getSkuData")
    @ResponseBody
    public String getSkuData(DataTableDTO dataTableDTO,Product product) {
		SysUser user = UserUtil.getUserMessage();
		SupplierInfo supplierInfo = supplierInfoMapper.selectByUserId(String.valueOf(user.getId()));
		if(supplierInfo != null){
			product.setS_id(supplierInfo.getS_id());
		}
        Page<Product> list = productService.getSkuData(dataTableDTO,product);
        DataTableResult dataTableResult = new DataTableResult(dataTableDTO.getDraw(),list.getTotal(),list.getTotal(),list.getResult());
        return JSON.toJSONString(dataTableResult);
    }
	
	
	@RequestMapping("/getExamineSkuData")
    @ResponseBody
    public String getExamineSkuData(DataTableDTO dataTableDTO,Product product) {
		SysUser user = UserUtil.getUserMessage();
		SupplierInfo supplierInfo = supplierInfoMapper.selectByUserId(String.valueOf(user.getId()));
		if(supplierInfo != null){
			product.setS_id(supplierInfo.getS_id());
		}
        Page<Product> list = productService.getExamineSkuData(dataTableDTO,product);
        DataTableResult dataTableResult = new DataTableResult(dataTableDTO.getDraw(),list.getTotal(),list.getTotal(),list.getResult());
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
        Product product = productService.selectById(id);
        return JSON.toJSONString(product);
    }
	
	/**
	 * 新增
	 * @param product
	 * @param request
	 * @param response
	 * @param image1
	 * @param
	 * @return
	 */
	@RequestMapping(value="/addSku",method=RequestMethod.POST)
    @ResponseBody
    public String addSku(Product product,HttpServletRequest request,HttpServletResponse response,@RequestParam("image1")MultipartFile[] image1) {
		DataTableResult result = new DataTableResult(); 
			
			String[] sid = product.getSpec().split(",");
			List<Product> productList = productMapper.selectByOther(product);
			if(productList.size() > 0){
				for (int i = 0; i < productList.size(); i++) {
					JSONArray json = JSONArray.fromObject(productList.get(i).getSpec());
					int ss =  0;
					sign:
					for (int j = 0; j < json.size(); j++) {
						 JSONObject job = json.getJSONObject(j);
						 boolean flag = Arrays.asList(sid).contains(String.valueOf(job.get("id")));
						 if(!flag){
							 break sign;
						 }else{
							 ss ++;
						 }
					}
					
					System.out.println(ss);
					if(ss == json.size()){
						result.setError("所选规格已经存在");
						return JSON.toJSONString(result);
					}
				}
			}
		
		OSSClientUtil ossUtil = new OSSClientUtil();
		int size = 1024 * 500;
		for (MultipartFile myfile : image1) {
			if(StringUtil.isNotBlank(myfile.getOriginalFilename())){
				try {
					BufferedImage bufferedImage = ImageIO.read(myfile.getInputStream());
					if (bufferedImage != null) {//如果image=null 表示上传的不是图片格式
						if(bufferedImage.getWidth() >= 748 && bufferedImage.getWidth() <= 752 && bufferedImage.getHeight() >= 748 && bufferedImage.getHeight() <= 752){
							if(myfile.getSize() > size){
								result.setError("大小错误");
								return JSON.toJSONString(result);
							}
							String image = ossUtil.uploadImg2Oss(myfile,OSSClientUtil.spImageFiledir);
							product.setSku_image(OSSClientUtil.SP_IMAGE_URL+image);
						}else{
							result.setError("图片格式错误");
							return JSON.toJSONString(result);
						}
					}else{
						result.setError("图片错误");
						return JSON.toJSONString(result);
					}
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		}
		
		result = productService.addProduct(product);
		ossUtil.destory();
        return JSON.toJSONString(result);
    }

	/**
	 * 编辑
	 * @param product
	 * @param request
	 * @param response
	 * @param image1
	 * @param
	 * @return
	 */
	@RequestMapping(value="/editSku",method=RequestMethod.POST)
    @ResponseBody
    public String editSku(Product product,HttpServletRequest request,HttpServletResponse response,@RequestParam("image1")MultipartFile[] image1) {
		DataTableResult result = new DataTableResult(); 
		//模板首费/首单数   >  结算首费/结算首单数  &&  模板首费  > 结算首费
		//模板续费/续件数   >  结算续费/结算续件数  &&  模板续费  > 结算续费
		//运费模板
		SupplierAccountShipping supplierAccountShipping = supplierAccountShippingMapper.selectById(product.getShipping_id());
		SupplierSettle supplierSettle = supplierSettleMapper.selectById(product.getSettle_id());
		//特殊区域数值
		BigDecimal firstPriceSettle = BigDecimal.ZERO;
		BigDecimal firstTagSettle = BigDecimal.ZERO;
		BigDecimal secondPriceSettle = BigDecimal.ZERO;
		BigDecimal secondTagSettle = BigDecimal.ZERO;
		//特殊区域数值
		//默认区域数值
		BigDecimal subDefaultPrice = BigDecimal.ZERO;
		BigDecimal subIncreaPrice = BigDecimal.ZERO;
		BigDecimal subDefaultAmount = BigDecimal.ZERO;
		BigDecimal subIncreaAmount = BigDecimal.ZERO;
		//默认区域数值
		if (supplierSettle != null){

			//结算首费
			subDefaultPrice = supplierSettle.getDefault_price();
			//结算首单数
			BigDecimal subDefaultNumber = new BigDecimal(supplierSettle.getDefault_number());
			//结算续费
			subIncreaPrice = supplierSettle.getIncrea_price();
			//结算续件数
			BigDecimal subIncreaNumber = new BigDecimal(supplierSettle.getIncrea_number());
			//结算首费/首单数
			subDefaultAmount = subDefaultPrice.divide(subDefaultNumber,2,BigDecimal.ROUND_FLOOR);
			//结算续费/续件数
			subIncreaAmount = subIncreaPrice.divide(subIncreaNumber,2,BigDecimal.ROUND_FLOOR);


			//获取结算运费模板中特殊区域比值最大的一条
			SupplierSettleSettings supplierSettleSettings = new SupplierSettleSettings();
			supplierSettleSettings.setShipping_id(supplierSettle.getSas_id());
			List<SupplierSettleSettings> settleList = supplierSettleSettingsMapper.getSupplierSettleSettingsData(supplierSettleSettings);

			for (int i = 0; i < settleList.size(); i++) {
				if (i == 0){
					firstPriceSettle = settleList.get(0).getFirst_price();
					firstTagSettle = new BigDecimal(settleList.get(0).getFirst_tag());
					secondPriceSettle = settleList.get(0).getSecond_price();
					secondTagSettle = new BigDecimal(settleList.get(0).getSecond_tag());
				}

				BigDecimal firstPriceSettle2 = settleList.get(i).getFirst_price();
				BigDecimal firstTagSettle2 = new BigDecimal(settleList.get(i).getFirst_tag());
				BigDecimal secondPriceSettle2 = settleList.get(i).getSecond_price();
				BigDecimal secondTagSettle2 = new BigDecimal(settleList.get(i).getSecond_tag());

				BigDecimal minFirstPriceSettle = firstPriceSettle.divide(firstTagSettle,2,BigDecimal.ROUND_FLOOR);
				BigDecimal minFirstPriceSettle2 = firstPriceSettle2.divide(firstTagSettle2,2,BigDecimal.ROUND_FLOOR);
				BigDecimal minSecondPriceSettle = secondPriceSettle.divide(secondTagSettle,2,BigDecimal.ROUND_FLOOR);
				BigDecimal minSecondPriceSettle2 = secondPriceSettle2.divide(secondTagSettle2,2,BigDecimal.ROUND_FLOOR);

				if (minFirstPriceSettle2.compareTo(minFirstPriceSettle) == 1 && minSecondPriceSettle2.compareTo(minSecondPriceSettle) == 1){
					firstPriceSettle = settleList.get(i).getFirst_price();
					firstTagSettle = new BigDecimal(settleList.get(i).getFirst_tag());
					secondPriceSettle = settleList.get(i).getSecond_price();
					secondTagSettle = new BigDecimal(settleList.get(i).getSecond_tag());
				}

			}
		}

		//特殊区域数值
		BigDecimal firstPrice = BigDecimal.ZERO;
		BigDecimal firstTag = BigDecimal.ZERO;
		BigDecimal secondPrice = BigDecimal.ZERO;
		BigDecimal secondTag = BigDecimal.ZERO;
		//特殊区域数值
		//默认区域数值
		BigDecimal defaultPrice = BigDecimal.ZERO;
		BigDecimal increaPrice = BigDecimal.ZERO;
		BigDecimal defaultAmount = BigDecimal.ZERO;
		BigDecimal increaAmount = BigDecimal.ZERO;
		//默认区域数值
		if(supplierAccountShipping != null){

			//模板首费
			defaultPrice = supplierAccountShipping.getDefault_price();
			//模板首单数
			BigDecimal defaultNumber = new BigDecimal(supplierAccountShipping.getDefault_number());
			//模板续费
			increaPrice = supplierAccountShipping.getIncrea_price();
			//模板续件数
			BigDecimal increaNumber = new BigDecimal(supplierAccountShipping.getIncrea_number());
			//模板首费/首单数
			defaultAmount = defaultPrice.divide(defaultNumber,2,BigDecimal.ROUND_FLOOR);
			//模板续费/续件数
			increaAmount = increaPrice.divide(increaNumber,2,BigDecimal.ROUND_FLOOR);


			//获取运费模板中特殊区域比值最小的一条
			SupplierAccountShippingSettings supplierAccountShippingSettings = new SupplierAccountShippingSettings();
			supplierAccountShippingSettings.setShipping_id(supplierAccountShipping.getSas_id());
			List<SupplierAccountShippingSettings> settingsList = supplierAccountShippingSettingsMapper.getSupplierAccountShippingSettingsData(supplierAccountShippingSettings);

			for (int i = 0; i < settingsList.size(); i++) {
				if (i == 0){
					firstPrice = settingsList.get(0).getFirst_price();
					firstTag = new BigDecimal(settingsList.get(0).getFirst_tag());
					secondPrice = settingsList.get(0).getSecond_price();
					secondTag = new BigDecimal(settingsList.get(0).getSecond_tag());
				}

				BigDecimal firstPrice2 = settingsList.get(i).getFirst_price();
				BigDecimal firstTag2 = new BigDecimal(settingsList.get(i).getFirst_tag());
				BigDecimal secondPrice2 = settingsList.get(i).getSecond_price();
				BigDecimal secondTag2 = new BigDecimal(settingsList.get(i).getSecond_tag());

				BigDecimal minFirstPrice = firstPrice.divide(firstTag,2,BigDecimal.ROUND_FLOOR);
				BigDecimal minFirstPrice2 = firstPrice2.divide(firstTag2,2,BigDecimal.ROUND_FLOOR);
				BigDecimal minSecondPrice = firstPrice.divide(firstTag,2,BigDecimal.ROUND_FLOOR);
				BigDecimal minSecondPrice2 = firstPrice2.divide(firstTag2,2,BigDecimal.ROUND_FLOOR);
				if (minFirstPrice2.compareTo(minFirstPrice) == -1 && minSecondPrice2.compareTo(minSecondPrice) == -1){
					firstPrice = settingsList.get(i).getFirst_price();
					firstTag = new BigDecimal(settingsList.get(i).getFirst_tag());
					secondPrice = settingsList.get(i).getSecond_price();
					secondTag = new BigDecimal(settingsList.get(i).getSecond_tag());
				}

			}

//			//模板首费
//			BigDecimal defaultPrice = supplierAccountShipping.getDefault_price();
//			//模板首单数
//			BigDecimal defaultNumber = new BigDecimal(supplierAccountShipping.getDefault_number());
//			//模板续费
//			BigDecimal increaPrice = supplierAccountShipping.getIncrea_price();
//			//模板续件数
//			BigDecimal increaNumber = new BigDecimal(supplierAccountShipping.getIncrea_number());
//			//模板首费/首单数
//			BigDecimal defaultAmount = defaultPrice.divide(defaultNumber,2,BigDecimal.ROUND_FLOOR);
//			//模板续费/续件数
//			BigDecimal increaAmount = increaPrice.divide(increaNumber,2,BigDecimal.ROUND_FLOOR);
//
//			//结算首费
//			BigDecimal subDefaultPrice = product.getDefault_price();
//			//结算首单数
//			BigDecimal subDefaultNumber = new BigDecimal(product.getDefault_number());
//			//结算续费
//			BigDecimal subIncreaPrice = product.getSub_dis_fee();
//			//结算续件数
//			BigDecimal subIncreaNumber = new BigDecimal(product.getSub_dis_value());
//			//结算首费/首单数
//			BigDecimal subDefaultAmount = subDefaultPrice.divide(subDefaultNumber,2,BigDecimal.ROUND_FLOOR);
//			//结算续费/续件数
//			BigDecimal subIncreaAmount = subIncreaPrice.divide(subIncreaNumber,2,BigDecimal.ROUND_FLOOR);
//
//			//模板首费  < 结算首费 || 模板续费 < 结算续费
//			if(defaultPrice.compareTo(subDefaultPrice) == -1 || increaPrice.compareTo(subIncreaPrice) == -1){
//				result.setError("运费差额设置错误");
//				return JSON.toJSONString(result);
//			}else{
//				//模板首费/首单数   <  结算首费/结算首单数 || 模板续费/续件数   <  结算续费/结算续件数
//				if(defaultAmount.compareTo(subDefaultAmount) == -1 || increaAmount.compareTo(subIncreaAmount) == -1){
//					result.setError("运费差额设置错误");
//					return JSON.toJSONString(result);
//				}
//			}
		}

		//模板首费  < 结算首费 || 模板续费 < 结算续费
		if(defaultPrice.compareTo(subDefaultPrice) == -1 || increaPrice.compareTo(subIncreaPrice) == -1){
			result.setError("运费差额设置错误");
			return JSON.toJSONString(result);
		}else{
			//模板首费/首单数   <  结算首费/结算首单数 || 模板续费/续件数   <  结算续费/结算续件数
			if(defaultAmount.compareTo(subDefaultAmount) == -1 || increaAmount.compareTo(subIncreaAmount) == -1){
				result.setError("运费差额设置错误");
				return JSON.toJSONString(result);
			}
		}


		//运费模板的特殊区域 首费/首单 > 结算模板的特殊区域  首费/首单  运费模板的特殊区域 续费/续单 > 结算模板的特殊区域  续费/续单
		if (firstTag.compareTo(BigDecimal.ZERO) != 0 && secondTag.compareTo(BigDecimal.ZERO) != 0 && firstTagSettle.compareTo(BigDecimal.ZERO) != 0 && secondTagSettle.compareTo(BigDecimal.ZERO) != 0){
			BigDecimal specialValue = firstPrice.divide(firstTag,2,BigDecimal.ROUND_FLOOR);
			BigDecimal specialValue2 = secondPrice.divide(secondTag,2,BigDecimal.ROUND_FLOOR);
			BigDecimal specialSettleValue = firstPriceSettle.divide(firstTagSettle,2,BigDecimal.ROUND_FLOOR);
			BigDecimal specialSettleValue2 = secondPriceSettle.divide(secondTagSettle,2,BigDecimal.ROUND_FLOOR);
			if (specialValue.compareTo(specialSettleValue) == -1 || specialValue2.compareTo(specialSettleValue2) == -1 ||
					defaultAmount.compareTo(specialSettleValue) == -1 || increaAmount.compareTo(specialSettleValue2) == -1){
				result.setError("结算运费模板设置错误");
				return JSON.toJSONString(result);
			}
		}else{
			//运费模板的特殊区域存在
			if (firstTag.compareTo(BigDecimal.ZERO) != 0 && secondTag.compareTo(BigDecimal.ZERO) != 0){
				BigDecimal specialValue = firstPrice.divide(firstTag,2,BigDecimal.ROUND_FLOOR);
				BigDecimal specialValue2 = secondPrice.divide(secondTag,2,BigDecimal.ROUND_FLOOR);
				if (specialValue.compareTo(subDefaultAmount) == -1 || specialValue2.compareTo(subIncreaAmount) == -1){
					result.setError("结算运费模板设置错误");
					return JSON.toJSONString(result);
				}
			}

			//结算模板的特殊区域存在
			if (firstTagSettle.compareTo(BigDecimal.ZERO) != 0 && secondTagSettle.compareTo(BigDecimal.ZERO) != 0){
				BigDecimal specialSettleValue = firstPriceSettle.divide(firstTagSettle,2,BigDecimal.ROUND_FLOOR);
				BigDecimal specialSettleValue2 = secondPriceSettle.divide(secondTagSettle,2,BigDecimal.ROUND_FLOOR);
				if (defaultAmount.compareTo(specialSettleValue) == -1 || increaAmount.compareTo(specialSettleValue2) == -1){
					result.setError("结算运费模板设置错误");
					return JSON.toJSONString(result);
				}
			}
		}

		OSSClientUtil ossUtil = new OSSClientUtil();
		int size = 1024 * 500;
 		for (MultipartFile myfile : image1) {
			if(StringUtil.isNotBlank(myfile.getOriginalFilename())){
				try {
					BufferedImage bufferedImage = ImageIO.read(myfile.getInputStream());
					if (bufferedImage != null) {//如果image=null 表示上传的不是图片格式
						if(bufferedImage.getWidth() >= 748 && bufferedImage.getWidth() <= 752 && bufferedImage.getHeight() >= 748 && bufferedImage.getHeight() <= 752){
							if(myfile.getSize() > size){
								result.setError("大小错误");
								return JSON.toJSONString(result);
							}
							String image = ossUtil.uploadImg2Oss(myfile,OSSClientUtil.spImageFiledir);
							product.setSku_image(OSSClientUtil.SP_IMAGE_URL+image);
						}else{
							result.setError("图片格式错误");
							return JSON.toJSONString(result);
						}
					}else{
						result.setError("图片错误");
						return JSON.toJSONString(result);
					}
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		}
		
		DataTableResult dataTableResult = productService.editSku(product);
		ossUtil.destory();
        return JSON.toJSONString(dataTableResult);
    }
	
	/**
	 * 供应商编辑SKU
	 * @param product
	 * @param request
	 * @param response
	 * @param image1
	 * @return
	 */
	@RequestMapping(value="/editSupplierSku",method=RequestMethod.POST)
    @ResponseBody
    public String editSupplierSku(Product product,HttpServletRequest request,HttpServletResponse response,@RequestParam("image1")MultipartFile[] image1) {
		DataTableResult result = new DataTableResult(); 
		OSSClientUtil ossUtil = new OSSClientUtil();
		int size = 1024 * 500;
		for (MultipartFile myfile : image1) {
			if(StringUtil.isNotBlank(myfile.getOriginalFilename())){
				try {
					BufferedImage bufferedImage = ImageIO.read(myfile.getInputStream());
					if (bufferedImage != null) {//如果image=null 表示上传的不是图片格式
						if(bufferedImage.getWidth() >= 748 && bufferedImage.getWidth() <= 752 && bufferedImage.getHeight() >= 748 && bufferedImage.getHeight() <= 752){
							if(myfile.getSize() > size){
								result.setError("大小错误");
								return JSON.toJSONString(result);
							}
							String image = ossUtil.uploadImg2Oss(myfile,OSSClientUtil.spImageFiledir);
							product.setSku_image(OSSClientUtil.SP_IMAGE_URL+image);
						}else{
							result.setError("图片格式错误");
							return JSON.toJSONString(result);
						}
					}else{
						result.setError("图片错误");
						return JSON.toJSONString(result);
					}
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		}
		
		DataTableResult dataTableResult = productService.editSku(product);
		ossUtil.destory();
        return JSON.toJSONString(dataTableResult);
    }
	
	
	@RequestMapping(value="/editOnSale")
    @ResponseBody
    public String editOnSale(Product product) {
		DataTableResult dataTableResult = productService.editOnSale(product);
        return JSON.toJSONString(dataTableResult);
    }
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteById")
    @ResponseBody
    public String deleteById(String id) {
		DataTableResult dataTableResult = productService.deleteById(id);
        return JSON.toJSONString(dataTableResult);
    }

	public static void main(String[] args) {
		BigDecimal a = new BigDecimal(3);
		BigDecimal b = new BigDecimal(4);
		if (a.compareTo(b) == -1){
			System.out.println("123");
		}else{
			System.out.println("456");
		}

	}


}
