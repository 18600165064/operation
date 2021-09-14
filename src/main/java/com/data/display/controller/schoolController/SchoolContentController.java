package com.data.display.controller.schoolController;


import com.alibaba.fastjson.JSON;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.school.SchoolContent;
import com.data.display.service.schoolService.SchoolContentService;
import com.data.display.util.OSSClientUtil;
import com.data.display.util.StringUtil;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

@Controller
@RequestMapping("/schoolContent")
public class SchoolContentController {

    @Autowired
    private SchoolContentService schoolContentService;

    /**
     * 列表页
     * @return
     */
    @RequestMapping("/schoolContentMenu")
    public String schoolContentMenu() {
        return "/school/schoolContentMenu";
    }

    @RequestMapping("/addSchoolContent")
    public String addSchoolContent(Model model) {
        return "/school/addSchoolContentMenu";
    }

    @RequestMapping("/editSchoolContent")
    public String editSchoolContent(Model model,String id) {
        model.addAttribute("id", id);
        return "/school/editSchoolContentMenu";
    }

    @RequestMapping("/getContentData")
    @ResponseBody
    public String getContentData(DataTableDTO dataTableDTO, SchoolContent schoolContent) {
        Page<SchoolContent> list = schoolContentService.getContentData(dataTableDTO,schoolContent);
        DataTableResult dataTableResult = new DataTableResult(dataTableDTO.getDraw(),list.getTotal(),list.getTotal(),list.getResult());
        return JSON.toJSONString(dataTableResult);
    }

    @RequestMapping("/selectById")
    @ResponseBody
    public String selectById(String id) {
        SchoolContent schoolContent = schoolContentService.selectById(id);
        return JSON.toJSONString(schoolContent);
    }

    @RequestMapping("/addSchoolContentData")
    @ResponseBody
    public String addSchoolContentData(SchoolContent schoolContent, @RequestParam("image1") MultipartFile[] image1, @RequestParam("video")MultipartFile[] video) {
        DataTableResult result =  new DataTableResult();
        OSSClientUtil ossUtil = new OSSClientUtil();
        try {
            int size = 1024 * 500;
            for (MultipartFile myfile : image1) {
                if(StringUtil.isNotBlank(myfile.getOriginalFilename())){
                    BufferedImage realImg = ImageIO.read(myfile.getInputStream());
                    if (realImg != null) {//如果image=null 表示上传的不是图片格式
                        if(myfile.getSize() > size){
                            result.setError("大小错误");
                            return JSON.toJSONString(result);
                        }
                        String image = ossUtil.uploadImg2Oss(myfile,OSSClientUtil.scImageFiledir);
                        schoolContent.setImage(OSSClientUtil.SC_IMAGE_URL+image);
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
                            schoolContent.setVideo_url(OSSClientUtil.SC_VIDEO_URL+image);
                        }
                    }
                }
            }

            result = schoolContentService.addSchoolContentData(schoolContent);
        }catch (Exception e){

        }finally {
            ossUtil.destory();
        }
        return JSON.toJSONString(result);
    }

    @RequestMapping("/editShoolColumeData")
    @ResponseBody
    public String editShoolContentData(SchoolContent schoolContent,@RequestParam("image1")MultipartFile[] image1,@RequestParam("video")MultipartFile[] video) {
        DataTableResult result =  new DataTableResult();
        OSSClientUtil ossUtil = new OSSClientUtil();
        try {
            int size = 1024 * 500;
            for (MultipartFile myfile : image1) {
                if(StringUtil.isNotBlank(myfile.getOriginalFilename())){
                    BufferedImage realImg = ImageIO.read(myfile.getInputStream());
                    if (realImg != null) {//如果image=null 表示上传的不是图片格式
                        if(myfile.getSize() > size){
                            result.setError("大小错误");
                            return JSON.toJSONString(result);
                        }
                        String image = ossUtil.uploadImg2Oss(myfile,OSSClientUtil.scImageFiledir);
                        schoolContent.setImage(OSSClientUtil.SC_IMAGE_URL+image);
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
                            schoolContent.setVideo_url(OSSClientUtil.SC_VIDEO_URL+image);
                        }
                    }
                }
            }

            result = schoolContentService.editShoolContentData(schoolContent);
        }catch (Exception e){

        }finally {
            ossUtil.destory();
        }
        return JSON.toJSONString(result);
    }

}
