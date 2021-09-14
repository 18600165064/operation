package com.data.display.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: CYN
 * @Date: 2019/1/14 11:39
 * @Description:错误页跳转
 */
@Controller
@RequestMapping("/error")
public class ErrorController {
    @RequestMapping("/404")
    public String error404(){
        return "/error/404";
    }
    @RequestMapping("/500")
    public String error500(){
        return "/error/500";
    }
    @RequestMapping("/403")
    public String error403(){
        return "/error/403";
    }
}
