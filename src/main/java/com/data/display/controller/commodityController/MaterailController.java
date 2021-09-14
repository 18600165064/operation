package com.data.display.controller.commodityController;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSON;
import com.data.display.model.commodity.Materail;
import com.data.display.model.commodity.SpuCommodity;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.service.commodityService.MaterailService;
import com.data.display.service.commodityService.SpuCommodityService;
import com.data.display.util.OSSClientUtil;
import com.data.display.util.StringUtil;
import com.github.pagehelper.Page;

/**
 * 素材
 * @author l
 *
 */
@Controller
@RequestMapping("/materail")
public class MaterailController {

	@Autowired
	private MaterailService materailService;
	
	@Autowired
	private SpuCommodityService spuCommodityService; 
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)
    @ResponseBody
    public String upload(HttpServletRequest request,HttpServletResponse response,@RequestParam("files")MultipartFile[] files) {
		OSSClientUtil ossUtil = new OSSClientUtil();
		DataTableResult result =  new DataTableResult();
		String images = "";
		int i = 0;
		try {
			for (MultipartFile myfile : files) {
				BufferedImage realImg = ImageIO.read(myfile.getInputStream());
				if (realImg != null) {//如果image=null 表示上传的不是图片格式
					if(realImg.getWidth() <= 748 || realImg.getWidth() >= 752 || realImg.getHeight() <= 598 || realImg.getHeight() >= 752){
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
					images += OSSClientUtil.SC_IMAGE_URL + ossUtil.uploadImg2Oss(myfile,OSSClientUtil.scImageFiledir);
				}else{
					images += OSSClientUtil.SC_IMAGE_URL + ossUtil.uploadImg2Oss(myfile,OSSClientUtil.scImageFiledir) + ",";
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
	
	
	@RequestMapping("/addMaterail")
    public String addMaterail(Model model,Materail materail) {
		model.addAttribute("spuid", materail.getSpuid());
        return "/commodity/addMaterail";
    }
	
	@RequestMapping("/editMaterail")
    public String editMaterail(Model model,Materail materail) {
		model.addAttribute("id", materail.getId());
		model.addAttribute("pid", materail.getSpuid());
		materail = materailService.selectMaterailById(String.valueOf(materail.getId()));
		model.addAttribute("spuid", materail.getSpuid());
		return "/commodity/editMaterail";
    }
	
	@RequestMapping("/materailMenu")
    public String materailMenu(Model model,Materail materail) {
		String pid = materail.getSpuid().replaceAll(",","");
		SpuCommodity spuCommodity = spuCommodityService.selectById(pid);
		model.addAttribute("spuid", spuCommodity.getSpuid());
		model.addAttribute("pid", spuCommodity.getId());
        return "/commodity/materailMenu";
    }
	

	@RequestMapping(value="/selectMaterail")
    @ResponseBody
	public String selectMaterail(DataTableDTO dataTableDTO,Materail materail){
		Page<Materail> list = materailService.selectMaterail(dataTableDTO,materail);
		DataTableResult dataTableResult = new DataTableResult(dataTableDTO.getDraw(),list.getTotal(),list.getTotal(),list.getResult());
		return JSON.toJSONString(dataTableResult);
	}
	
	
	@RequestMapping(value="/selectMaterailById")
    @ResponseBody
	public String selectMaterailById(String id){
		id = id.replace(",", "");
		List<Map<String,String>> list = new ArrayList<>();
		Materail materail = materailService.selectMaterailById(id);
		String[] array = materail.getImages().split(",");
		for (int i = 0; i < array.length; i++) {
			Map<String,String> map = new HashMap<String, String>();
			String name = array[i].toString().split("data/")[1];
			map.put("caption", name);
			map.put("url", "/spuCommodity/delOssPhoto");
			map.put("key",String.valueOf(i+1));
			list.add(map);
		}
		materail.setList(JSON.toJSONString(list));
		return JSON.toJSONString(materail);
	}
	
	@RequestMapping("/deleteById")
    @ResponseBody
    public String deleteById(String id) {
		DataTableResult dataTableResult = materailService.deleteById(id);
        return JSON.toJSONString(dataTableResult);
    }
	
	
	@RequestMapping(value="/editMaterailData",method=RequestMethod.POST)
    @ResponseBody
    public String editMaterailData(Materail materail,HttpServletRequest request,HttpServletResponse response,@RequestParam("videoImage")MultipartFile[] videoImage,@RequestParam("video")MultipartFile[] video) {
		OSSClientUtil ossUtil = new OSSClientUtil();
		DataTableResult result = new DataTableResult();
		try {
			int size = 1024 * 500;
			for (MultipartFile myfile : videoImage) {
				if(StringUtil.isNotBlank(myfile.getOriginalFilename())){
					BufferedImage realImg = ImageIO.read(myfile.getInputStream());
					if (realImg != null) {//如果image=null 表示上传的不是图片格式
						if(realImg.getWidth() >= 748 && realImg.getWidth() <= 752 && realImg.getHeight() >= 748 && realImg.getHeight() <= 752){
							if(myfile.getSize() > size){
								result.setError("大小错误");
								return JSON.toJSONString(result);
							}
							String image = ossUtil.uploadImg2Oss(myfile,OSSClientUtil.scImageFiledir);
							materail.setVideo_image(OSSClientUtil.SC_IMAGE_URL+image);
						}else{
							result.setError("图片格式错误");
							return JSON.toJSONString(result);
						}
					}else{
						result.setError("图片错误");
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
		                	String image = ossUtil.uploadImg2Oss(myfile,OSSClientUtil.scVideoFiledir);
							materail.setVideo_url(OSSClientUtil.SC_VIDEO_URL+image);
		                }
		            }
				}
			}
			
			result = materailService.editMaterailData(materail);
		} catch (Exception e) {
			result.setError("系统错误");
		}finally {
			ossUtil.destory();
		}
        return JSON.toJSONString(result);
    }
	
	
	@RequestMapping(value="/addMaterailData",method=RequestMethod.POST)
    @ResponseBody
    public String addMaterailData(Materail materail,HttpServletRequest request,HttpServletResponse response,@RequestParam("videoImage")MultipartFile[] videoImage,@RequestParam("video")MultipartFile[] video) {
		OSSClientUtil ossUtil = new OSSClientUtil();
		DataTableResult result = new DataTableResult();
		try {
			int size = 1024 * 500;
			for (MultipartFile myfile : videoImage) {
				if(StringUtil.isNotBlank(myfile.getOriginalFilename())){
					BufferedImage realImg = ImageIO.read(myfile.getInputStream());
					if (realImg != null) {//如果image=null 表示上传的不是图片格式
						if(realImg.getWidth() >= 748 && realImg.getWidth() <= 752 && realImg.getHeight() >= 748 && realImg.getHeight() <= 752){
							if(myfile.getSize() > size){
								result.setError("大小错误");
								return JSON.toJSONString(result);
							}
							String videoImg = ossUtil.uploadImg2Oss(myfile,OSSClientUtil.scImageFiledir);
							materail.setVideo_image(OSSClientUtil.SC_IMAGE_URL+videoImg);
						}else{
							result.setError("图片格式错误");
							return JSON.toJSONString(result);
						}
					}else{
						result.setError("图片错误");
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
		                	String videoImg = ossUtil.uploadImg2Oss(myfile,OSSClientUtil.scVideoFiledir);
							materail.setVideo_url(OSSClientUtil.SC_VIDEO_URL+videoImg);
		                }
		            }
				}
			}
			
			result = materailService.addMaterailData(materail);
		} catch (Exception e) {
			result.setError("系统错误");
		}finally {
			ossUtil.destory();
		}
		
        return JSON.toJSONString(result);
    }

}
