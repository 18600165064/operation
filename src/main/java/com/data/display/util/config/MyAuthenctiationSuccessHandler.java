package com.data.display.util.config;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.data.display.mapper.userMapper.SysMenuRoleMapper;
import com.data.display.mapper.userMapper.UserMapper;
import com.data.display.model.user.SysMenuRole;
import com.data.display.model.user.SysUser;
import com.data.display.util.UserUtil;

/**
 * @Author: CYN
 * @Date: 2019/1/8 14:37
 * @Description:登录成功处理
 */
@Component("myAuthenctiationSuccessHandler")
public class MyAuthenctiationSuccessHandler implements AuthenticationSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(MyAuthenctiationSuccessHandler.class);
    @Resource
    UserMapper userMapper;
    @Resource
    SysMenuRoleMapper sysMenuRoleMapper;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SysUser sysUser = UserUtil.getUserMessage();
        String userName=sysUser.getUsername();
        logger.info("登录成功："+userName);
        HttpSession httpSession=request.getSession();
        String json;
        if (sysUser != null) {
            List<SysMenuRole> menuList=sysMenuRoleMapper.getSysMenu(sysUser.getId());
            json=JSONArray.toJSON(menuList).toString();
        }else{
            json="";
        }
        //存储菜单
        httpSession.setAttribute("menu",json);
        httpSession.removeAttribute("error");
        request.getRequestDispatcher("/").forward(request, response);
    }
}
