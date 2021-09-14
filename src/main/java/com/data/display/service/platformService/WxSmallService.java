//package com.data.display.service.platformService;
//
//import com.data.display.service.platformService.impl.WxSmallServiceFallBack;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@FeignClient(value = "wxSmallService", url = "http:172.16.1.10:9089/", fallback = WxSmallServiceFallBack.class)
//public interface WxSmallService {
//
//    @RequestMapping("getAccessToken")
//    String getAccessToken();
//}
