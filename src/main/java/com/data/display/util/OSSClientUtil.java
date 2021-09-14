package com.data.display.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.data.display.util.config.Contants;


public class OSSClientUtil {

	private static final Logger logger = LoggerFactory.getLogger(OSSClientUtil.class);
	//内网
    private String endpoint2 = "yuemee-test.oss-cn-beijing-internal.aliyuncs.com";
    //外网
	private String endpoint = "oss-cn-beijing.aliyuncs.com";
    // accessKey
//    private String accessKeyId = "LTAIQAiWQ9wbhGtZ";
    private String accessKeyId = "LTAIpuHyotbOhYVf";
//    private String accessKeySecret = "eM7Fc3UIGpb9cgM0Mkl2Ftpe94qr0M";
    private String accessKeySecret = "IZTyPIjl0NUr1u8cAuBri4yNiIUfXE";
    //空间
    private String bucketName = "yuemee-test";
    //商品图片文件存储目录
    public static final String spImageFiledir = "data/product/image/";
    //商品视频文件存储目录
    public static final String spVideoFiledir = "data/product/video/";
    //素材图片文件存储目录
    public static final String scImageFiledir = "data/material/image/";
    //素材视频文件存储目录
    public static final String scVideoFiledir = "data/material/video/";
    //订单导入
    public static final String orderFiledir = "data/file/";

    private OSSClient ossClient;
    
    //商品视频
    public static final String SP_VIDEO_URL = "https://yuemee-test.oss-cn-beijing.aliyuncs.com/data/product/video/";
    //商品图片
    public static final String SP_IMAGE_URL = "https://yuemee-test.oss-cn-beijing.aliyuncs.com/data/product/image/";
    //素材视频
    public static final String SC_VIDEO_URL = "https://yuemee-test.oss-cn-beijing.aliyuncs.com/data/material/video/";
    //素材图片
    public static final String SC_IMAGE_URL = "https://yuemee-test.oss-cn-beijing.aliyuncs.com/data/material/image/";
    //订单导入
    public static final String ORDER_URL = "https://yuemee-test.oss-cn-beijing.aliyuncs.com/data/file/";

    //服务器路径
    public static  final  String URL = "http://60.205.217.185:8082";
//    public static  final  String URL = "http://39.106.8.147:8081";

    public OSSClientUtil() {
        ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    /**
     * 初始化
     */
    public void init() {
        ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    /**
     * 销毁
     */
    public void destory() {
        ossClient.shutdown();
    }

    /**
     * 上传图片
     *
     * @param url
     */
    public void uploadImg2Oss(String url,String filedir) {
        File fileOnServer = new File(url);
        FileInputStream fin;
            try {
				fin = new FileInputStream(fileOnServer);
				String[] split = url.split("/");
	            this.uploadFile2OSS(fin, split[split.length - 1],filedir);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				logger.error("图片上传失败", e);
			}
            
    }


    public String uploadImg2Oss(MultipartFile file,String filedir){
    	String filename = file.getOriginalFilename();
		String prefix=filename.substring(filename.lastIndexOf(".")+1);
        if("mp4".equals(prefix)){
        	if (file.getSize() > 1024 * 1024 * 15) {
            	return "上传视频大小不能超过15M!";
            }
        }else{
        	if (file.getSize() > 1024 * 1024) {
            	return "上传图片大小不能超过1M!";
            }
        }
        
//        String originalFilename = file.getFile().getAbsolutePath();
        String originalFilename = file.getOriginalFilename();
        String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
//        Random random = new Random();
//        String name = random.nextInt(10000000) + System.currentTimeMillis() + substring;
        String name = StringUtil.dataRandom() + substring;
        try {
            InputStream inputStream = file.getInputStream();
            this.uploadFile2OSS(inputStream, name,filedir);
            return name;
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        }
        return name;
    }

    /**
     * 获得图片路径
     *
     * @param fileUrl
     * @return
     */
    public String getImgUrl(String fileUrl,String filedir) {
        if (null!=fileUrl){
            String[] split = fileUrl.split("/");
            return this.getUrl(filedir + split[split.length - 1]);
        }
        return null;
    }

    /**
     * 上传到OSS服务器  如果同名文件会覆盖服务器上的
     *
     * @param instream 文件流
     * @param fileName 文件名称 包括后缀名
     * @return 出错返回"" ,唯一MD5数字签名
     */
    public String uploadFile2OSS(InputStream instream, String fileName,String filedir) {
        String ret = "";
        try {
            //创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(instream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            //上传文件
            PutObjectResult putResult = ossClient.putObject(bucketName, filedir + fileName, instream, objectMetadata);
            ret = putResult.getETag();
            System.out.println(ret);
        } catch (IOException e) {
        	logger.error(e.getMessage(), e);
        } finally {
            try {
                if (instream != null) {
                    instream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    /**
     * Description: 判断OSS服务文件上传时文件的contentType
     *
     * @param FilenameExtension 文件后缀
     * @return String
     */
    public static String getcontentType(String FilenameExtension) {
        if (FilenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (FilenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (FilenameExtension.equalsIgnoreCase(".jpeg") ||
                FilenameExtension.equalsIgnoreCase(".jpg") ||
                FilenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpeg";
        }
        if (FilenameExtension.equalsIgnoreCase(".html")) {
            return "text/html";
        }
        if (FilenameExtension.equalsIgnoreCase(".txt")) {
            return "text/plain";
        }
        if (FilenameExtension.equalsIgnoreCase(".vsd")) {
            return "application/vnd.visio";
        }
        if (FilenameExtension.equalsIgnoreCase(".pptx") ||
                FilenameExtension.equalsIgnoreCase(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (FilenameExtension.equalsIgnoreCase(".docx") ||
                FilenameExtension.equalsIgnoreCase(".doc")) {
            return "application/msword";
        }
        if (FilenameExtension.equalsIgnoreCase(".xml")) {
            return "text/xml";
        }
        if (FilenameExtension.equalsIgnoreCase(".mp4")) {
            return "video/mpeg4";
        }
        if (FilenameExtension.equalsIgnoreCase(".xls")) {
            return "application/vnd.ms-excel";
        }
        if (FilenameExtension.equalsIgnoreCase(".xlsx")) {
            return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        }
        return "image/jpeg";
    }

    /**
     * 获得url链接
     *
     * @param key
     * @return
     */
    public String getUrl(String key) {
        // 设置URL过期时间为10年  3600l* 1000*24*365*10
        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
        // 生成URL
        URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
        if (url != null) {
            return url.toString();
        }
        return null;
    }
    public boolean deleteFile(String filePath) {
        String accessId= Contants.ACCESSID;
        String endpoint=Contants.ENDPOINT;
        String accessKey=Contants.ACCESSKEY;
        String bucket=Contants.BACKET;
        filePath=filePath.replace("https://" + bucket + "." + endpoint+"/","");
        OSSClient ossClient = new OSSClient(endpoint, accessId, accessKey);
        boolean exist = ossClient.doesObjectExist(bucket, filePath);
        if (!exist) {
        	logger.info("文件不存在,filePath={}"+filePath);
            return false;
        }
        logger.info("删除文件,filePath={}"+filePath);
        ossClient.deleteObject(bucket, filePath);
        ossClient.shutdown();
        return true;
    }
}
