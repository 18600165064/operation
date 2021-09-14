package com.data.display.controller.richController;

import com.alibaba.fastjson.JSON;
import com.data.display.mapper.commodityMapper.SpuCommodityMapper;
import com.data.display.model.commodity.SpuCommodity;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.rich.YMComment;
import com.data.display.model.user.SysUser;
import com.data.display.service.richService.YMCommentService;
import com.data.display.util.OSSClientUtil;
import com.data.display.util.StringUtil;
import com.data.display.util.UserUtil;
import com.github.pagehelper.Page;
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
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.*;

@Controller
@RequestMapping("/yMComment")
public class YMCommentController {

    private static Logger _log = LoggerFactory.getLogger(YMCommentController.class);

    @Autowired
    private YMCommentService yMCommentService;

    @Resource
    private SpuCommodityMapper spuCommodityMapper;


    @RequestMapping("/yMCommentMenu")
    public String yMCommentMenu() {
        return "/rich/yMCommentMenu";
    }

    @RequestMapping("/addYMComment")
    public String addYMComment() {
        return "/rich/addYMComment";
    }

    @RequestMapping("/editYMComment")
    public String editYMComment(Model model, YMComment yMComment) {
        model.addAttribute("id", yMComment.getId());
        return "/rich/editYMComment";
    }

    @RequestMapping("/getSpuData")
    @ResponseBody
    public String getSpuData() {
        List<SpuCommodity> list = spuCommodityMapper.selectAll();
        return JSON.toJSONString(list);
    }

    @RequestMapping(value="/upload",method=RequestMethod.POST)
    @ResponseBody
    public String upload(HttpServletRequest request,HttpServletResponse response,@RequestParam("files")MultipartFile[] files) {
        DataTableResult result =  new DataTableResult();
        OSSClientUtil ossUtil = new OSSClientUtil();
        String images = "";
        int size = 1024 * 500;
        int i = 0;
        try {
            if (files.length > 4){
                result.setDraw(0);
                result.setError("数量错误");
                return JSON.toJSONString(result);
            }
            for (MultipartFile myfile : files) {
                BufferedImage realImg = ImageIO.read(myfile.getInputStream());
                if (realImg == null) {//如果image=null 表示上传的不是图片格式
                    result.setDraw(0);
                    result.setError("图片错误");
                    return JSON.toJSONString(result);
                }else{
                    if(myfile.getSize() > size){
                        result.setDraw(0);
                        result.setError("大小错误");
                        return JSON.toJSONString(result);
                    }
                    String name = myfile.getOriginalFilename();
                    String suffix = name.substring(name.lastIndexOf(".") + 1);
                    if (suffix.equals("jpg") || suffix.equals("png") || suffix.equals("jpeg")){
                        continue;
                    }else{
                        result.setDraw(0);
                        result.setError("格式错误");
                        return JSON.toJSONString(result);
                    }
                }
            }

            for (MultipartFile myfile : files) {
                if(i == files.length-1){
                    images += OSSClientUtil.SP_IMAGE_URL + ossUtil.uploadImg2Oss(myfile,OSSClientUtil.spImageFiledir);
                }else{
                    images += OSSClientUtil.SP_IMAGE_URL + ossUtil.uploadImg2Oss(myfile,OSSClientUtil.spImageFiledir) + ",";
                }
                i ++;
            }
        } catch (Exception e) {
            result.setError("系统错误");
        }
        result.setDraw(1);
        result.setData(images);
        return JSON.toJSONString(result);
    }


    @RequestMapping(value="/editYMCommentData",method=RequestMethod.POST)
    @ResponseBody
    public String editYMCommentData(YMComment yMComment) {
        DataTableResult result = new DataTableResult();
        try {
            if (yMComment.getImages().split(",").length > 4){
                result.setError("数量错误");
                return JSON.toJSONString(result);
            }
            if (StringUtil.isBlank(yMComment.getImages())){
                yMComment.setImages(null);
            }
            yMComment.setUpdate_time(new Date());
            result.setDraw(yMCommentService.editYMCommentData(yMComment));
            result.setError("修改成功");
        } catch (Exception e) {
            result.setError("系统错误");
        }
        return JSON.toJSONString(result);
    }



    @RequestMapping(value="/addYMCommentData",method= RequestMethod.POST)
    @ResponseBody
    public String addYMCommentData(YMComment yMComment){
        DataTableResult result = new DataTableResult();
        SysUser user = UserUtil.getUserMessage();
        try{
            if (yMComment.getImages().split(",").length > 4){
                result.setError("数量错误");
                return JSON.toJSONString(result);
            }

            for (int j = 0; j < 519; j++) {
                int virtUserId = (int)(1+Math.random()*(519-1+1));
                Map<String,Object> map = yMCommentService.selectByUserId(String.valueOf(virtUserId));
                if (map != null){
                    yMComment.setHead_icon(String.valueOf(map.get("head_icon")));
                    yMComment.setUser_id(Integer.parseInt(String.valueOf(map.get("id"))));
                    yMComment.setNick_name(String.valueOf(map.get("nick_name")));
                    break;
                }
            }
            if (StringUtil.isBlank(yMComment.getImages())){
                yMComment.setImages(null);
            }
            yMComment.setCreate_time(new Date());
            yMComment.setEditor_id(user.getId());

            result.setDraw(yMCommentService.addYMCommentData(yMComment));
            result.setError("保存成功");
        }catch (Exception e) {
            result.setError("系统错误");
        }
        return JSON.toJSONString(result);
    }




    /**
     * 获取列表数据
     * @param dataTableDTO
     * @param yMComment
     * @return
     */
    @RequestMapping("/getYMCommentData")
    @ResponseBody
    public String getYMCommentData(DataTableDTO dataTableDTO,YMComment yMComment) {
        Page<YMComment> list = yMCommentService.getYMCommentData(dataTableDTO,yMComment);
        DataTableResult dataTableResult = new DataTableResult(dataTableDTO.getDraw(),list.getTotal(),list.getTotal(),list.getResult());
        return JSON.toJSONString(dataTableResult);
    }


    /**
     * 单条数据
     * @param id
     * @return
     */
    @RequestMapping("/selectById")
    @ResponseBody
    public String selectById(String id) {
        List<Map<String,String>> list = new ArrayList<>();
        YMComment yMComment = yMCommentService.selectById(id);
        if (StringUtil.isNotBlank(yMComment.getImages())){
            String[] array = yMComment.getImages().split(",");
            for (int i = 0; i < array.length; i++) {
                Map<String,String> map = new HashMap<String, String>();
                String name = array[i].toString().split("data/")[1];
                map.put("caption", name);
                map.put("url", "/spuCommodity/delOssPhoto");
                map.put("key",String.valueOf(i+1));
                list.add(map);
            }
            yMComment.setList(JSON.toJSONString(list));
        }
        return JSON.toJSONString(yMComment);
    }

}
