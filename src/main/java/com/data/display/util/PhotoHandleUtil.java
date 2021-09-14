package com.data.display.util;

import com.data.display.mapper.commodityMapper.SpuDescMapper;
import com.data.display.mapper.richMapper.RichMapper;
import com.data.display.mapper.schoolMapper.SchoolContentMapper;
import com.data.display.model.commodity.SpuCommodity;
import com.data.display.model.rich.Rich;
import com.data.display.model.school.SchoolContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhotoHandleUtil implements Runnable{

    private static Logger _log = LoggerFactory.getLogger(PhotoHandleUtil.class);

    private SpuDescMapper spuDescMapper;

    private SpuCommodity spuCommodity;

    private int i;

    private Rich rich;

    private RichMapper richMapper;

    private SchoolContent schoolContent;

    private SchoolContentMapper schoolContentMapper;

    public PhotoHandleUtil(SpuCommodity spuCommodity, SpuDescMapper spuDescMapper, int i, Rich rich, RichMapper richMapper, SchoolContent schoolContent, SchoolContentMapper schoolContentMapper) {
        this.spuCommodity = spuCommodity;
        this.spuDescMapper = spuDescMapper;
        this.i = i;
        this.rich = rich;
        this.richMapper = richMapper;
        this.schoolContent = schoolContent;
        this.schoolContentMapper = schoolContentMapper;
    }

    @Override
    public void run() {
        List<String> list = new ArrayList<String>();
        Pattern p_img = Pattern.compile("<(img|IMG)(.*?)(/>|></img>|>)");
        Matcher m_img = null;
        //SPU
        if(i == 1){
            m_img = p_img.matcher(spuCommodity.getSpuDesc().getSkudesc());
        }
        //消息富文本
        if (i == 2){
            m_img = p_img.matcher(rich.getContent());
        }
        //文章富文本
        if (i == 3){
            m_img = p_img.matcher(schoolContent.getContent());
        }

        boolean result_img = m_img.find();
        if (result_img) {
            while (result_img) {
                //获取到匹配的<img />标签中的内容
                String str_img = m_img.group(2); //开始匹配<img />标签中的src
                Pattern p_src = Pattern.compile("(src|SRC)=(\"|\')(.*?)(\"|\')");
                Matcher m_src = p_src.matcher(str_img);
                if (m_src.find()) {
                    String str_src = m_src.group(3);
                    _log.debug("服务器访问路径====="+str_src);
                    list.add(str_src);
                    String imgName = "";
                    if (str_src.contains(OSSClientUtil.URL)){
                        imgName = getImg(str_src);
                        _log.debug("服务器图片名称====="+imgName);
                    }
                    if(StringUtil.isNotBlank(imgName)){
                        //spu
                        if (i == 1){
                            String descname = spuCommodity.getSpuDesc().getSkudesc().replaceAll(str_src,OSSClientUtil.SP_IMAGE_URL+imgName);
                            _log.debug("OSS访问路径====="+descname);
                            spuCommodity.getSpuDesc().setSkudesc(descname);
                            spuCommodity.getSpuDesc().setSpuid(spuCommodity.getSpuid());
                            spuDescMapper.updateSpuDesc(spuCommodity.getSpuDesc());
                        }
                        //富文本消息
                        if(i == 2){
                            String descname = rich.getContent().replaceAll(str_src,OSSClientUtil.SP_IMAGE_URL+imgName);
                            _log.debug("OSS访问路径====="+descname);
                            rich.setContent(descname);
                            richMapper.editRichData(rich);
                        }
                        //文章富文本
                        if(i == 3){
                            String descname = schoolContent.getContent().replaceAll(str_src,OSSClientUtil.SP_IMAGE_URL+imgName);
                            _log.debug("OSS访问路径====="+descname);
                            schoolContent.setContent(descname);
                            schoolContentMapper.editShoolContentData(schoolContent);
                        }

                        String delPath = str_src.replaceAll(OSSClientUtil.URL,"/data/springboot");
                        _log.debug("需要删除的服务器路径====="+delPath);
                        File file=new File(delPath);
                        file.delete();

                        String fileName = "/"+delPath.substring(delPath.lastIndexOf("/")+1);
                        String folderName = delPath.replaceAll(fileName,"");
                        _log.debug("服务器文件夹路径====="+folderName);
                        File file2 = new File(folderName);
                        if (file2.exists()) {
                            File[] files = file2.listFiles();
                            _log.debug("服务器文件夹内文件个数====="+files.length);
                            if (null == files || files.length == 0) {
                                file2.delete();
                            }
                        }
                    }
                }
                result_img = m_img.find();
            }
        }
        System.out.println(list);
    }


    private String getImg(String iconUrl) {
        _log.debug("上传图片路径====="+iconUrl);
        OSSClientUtil ossUtil = new OSSClientUtil();
        String imgName = "";
        try {
            String url = iconUrl.replaceAll(OSSClientUtil.URL,"/data/springboot");
            File file = new File(url);
            InputStream is = new FileInputStream(file);
            String substring = iconUrl.substring(iconUrl.lastIndexOf(".")).toLowerCase();
            imgName = StringUtil.dataRandom() + substring;
            ossUtil.uploadFile2OSS(is,imgName, OSSClientUtil.spImageFiledir);
        } catch (Exception e) {
            _log.error("图片上传错误",e);
        }finally {
            ossUtil.destory();
        }
        return imgName;
    }


}
