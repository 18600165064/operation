package com.data.display.util.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author: CYN
 * @Date: 2019/1/15 09:15
 * @Description:登录失败处理
 */
@Component("myAuthenctiationFailureHandler")
public class MyAuthenctiationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        HttpSession httpSession=request.getSession();
        httpSession.setAttribute("error","用户名或密码错误");
        request.getRequestDispatcher("/login").forward(request, response);

    }
}
