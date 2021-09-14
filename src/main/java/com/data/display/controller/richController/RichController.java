package com.data.display.controller.richController;

import java.awt.image.BufferedImage;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
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
import com.data.display.mapper.commodityMapper.SpuCommodityMapper;
import com.data.display.model.commodity.SpuCommodity;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.rich.Rich;
import com.data.display.service.richService.RichService;
import com.data.display.util.OSSClientUtil;
import com.data.display.util.StringUtil;
import com.github.pagehelper.Page;

@Controller
@RequestMapping("/rich")
public class RichController {

	private static Logger _log = LoggerFactory.getLogger(RichController.class);

	@Autowired
	private RichService richService;
	
	@Resource
	private SpuCommodityMapper spuCommodityMapper;
	
	
	@RequestMapping("/richMenu")
    public String richMenu() {
        return "/rich/richMenu";
    }
	
	@RequestMapping("/addRich")
    public String addRich() {
        return "/rich/addRich";
    }
	
	
	@RequestMapping("/editRich")
    public String editRich(Model model,Rich rich) {
		model.addAttribute("id", rich.getId());
        return "/rich/editRich";
    }
	
	
	@RequestMapping(value="/editRichData",method=RequestMethod.POST)
    @ResponseBody
    public String editRichData(Rich rich,@RequestParam("image1")MultipartFile[] image1,@RequestParam("video")MultipartFile[] video) {
		DataTableResult result =  new DataTableResult();
		OSSClientUtil ossUtil = new OSSClientUtil();
		try {
			if(rich.getType()==3 || rich.getType()==12 || rich.getType()==9 || rich.getType()==13){
				if(StringUtil.isBlank(rich.getSpuid())){
					result.setError("请先填写SPU编号");
					return JSON.toJSONString(result);
				}else{
					SpuCommodity spuCommodity = spuCommodityMapper.selectBySpuid(rich.getSpuid());
					if(spuCommodity == null){
						result.setError("SPU编号错误，请先填写正确的编号");
						return JSON.toJSONString(result);
					}else{

						Rich rich2 = new Rich();
						rich2.setType(rich.getType());
						rich2.setSpuid(rich.getSpuid());
						List<Rich> list = richService.selectRichByOthers(rich2);
						if(list.size() > 1){
							result.setError("该spu下消息已经存在了");
							return JSON.toJSONString(result);
						}

						if (rich.getType()==3 || rich.getType()==12){
							if(spuCommodity.getOn_sale() == 1){
								result.setError("SPU是下架状态，请先上架");
								return JSON.toJSONString(result);
							}if (spuCommodity.getMaintain_status() != 0){
								result.setError("SPU是暂处于维护，请进行处理");
								return JSON.toJSONString(result);
							}
						}

					}
				}
			}
			
				int size = 1024 * 500;
				for (MultipartFile myfile : image1) {
					if(StringUtil.isNotBlank(myfile.getOriginalFilename())){
						BufferedImage realImg = ImageIO.read(myfile.getInputStream());
						if(rich.getType() == 5 || rich.getType() == 6 || rich.getType() == 7 || rich.getType()==14){
							if (realImg != null) {//如果image=null 表示上传的不是图片格式
								if(realImg.getWidth() >= 688 && realImg.getWidth() <= 692 && realImg.getHeight() >= 388 && realImg.getHeight() <= 392){
									if(myfile.getSize() > size){
										result.setError("大小错误");
										return JSON.toJSONString(result);
									}
									String image = ossUtil.uploadImg2Oss(myfile,OSSClientUtil.scImageFiledir);
									rich.setImage(OSSClientUtil.SC_IMAGE_URL+image);
								}else{
									result.setError("图片格式错误");
									return JSON.toJSONString(result);
								}
							}else{
								result.setError("图片错误");
								return JSON.toJSONString(result);
							}
						}else if (rich.getType() == 11 || rich.getType() == 3 || rich.getType() == 12){
							if (realImg != null) {//如果image=null 表示上传的不是图片格式
								if(myfile.getSize() > size){
									result.setError("大小错误");
									return JSON.toJSONString(result);
								}
								String image = ossUtil.uploadImg2Oss(myfile,OSSClientUtil.scImageFiledir);
								rich.setImage(OSSClientUtil.SC_IMAGE_URL+image);
							}else{
								result.setError("图片错误");
								return JSON.toJSONString(result);
							}
						}else{
							if (realImg != null) {//如果image=null 表示上传的不是图片格式
								if(realImg.getWidth() >= 748 && realImg.getWidth() <= 752 && realImg.getHeight() >= 748 && realImg.getHeight() <= 752){
									if(myfile.getSize() > size){
										result.setError("大小错误");
										return JSON.toJSONString(result);
									}
									String image = ossUtil.uploadImg2Oss(myfile,OSSClientUtil.scImageFiledir);
									rich.setImage(OSSClientUtil.SC_IMAGE_URL+image);
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
								rich.setVideo_url(OSSClientUtil.SC_VIDEO_URL+image);
			                }
			            } 
					}
				}

				if (rich.getType()==3 || rich.getType()==6 || rich.getType()==7 || rich.getType()==11 || rich.getType()==12 || rich.getType()==14){
					if (StringUtil.isBlank(rich.getImage())){
						result.setError("请上传图片");
						return JSON.toJSONString(result);
					}
				}

				result = richService.editRichData(rich);
		} catch (Exception e) {
			_log.debug("系统错误"+e);
			result.setError("系统错误");
		}finally {
			ossUtil.destory();
		}
        return JSON.toJSONString(result);
    }
	
	
	@RequestMapping(value="/addRichData",method=RequestMethod.POST)
    @ResponseBody
    public String addRichData(Rich rich,@RequestParam("image1")MultipartFile[] image1,@RequestParam("video")MultipartFile[] video) {
		DataTableResult result =  new DataTableResult();
		OSSClientUtil ossUtil = new OSSClientUtil();
		Rich rich2 = new Rich();
		try {
			if(rich.getType()==2 || rich.getType()==3 || rich.getType()==12 || rich.getType()==11 || rich.getType()==14 || rich.getType()==9 || rich.getType()==13){
				rich2.setType(rich.getType());
				if (rich.getType()==3 || rich.getType()==12 || rich.getType()==9 || rich.getType()==13){
					if(StringUtil.isBlank(rich.getSpuid())){
						result.setError("请先填写SPU编号");
						return JSON.toJSONString(result);
					}else{
						rich2.setSpuid(rich.getSpuid());
					}
				}
				List<Rich> list = richService.selectRichByOthers(rich2);
				if(list.size() >= 1){
					result.setError("该类型消息已经存在了");
					return JSON.toJSONString(result);
				}
			}
			if(rich.getType()==3 || rich.getType()==12){
				if(StringUtil.isBlank(rich.getSpuid())){
					result.setError("请先填写SPU编号");
					return JSON.toJSONString(result);
				}else{
					SpuCommodity spuCommodity = spuCommodityMapper.selectBySpuid(rich.getSpuid());
					if(spuCommodity == null){
						result.setError("SPU编号错误，请先填写正确的编号");
						return JSON.toJSONString(result);
					}else{
						if(spuCommodity.getOn_sale() == 1){
							result.setError("SPU是下架状态，请先上架");
							return JSON.toJSONString(result);
						}
					}
				}
			}
				int size = 1024 * 500;
				for (MultipartFile myfile : image1) {
					if(StringUtil.isNotBlank(myfile.getOriginalFilename())){
						BufferedImage realImg = ImageIO.read(myfile.getInputStream());
						if(rich.getType() == 5 || rich.getType() == 6 || rich.getType() == 7 || rich.getType() == 14){
							if (realImg != null) {//如果image=null 表示上传的不是图片格式
								if(realImg.getWidth() >= 688 && realImg.getWidth() <= 692 && realImg.getHeight() >= 388 && realImg.getHeight() <= 392){
									if(myfile.getSize() > size){
										result.setError("大小错误");
										return JSON.toJSONString(result);
									}
									String image = ossUtil.uploadImg2Oss(myfile,OSSClientUtil.scImageFiledir);
									rich.setImage(OSSClientUtil.SC_IMAGE_URL+image);
								}else{
									result.setError("图片格式错误");
									return JSON.toJSONString(result);
								}
							}else{
								result.setError("图片错误");
								return JSON.toJSONString(result);
							}
						}else if (rich.getType() == 11 || rich.getType() == 3 || rich.getType() == 12){
							if (realImg != null) {//如果image=null 表示上传的不是图片格式
									if(myfile.getSize() > size){
										result.setError("大小错误");
										return JSON.toJSONString(result);
									}
									String image = ossUtil.uploadImg2Oss(myfile,OSSClientUtil.scImageFiledir);
									rich.setImage(OSSClientUtil.SC_IMAGE_URL+image);
							}else{
								result.setError("图片错误");
								return JSON.toJSONString(result);
							}
						}else{
							if (realImg != null) {//如果image=null 表示上传的不是图片格式
								if(realImg.getWidth() >= 748 && realImg.getWidth() <= 752 && realImg.getHeight() >= 748 && realImg.getHeight() <= 752){
									if(myfile.getSize() > size){
										result.setError("大小错误");
										return JSON.toJSONString(result);
									}
									String image = ossUtil.uploadImg2Oss(myfile,OSSClientUtil.scImageFiledir);
									rich.setImage(OSSClientUtil.SC_IMAGE_URL+image);
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
								rich.setVideo_url(OSSClientUtil.SC_VIDEO_URL+image);
			                }
			            }
					}
				}

				if (rich.getType()==3 || rich.getType()==6 || rich.getType()==7 || rich.getType()==11 || rich.getType()==12 || rich.getType()==14){
					if (StringUtil.isBlank(rich.getImage())){
						result.setError("请上传图片");
						return JSON.toJSONString(result);
					}
				}

				result = richService.addRichData(rich);
		} catch (Exception e) {
			result.setError("系统错误");
			return JSON.toJSONString(result);
		}finally {
			ossUtil.destory();
		}
        return JSON.toJSONString(result);
    }
	
	
	@RequestMapping("/getRichData")
    @ResponseBody
    public String getRichData(DataTableDTO dataTableDTO,Rich rich) {
		ActiveSpan.tag("123","12322323");
		System.out.println(TraceContext.traceId());
        Page<Rich> list = richService.getRichData(dataTableDTO,rich);
        DataTableResult dataTableResult = new DataTableResult(dataTableDTO.getDraw(),list.getTotal(),list.getTotal(),list.getResult());
        return JSON.toJSONString(dataTableResult);
    }
	

	
	@RequestMapping("/selectById")
    @ResponseBody
    public String selectById(String id) {
		Rich rich = richService.selectById(id);
        return JSON.toJSONString(rich);
    }

}
