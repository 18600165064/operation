package com.data.display.controller.commodityController;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.*;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.data.display.mapper.commodityMapper.SpuDescMapper;
import com.data.display.util.PhotoHandleUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSON;
import com.data.display.mapper.orderMapper.ExpressCompanyMapper;
import com.data.display.mapper.orderMapper.OrderMapper;
import com.data.display.mapper.supplierMapper.SupplierInfoMapper;
import com.data.display.model.commodity.SpuCommodity;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.supplier.SupplierInfo;
import com.data.display.model.user.SysUser;
import com.data.display.service.commodityService.SpuCommodityService;
import com.data.display.util.OSSClientUtil;
import com.data.display.util.StringUtil;
import com.data.display.util.UserUtil;
import com.github.pagehelper.Page;
import net.sf.json.JSONArray;

@Controller
@RequestMapping("/spuCommodity")
public class SpuCommodityController {

	 private static Logger _log = LoggerFactory.getLogger(SpuCommodityController.class);

	@Autowired
	private SpuCommodityService spuCommodityService;

	@Resource
	private SpuDescMapper spuDescMapper;

	 @Resource
	 private SupplierInfoMapper supplierInfoMapper;

	/**
	 * 服务商SPU
	 * @return
	 */
	@RequestMapping("/spuMenu")
    public String spuList(String a) {
        return "/commodity/spuMenu";
    }

	/**
	 * 供应商SPU
	 * @param a
	 * @return
	 */
	@RequestMapping("/supplierSpuMenu")
    public String supplierSpuMenu(String a) {
        return "/supplier/supplierSpuMenu";
    }

	/**
	 * SPU审核列表
	 * @param a
	 * @return
	 */
	@RequestMapping("/spuMenu2")
	public String spuList2(String a) {
		return "/commodity/spuMenu2";
	}

	/**
	 * 服务商查看
	 * @param
	 * @return
	 */
	@RequestMapping("/seecontent")
	public String seecontent(Model model,String id) {
        model.addAttribute("id", id);
	    return "/commodity/seecontent";
	}

	/**
	 * 服务商新增页
	 * @return
	 */
	@RequestMapping("/addSpuCommodity")
    public String addSpuCommodity() {
        return "/commodity/addSpuCommodity";
    }

	/**
	 * 供应商新增页
	 * @return
	 */
	@RequestMapping("/addSupplierSpu")
    public String addSupplierSpu() {
        return "/supplier/addSupplierSpu";
    }


	/**
	 * 服务商编辑页
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/editSpu")
    public String update(Model model,String id) {
		model.addAttribute("id", id);
		return "/commodity/editSpuCommodity";
    }

	@RequestMapping("/editSpu2")
    public String editSpu2(Model model,String id) {
		model.addAttribute("id", id);
		return "/commodity/editSpuCommodity2";
    }

	/**
	 * 供应商编辑
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/editSupplierSpu")
    public String editSupplierSpu(Model model,String id) {
		model.addAttribute("id", id);
		return "/supplier/editSupplierSpu";
    }

	/**
	 * SPU审核详情
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/examineSpu")
    public String examineSpu(Model model,String id) {
		model.addAttribute("id", id);
		return "/commodity/examineSpuCommodity";
    }


	@RequestMapping(value="/upload",method=RequestMethod.POST)
    @ResponseBody
    public String upload(HttpServletRequest request,HttpServletResponse response,@RequestParam("files")MultipartFile[] files) {
		DataTableResult result =  new DataTableResult();
		OSSClientUtil ossUtil = new OSSClientUtil();
		String images = "";
		int i = 0;
		try {
			for (MultipartFile myfile : files) {
				BufferedImage realImg = ImageIO.read(myfile.getInputStream());
				if (realImg != null) {//如果image=null 表示上传的不是图片格式
					if(realImg.getWidth() <= 748 || realImg.getWidth() >= 752 || realImg.getHeight() <= 748 || realImg.getHeight() >= 752){
						result.setDraw(0);
						result.setError("图片格式错误");
						return JSON.toJSONString(result);
					}
				}else{
					result.setDraw(0);
					result.setError("图片错误");
					return JSON.toJSONString(result);
				}
				if(i == files.length-1){
					images += OSSClientUtil.SP_IMAGE_URL + ossUtil.uploadImg2Oss(myfile,OSSClientUtil.spImageFiledir);
				}else{
					images += OSSClientUtil.SP_IMAGE_URL + ossUtil.uploadImg2Oss(myfile,OSSClientUtil.spImageFiledir) + ",";
				}
				i ++;
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

	@RequestMapping(value="/delOssPhoto",method=RequestMethod.POST)
    @ResponseBody
	public String delOssPhoto(String filePath){
		System.out.println(filePath);
		if (StringUtil.isNotBlank(filePath)){
			OSSClientUtil ossUtil = new OSSClientUtil();
			ossUtil.deleteFile(filePath);
			ossUtil.destory();
		}
		return "{}";
	}


	/**
	 * 修改
	 * @param spuCommodity
	 * @param request
	 * @param response
	 * @param image1
	 * @return
	 */
	@RequestMapping(value="/editSpuData",method=RequestMethod.POST)
    @ResponseBody
    public String editSpu(SpuCommodity spuCommodity,HttpServletRequest request,HttpServletResponse response,@RequestParam("image1")MultipartFile[] image1,@RequestParam("videoImage")MultipartFile[] videoImage,@RequestParam("videoUrl")MultipartFile[] videoUrl,@RequestParam("bannerImage")MultipartFile[] bannerImage,@RequestParam("successImage")MultipartFile[] successImage,@RequestParam("gifImage")MultipartFile[] gifImage) {
		OSSClientUtil ossUtil = new OSSClientUtil();
		DataTableResult result = new DataTableResult();
		try {
			int size = 1024 * 500;
			for (MultipartFile myfile : image1) {
				if(StringUtil.isNotBlank(myfile.getOriginalFilename())){
					BufferedImage realImg = ImageIO.read(myfile.getInputStream());
					if (realImg != null) {//如果image=null 表示上传的不是图片格式
						if(realImg.getWidth() >= 748 && realImg.getWidth() <= 752 && realImg.getHeight() >= 748 && realImg.getHeight() <= 752){
							if(myfile.getSize() > size){
								result.setError("大小错误");
								return JSON.toJSONString(result);
							}
							String image = ossUtil.uploadImg2Oss(myfile,OSSClientUtil.spImageFiledir);
							spuCommodity.setImage(OSSClientUtil.SP_IMAGE_URL+image);
						}else{
							result.setError("主图图片格式错误");
							return JSON.toJSONString(result);
						}
					}else{
						result.setError("主图图片错误");
						return JSON.toJSONString(result);
					}
				}
			}

			for (MultipartFile myfile : videoImage) {
				if(StringUtil.isNotBlank(myfile.getOriginalFilename())){
					BufferedImage realImg = ImageIO.read(myfile.getInputStream());
					if (realImg != null) {//如果image=null 表示上传的不是图片格式
						if(realImg.getWidth() >= 748 && realImg.getWidth() <= 752 && realImg.getHeight() >= 748 && realImg.getHeight() <= 752){
							if(myfile.getSize() > size){
								result.setError("大小错误");
								return JSON.toJSONString(result);
							}
							String image = ossUtil.uploadImg2Oss(myfile,OSSClientUtil.spImageFiledir);
							spuCommodity.setVideo_image(OSSClientUtil.SP_IMAGE_URL+image);
						}else{
							result.setError("视频封面图片格式错误");
							return JSON.toJSONString(result);
						}
					}else{
						result.setError("视频封面图片错误");
						return JSON.toJSONString(result);
					}
				}
			}


			for (MultipartFile myfile : videoUrl) {
				if(StringUtil.isNotBlank(myfile.getOriginalFilename())){
					int dot = myfile.getOriginalFilename().lastIndexOf('.');
		            if ((dot >-1) && (dot < (myfile.getOriginalFilename().length() - 1))) {
		                String name = myfile.getOriginalFilename().substring(dot + 1);
		                if(!name.equals("mp4")){
		                	result.setError("视频错误");
							return JSON.toJSONString(result);
		                }else{
		                	String image = ossUtil.uploadImg2Oss(myfile,OSSClientUtil.spVideoFiledir);
							spuCommodity.setVideo_url(OSSClientUtil.SP_VIDEO_URL+image);
		                }
		            }
				}
			}

			for (MultipartFile myfile : bannerImage) {
				if(StringUtil.isNotBlank(myfile.getOriginalFilename())){
					BufferedImage realImg = ImageIO.read(myfile.getInputStream());
					if (realImg != null) {//如果image=null 表示上传的不是图片格式
						if(realImg.getWidth() >= 688 && realImg.getWidth() <= 692 && realImg.getHeight() >= 386 && realImg.getHeight() <= 390){
							if(myfile.getSize() > size){
								result.setError("大小错误");
								return JSON.toJSONString(result);
							}
							String videoImg = ossUtil.uploadImg2Oss(myfile,OSSClientUtil.spImageFiledir);
							spuCommodity.setBanner_image(OSSClientUtil.SP_IMAGE_URL+videoImg);
						}else{
							result.setError("banner图片格式错误");
							return JSON.toJSONString(result);
						}
					}else{
						result.setError("banner图片错误");
						return JSON.toJSONString(result);
					}
				}
			}

			for (MultipartFile myfile : successImage) {
				if(StringUtil.isNotBlank(myfile.getOriginalFilename())){
					BufferedImage realImg = ImageIO.read(myfile.getInputStream());
					if (realImg != null) {//如果image=null 表示上传的不是图片格式
//						if(realImg.getWidth() >= 688 && realImg.getWidth() <= 692 && realImg.getHeight() >= 386 && realImg.getHeight() <= 390){
							if(myfile.getSize() > size){
								result.setError("大小错误");
								return JSON.toJSONString(result);
							}
							String videoImg = ossUtil.uploadImg2Oss(myfile,OSSClientUtil.spImageFiledir);
							spuCommodity.setSuccess_image(OSSClientUtil.SP_IMAGE_URL+videoImg);
//						}else{
//							result.setError("banner图片格式错误");
//							return JSON.toJSONString(result);
//						}
					}else{
						result.setError("banner图片错误");
						return JSON.toJSONString(result);
					}
				}
			}


			for (MultipartFile myfile : gifImage) {
				if(StringUtil.isNotBlank(myfile.getOriginalFilename())){
					BufferedImage realImg = ImageIO.read(myfile.getInputStream());
					if (realImg != null) {//如果image=null 表示上传的不是图片格式
						if(myfile.getSize() > size){
							result.setError("大小错误");
							return JSON.toJSONString(result);
						}
						String videoImg = ossUtil.uploadImg2Oss(myfile,OSSClientUtil.spImageFiledir);
						spuCommodity.setSuccess_gif_image(OSSClientUtil.SP_IMAGE_URL+videoImg);
					}else{
						result.setError("banner图片错误");
						return JSON.toJSONString(result);
					}
				}
			}

			//3人团佣金
//			BigDecimal threeCom = spuCommodity.getCommission_price_three();
//			BigDecimal minThree = new BigDecimal(3);
//			BigDecimal type = new BigDecimal(2);
//			//8人团佣金
//			BigDecimal eightCom = spuCommodity.getCommission_price_eight();
//			BigDecimal minEight = new BigDecimal(10);
//			//一级
//			BigDecimal oneMax = minEight.multiply(eightCom);
//			//二级
//			BigDecimal twoMax = minEight.multiply(minEight).multiply(eightCom.divide(type));
//			//三级
//			BigDecimal threeMax = minEight.multiply(minEight).multiply(minEight).multiply(eightCom.divide(type).divide(type));
//			//最低佣金
//			BigDecimal minCom = threeCom.multiply(minThree);
//			//最高佣金
//			BigDecimal MaxCom = oneMax.add(twoMax).add(threeMax);
//
//			String _interval = minCom.stripTrailingZeros().toPlainString() +"-"+ MaxCom.stripTrailingZeros().toPlainString();
//			spuCommodity.setInterval_value(_interval);
			spuCommodity.setInterval_value("18-2600");



			if (spuCommodity.getSpuDesc() != null){
				if(StringUtil.isNotBlank(spuCommodity.getSpuDesc().getSkudesc())){
					Thread thread = new Thread(new PhotoHandleUtil(spuCommodity,spuDescMapper,1,null,null,null,null));
					thread.start();
				}
			}

			BigDecimal refundPrice = BigDecimal.ZERO;
			spuCommodity.setRefund_price(refundPrice);
			result = spuCommodityService.editSpu(spuCommodity);
		} catch (Exception e) {
			System.out.println(e);
			result.setError("系统错误");
		}finally {
			ossUtil.destory();
		}
        return JSON.toJSONString(result);
    }


	@RequestMapping(value="/editOnSale")
    @ResponseBody
    public String editOnSale(SpuCommodity spuCommodity) {
		DataTableResult dataTableResult = spuCommodityService.editOnSale(spuCommodity);
        return JSON.toJSONString(dataTableResult);
    }


	/**
	 * 添加
	 * @param spuCommodity
	 * @param request
	 * @param response
	 * @param image1
	 * @return
	 */
	@RequestMapping(value="/addSpu",method=RequestMethod.POST)
    @ResponseBody
    public String addSpu(SpuCommodity spuCommodity,HttpServletRequest request,HttpServletResponse response,@RequestParam("image1")MultipartFile[] image1,@RequestParam("videoImage")MultipartFile[] videoImage,@RequestParam("video")MultipartFile[] video,@RequestParam("bannerImage")MultipartFile[] bannerImage,@RequestParam("successImage")MultipartFile[] successImage,@RequestParam("gifImage")MultipartFile[] gifImage) {
		OSSClientUtil ossUtil = new OSSClientUtil();
		DataTableResult result = new DataTableResult();
		try {
			int size = 1024 * 500;
			for (MultipartFile myfile : image1) {
				if(StringUtil.isNotBlank(myfile.getOriginalFilename())){
					BufferedImage realImg = ImageIO.read(myfile.getInputStream());
					if (realImg != null) {//如果image=null 表示上传的不是图片格式
						if(realImg.getWidth() >= 748 && realImg.getWidth() <= 752 && realImg.getHeight() >= 748 && realImg.getHeight() <= 752){
							if(myfile.getSize() > size){
								result.setError("大小错误");
								return JSON.toJSONString(result);
							}
							String image = ossUtil.uploadImg2Oss(myfile,OSSClientUtil.spImageFiledir);
							spuCommodity.setImage(OSSClientUtil.SP_IMAGE_URL+image);
						}else{
							result.setError("主图图片格式错误");
							return JSON.toJSONString(result);
						}
					}else{
						result.setError("主图图片错误");
						return JSON.toJSONString(result);
					}
				}
			}

			for (MultipartFile myfile : videoImage) {
				if(StringUtil.isNotBlank(myfile.getOriginalFilename())){
					BufferedImage realImg = ImageIO.read(myfile.getInputStream());
					if (realImg != null) {//如果image=null 表示上传的不是图片格式
						if(realImg.getWidth() >= 748 && realImg.getWidth() <= 752 && realImg.getHeight() >= 748 && realImg.getHeight() <= 752){
							if(myfile.getSize() > size){
								result.setError("大小错误");
								return JSON.toJSONString(result);
							}
							String image = ossUtil.uploadImg2Oss(myfile,OSSClientUtil.spImageFiledir);
							spuCommodity.setVideo_image(OSSClientUtil.SP_IMAGE_URL+image);
						}else{
							result.setError("视频封面图片格式错误");
							return JSON.toJSONString(result);
						}
					}else{
						result.setError("视频封面图片错误");
						return JSON.toJSONString(result);
					}
				}
			}


			for (MultipartFile myfile : video) {
				if(StringUtil.isNotBlank(myfile.getOriginalFilename())){
					int dot = myfile.getOriginalFilename().lastIndexOf('.');
		            if ((dot >-1) && (dot < (myfile.getOriginalFilename().length() - 1))) {
		                String name = myfile.getOriginalFilename().substring(dot + 1);
		                if(!name.equals("mp4")){
		                	result.setError("视频错误");
							return JSON.toJSONString(result);
		                }else{
		                	String image = ossUtil.uploadImg2Oss(myfile,OSSClientUtil.spVideoFiledir);
							spuCommodity.setVideo_url(OSSClientUtil.SP_VIDEO_URL+image);
		                }
		            }
				}
			}

			for (MultipartFile myfile : bannerImage) {
				if(StringUtil.isNotBlank(myfile.getOriginalFilename())){
					BufferedImage realImg = ImageIO.read(myfile.getInputStream());
					if (realImg != null) {//如果image=null 表示上传的不是图片格式
						if(realImg.getWidth() >= 688 && realImg.getWidth() <= 692 && realImg.getHeight() >= 386 && realImg.getHeight() <= 390){
							if(myfile.getSize() > size){
								result.setError("大小错误");
								return JSON.toJSONString(result);
							}
							String videoImg = ossUtil.uploadImg2Oss(myfile,OSSClientUtil.spImageFiledir);
							spuCommodity.setBanner_image(OSSClientUtil.SP_IMAGE_URL+videoImg);
						}else{
							result.setError("banner图片格式错误");
							return JSON.toJSONString(result);
						}
					}else{
						result.setError("banner图片错误");
						return JSON.toJSONString(result);
					}
				}
			}

			BigDecimal refundPrice = BigDecimal.ZERO;
			spuCommodity.setRefund_price(refundPrice);
			result = spuCommodityService.addSpu(spuCommodity);

		} catch (Exception e) {
			result.setError("系统错误");
		}finally {
			ossUtil.destory();
		}

        return JSON.toJSONString(result);
    }


	/**
	 * 供应商添加商品
	 * @param spuCommodity
	 * @param request
	 * @param response
	 * @param image1
	 * @param videoImage
	 * @param video
	 * @param bannerImage
	 * @return
	 */
	@RequestMapping(value="/addSupplierSpuData",method=RequestMethod.POST)
    @ResponseBody
    public String addSupplierSpuData(SpuCommodity spuCommodity,HttpServletRequest request,HttpServletResponse response,@RequestParam("image1")MultipartFile[] image1,@RequestParam("videoImage")MultipartFile[] videoImage,@RequestParam("video")MultipartFile[] video,@RequestParam("bannerImage")MultipartFile[] bannerImage,@RequestParam("successImage")MultipartFile[] successImage,@RequestParam("gifImage")MultipartFile[] gifImage) {
		OSSClientUtil ossUtil = new OSSClientUtil();
		DataTableResult result = new DataTableResult();
		try {
			int size = 1024 * 500;
			for (MultipartFile myfile : image1) {
				if(StringUtil.isNotBlank(myfile.getOriginalFilename())){
					BufferedImage realImg = ImageIO.read(myfile.getInputStream());
					if (realImg != null) {//如果image=null 表示上传的不是图片格式
						if(realImg.getWidth() >= 748 && realImg.getWidth() <= 752 && realImg.getHeight() >= 748 && realImg.getHeight() <= 752){
							if(myfile.getSize() > size){
								result.setError("大小错误");
								return JSON.toJSONString(result);
							}
							String image = ossUtil.uploadImg2Oss(myfile,OSSClientUtil.spImageFiledir);
							spuCommodity.setImage(OSSClientUtil.SP_IMAGE_URL+image);
						}else{
							result.setError("主图图片格式错误");
							return JSON.toJSONString(result);
						}
					}else{
						result.setError("主图图片错误");
						return JSON.toJSONString(result);
					}
				}
			}

			for (MultipartFile myfile : videoImage) {
				if(StringUtil.isNotBlank(myfile.getOriginalFilename())){
					BufferedImage realImg = ImageIO.read(myfile.getInputStream());
					if (realImg != null) {//如果image=null 表示上传的不是图片格式
						if(realImg.getWidth() >= 748 && realImg.getWidth() <= 752 && realImg.getHeight() >= 748 && realImg.getHeight() <= 752){
							if(myfile.getSize() > size){
								result.setError("大小错误");
								return JSON.toJSONString(result);
							}
							String image = ossUtil.uploadImg2Oss(myfile,OSSClientUtil.spImageFiledir);
							spuCommodity.setVideo_image(OSSClientUtil.SP_IMAGE_URL+image);
						}else{
							result.setError("视频封面图片格式错误");
							return JSON.toJSONString(result);
						}
					}else{
						result.setError("视频封面图片错误");
						return JSON.toJSONString(result);
					}
				}
			}


			for (MultipartFile myfile : video) {
				if(StringUtil.isNotBlank(myfile.getOriginalFilename())){
					int dot = myfile.getOriginalFilename().lastIndexOf('.');
		            if ((dot >-1) && (dot < (myfile.getOriginalFilename().length() - 1))) {
		                String name = myfile.getOriginalFilename().substring(dot + 1);
		                if(!name.equals("mp4")){
		                	result.setError("视频错误");
							return JSON.toJSONString(result);
		                }else{
		                	String image = ossUtil.uploadImg2Oss(myfile,OSSClientUtil.spVideoFiledir);
							spuCommodity.setVideo_url(OSSClientUtil.SP_VIDEO_URL+image);
		                }
		            }
				}
			}

			for (MultipartFile myfile : bannerImage) {
				if(StringUtil.isNotBlank(myfile.getOriginalFilename())){
					BufferedImage realImg = ImageIO.read(myfile.getInputStream());
					if (realImg != null) {//如果image=null 表示上传的不是图片格式
						if(realImg.getWidth() >= 688 && realImg.getWidth() <= 692 && realImg.getHeight() >= 386 && realImg.getHeight() <= 390){
							if(myfile.getSize() > size){
								result.setError("大小错误");
								return JSON.toJSONString(result);
							}
							String videoImg = ossUtil.uploadImg2Oss(myfile,OSSClientUtil.spImageFiledir);
							spuCommodity.setBanner_image(OSSClientUtil.SP_IMAGE_URL+videoImg);
						}else{
							result.setError("banner图片格式错误");
							return JSON.toJSONString(result);
						}
					}else{
						result.setError("banner图片错误");
						return JSON.toJSONString(result);
					}
				}
			}

			for (MultipartFile myfile : successImage) {
				if(StringUtil.isNotBlank(myfile.getOriginalFilename())){
					BufferedImage realImg = ImageIO.read(myfile.getInputStream());
					if (realImg != null) {//如果image=null 表示上传的不是图片格式
//						if(realImg.getWidth() >= 688 && realImg.getWidth() <= 692 && realImg.getHeight() >= 386 && realImg.getHeight() <= 390){
						if(myfile.getSize() > size){
							result.setError("大小错误");
							return JSON.toJSONString(result);
						}
						String successImg = ossUtil.uploadImg2Oss(myfile,OSSClientUtil.spImageFiledir);
						spuCommodity.setSuccess_image(OSSClientUtil.SP_IMAGE_URL+successImg);
//						}else{
//							result.setError("banner图片格式错误");
//							return JSON.toJSONString(result);
//						}
					}else{
						result.setError("banner图片错误");
						return JSON.toJSONString(result);
					}
				}
			}


			for (MultipartFile myfile : gifImage) {
				if(StringUtil.isNotBlank(myfile.getOriginalFilename())){
					BufferedImage realImg = ImageIO.read(myfile.getInputStream());
					if (realImg != null) {//如果image=null 表示上传的不是图片格式
						if(myfile.getSize() > size){
							result.setError("大小错误");
							return JSON.toJSONString(result);
						}
						String gifImg = ossUtil.uploadImg2Oss(myfile,OSSClientUtil.spImageFiledir);
						spuCommodity.setSuccess_gif_image(OSSClientUtil.SP_IMAGE_URL+gifImg);
					}else{
						result.setError("banner图片错误");
						return JSON.toJSONString(result);
					}
				}
			}


			if (spuCommodity.getSpuDesc() != null){
				if(StringUtil.isNotBlank(spuCommodity.getSpuDesc().getSkudesc())){
					Thread thread = new Thread(new PhotoHandleUtil(spuCommodity,spuDescMapper,1,null,null,null,null));
					thread.start();
				}
			}
			result = spuCommodityService.addSupplierSpuData(spuCommodity);

		} catch (Exception e) {
			result.setError("系统错误");
		}finally {
			ossUtil.destory();
		}

        return JSON.toJSONString(result);
    }


	@RequestMapping(value="/editSupplierSpuData",method=RequestMethod.POST)
    @ResponseBody
    public String editSupplierSpuData(SpuCommodity spuCommodity,HttpServletRequest request,HttpServletResponse response,@RequestParam("image1")MultipartFile[] image1,@RequestParam("videoImage")MultipartFile[] videoImage,@RequestParam("videoUrl")MultipartFile[] videoUrl,@RequestParam("bannerImage")MultipartFile[] bannerImage,@RequestParam("successImage")MultipartFile[] successImage,@RequestParam("gifImage")MultipartFile[] gifImage) {
		OSSClientUtil ossUtil = new OSSClientUtil();
		DataTableResult result = new DataTableResult();
		try {
			int size = 1024 * 500;
			for (MultipartFile myfile : image1) {
				if(StringUtil.isNotBlank(myfile.getOriginalFilename())){
					BufferedImage realImg = ImageIO.read(myfile.getInputStream());
					if (realImg != null) {//如果image=null 表示上传的不是图片格式
						if(realImg.getWidth() >= 748 && realImg.getWidth() <= 752 && realImg.getHeight() >= 748 && realImg.getHeight() <= 752){
							if(myfile.getSize() > size){
								result.setError("大小错误");
								return JSON.toJSONString(result);
							}
							String image = ossUtil.uploadImg2Oss(myfile,OSSClientUtil.spImageFiledir);
							spuCommodity.setImage(OSSClientUtil.SP_IMAGE_URL+image);
						}else{
							result.setError("主图图片格式错误");
							return JSON.toJSONString(result);
						}
					}else{
						result.setError("主图图片错误");
						return JSON.toJSONString(result);
					}
				}
			}

			for (MultipartFile myfile : videoImage) {
				if(StringUtil.isNotBlank(myfile.getOriginalFilename())){
					BufferedImage realImg = ImageIO.read(myfile.getInputStream());
					if (realImg != null) {//如果image=null 表示上传的不是图片格式
						if(realImg.getWidth() >= 748 && realImg.getWidth() <= 752 && realImg.getHeight() >= 748 && realImg.getHeight() <= 752){
							if(myfile.getSize() > size){
								result.setError("大小错误");
								return JSON.toJSONString(result);
							}
							String image = ossUtil.uploadImg2Oss(myfile,OSSClientUtil.spImageFiledir);
							spuCommodity.setVideo_image(OSSClientUtil.SP_IMAGE_URL+image);
						}else{
							result.setError("视频封面图片格式错误");
							return JSON.toJSONString(result);
						}
					}else{
						result.setError("视频封面图片错误");
						return JSON.toJSONString(result);
					}
				}
			}


			for (MultipartFile myfile : videoUrl) {
				if(StringUtil.isNotBlank(myfile.getOriginalFilename())){
					int dot = myfile.getOriginalFilename().lastIndexOf('.');
		            if ((dot >-1) && (dot < (myfile.getOriginalFilename().length() - 1))) {
		                String name = myfile.getOriginalFilename().substring(dot + 1);
		                if(!name.equals("mp4")){
		                	result.setError("视频错误");
							return JSON.toJSONString(result);
		                }else{
		                	String image = ossUtil.uploadImg2Oss(myfile,OSSClientUtil.spVideoFiledir);
							spuCommodity.setVideo_url(OSSClientUtil.SP_VIDEO_URL+image);
		                }
		            }
				}
			}

			for (MultipartFile myfile : bannerImage) {
				if(StringUtil.isNotBlank(myfile.getOriginalFilename())){
					BufferedImage realImg = ImageIO.read(myfile.getInputStream());
					if (realImg != null) {//如果image=null 表示上传的不是图片格式
						if(realImg.getWidth() >= 688 && realImg.getWidth() <= 692 && realImg.getHeight() >= 386 && realImg.getHeight() <= 390){
							if(myfile.getSize() > size){
								result.setError("大小错误");
								return JSON.toJSONString(result);
							}
							String videoImg = ossUtil.uploadImg2Oss(myfile,OSSClientUtil.spImageFiledir);
							spuCommodity.setBanner_image(OSSClientUtil.SP_IMAGE_URL+videoImg);
						}else{
							result.setError("banner图片格式错误");
							return JSON.toJSONString(result);
						}
					}else{
						result.setError("banner图片错误");
						return JSON.toJSONString(result);
					}
				}
			}

			result = spuCommodityService.editSupplierSpuData(spuCommodity);

		} catch (Exception e) {
			result.setError("系统错误");
		}finally {
			ossUtil.destory();
		}
        return JSON.toJSONString(result);
    }

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/delSpu")
    @ResponseBody
    public String delSpu(String id) {
		DataTableResult dataTableResult = spuCommodityService.deleteByPrimaryKey(id);
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
		List<Map<String,String>> list = new ArrayList<>();

		SpuCommodity spuCommodity = spuCommodityService.selectById(id);
		if(StringUtil.isNotBlank(spuCommodity.getImages())){
			JSONArray array = JSONArray.fromObject(spuCommodity.getImages());
			for (int i = 0; i < array.size(); i++) {
				Map<String,String> map = new HashMap<String, String>();
				String name = array.get(i).toString().split("data/")[1];
				map.put("caption", name);
				map.put("url", "/spuCommodity/delOssPhoto");
				map.put("key",String.valueOf(i+1));
				list.add(map);
			}
		}
		spuCommodity.setList(JSON.toJSONString(list));
        return JSON.toJSONString(spuCommodity);
    }

	/**
	 * spu列表
	 * @param dataTableDTO
	 * @param spuCommodity
	 * @return
	 */
	@RequestMapping("/getSpuData")
    @ResponseBody
    public String getSpuData(DataTableDTO dataTableDTO,SpuCommodity spuCommodity) {
		SysUser user = UserUtil.getUserMessage();
		SupplierInfo supplierInfo = supplierInfoMapper.selectByUserId(String.valueOf(user.getId()));
		if(supplierInfo != null){
			spuCommodity.setS_id(supplierInfo.getS_id());
		}
        Page<SpuCommodity> list = spuCommodityService.getSpuData(dataTableDTO,spuCommodity);
        DataTableResult dataTableResult = new DataTableResult(dataTableDTO.getDraw(),list.getTotal(),list.getTotal(),list.getResult());
        return JSON.toJSONString(dataTableResult);
    }

	/**
	 * 审核SPU列表
	 * @param dataTableDTO
	 * @param spuCommodity
	 * @return
	 */
	@RequestMapping("/getExamineSpuData")
    @ResponseBody
    public String getExamineSpuData(DataTableDTO dataTableDTO,SpuCommodity spuCommodity) {
		SysUser user = UserUtil.getUserMessage();
		SupplierInfo supplierInfo = supplierInfoMapper.selectByUserId(String.valueOf(user.getId()));
		if(supplierInfo != null){
			spuCommodity.setS_id(supplierInfo.getS_id());
		}
        Page<SpuCommodity> list = spuCommodityService.getExamineSpuData(dataTableDTO,spuCommodity);
        DataTableResult dataTableResult = new DataTableResult(dataTableDTO.getDraw(),list.getTotal(),list.getTotal(),list.getResult());
        return JSON.toJSONString(dataTableResult);
    }


	/**
	 * 维护
	 * @param spuCommodity
	 * @return
	 */
	@RequestMapping(value="/maintainSpu")
    @ResponseBody
    public String maintainSpu(SpuCommodity spuCommodity) {
		DataTableResult dataTableResult = spuCommodityService.maintainSpu(spuCommodity);
        return JSON.toJSONString(dataTableResult);
    }

}
