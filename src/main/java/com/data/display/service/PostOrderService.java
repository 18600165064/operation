package com.data.display.service;


import org.nutz.http.Http;
import org.nutz.http.Response;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.data.display.model.dto.DataTableResult;
import com.data.display.quartz.DeliverySyncJob;
import com.data.display.util.config.Configs;

/**
 * 推送订单
 */
@Service("PostOrderService")
public class PostOrderService {

	 private static Logger _log = LoggerFactory.getLogger(PostOrderService.class);


    public DataTableResult post(String company,String number){
    	
    	_log.info("物流公司============"+company+"单号-----"+number);
    	
    	DataTableResult result2 = new DataTableResult();
        if (Strings.isBlank(company) || Strings.isBlank(number)){
        	_log.info("参数有误");
        	result2.setDraw(0);
        	result2.setError("参数有误");
            return result2;
        }
        NutMap reqBody = NutMap.NEW();
        reqBody.setv("schema","json");
        NutMap param = NutMap.NEW();
        param.setv("company",company.trim());
        param.setv("number",number.trim());
        param.setv("key","iHFRWqAL5367");
        NutMap parameters = NutMap.NEW();
        String notifyUrl = Configs.getYmApi()+"delivery/notify";
        parameters.setv("callbackurl", notifyUrl);
            parameters.setv("resultv2","1");
            param.setv("parameters",parameters);
        reqBody.setv("param",Json.toJson(param,JsonFormat.compact()));
        Response response1 = Http.post2("http://poll.kuaidi100.com/poll", reqBody, 10 * 1000);
        _log.info("response1"+response1.getStatus());
        if (response1.isOK()){
        	_log.info("response1.isOK");
        	
            NutMap resMap = Json.fromJson(NutMap.class,response1.getContent());
            _log.info("物流结果查询=========="+JSON.toJSONString(resMap));
            boolean result = resMap.getBoolean("result");
           if (result){
        	   _log.info("result"+result);
        	   result2.setDraw(1);
               return result2;
           }
        }
        result2.setDraw(0);
        result2.setError("未知错误");
        
        _log.info("-----------------------------------------"+JSON.toJSONString(result2));
        
        return result2;
    }


}
