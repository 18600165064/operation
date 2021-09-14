package com.data.display;

import java.util.Date;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    
    @Bean
    public Converter<String, Date> addNewConvert() { 
    	return new Converter<String, Date>() { 
    		@Override 
    		public Date convert(String source) { 
//    			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    			Date date = null; 
    			try { 
    				long lt = new Long(source);
    			    date = new Date(lt);
	    			}catch (Exception e) {
	    				e.printStackTrace(); 
	    			}
	    				return date; 
	    			} 
    		}; 
    	}
    
    
    
    /**  
     * 文件上传配置  
     * @return  
     */  
    @Bean  
    public MultipartConfigElement multipartConfigElement() {  
        MultipartConfigFactory factory = new MultipartConfigFactory();  
        //单个文件最大  
        factory.setMaxFileSize("15360KB"); //15M
        /// 设置总上传数据总大小  
        factory.setMaxRequestSize("20480KB");  //20M
        return factory.createMultipartConfig();  
    }  
    
    
}

