package com.data.display.controller;

import com.data.display.model.user.SysUser;
import com.data.display.service.userService.UserService;
import com.data.display.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: CYN
 * @Date: 2019/1/7 17:52
 * @Description:
 */
@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    /**
     * 首页
     * @return view
     */
    @RequestMapping("/")
    public String index(){
        return "index"; }

    /**
     * 修改密码页
     * @return view
     */
    @RequestMapping("/editPassword")
    public ModelAndView editPassword()
    {
        ModelAndView mv=new ModelAndView("/setting/editPassword");
        mv.addObject("error","");
        return mv;
    }

    /**
     * 修改密码
     * @param oldPassword  旧密码
     * @param newPassword 新密码
     * @return view
     */
    @RequestMapping("/saveNewPassword")
    public ModelAndView saveNewpassword(String oldPassword, String newPassword){
        ModelAndView mv=new ModelAndView("/logout");
        SysUser sysUser = UserUtil.getUserMessage();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (bCryptPasswordEncoder.matches(oldPassword,sysUser.getPassword())) {
            //密码加密
            String pwd = bCryptPasswordEncoder.encode(newPassword);
            userService.updatePassword(sysUser.getId().toString(),pwd);
        }else{
            ModelAndView mvError=new ModelAndView("/setting/editPassword");
            mvError.addObject("error","旧密码输入错误");
            return  mvError;
        }
//        return new ModelAndView("/logout");
        return new ModelAndView("redirect:/logout");
    }
    
    @RequestMapping("/uploadimage")
    public void uploadimage(@RequestParam("upfile") MultipartFilter[] upfile){
    	System.out.println(upfile);
    }

}