package com.data.display.util.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @Author: CYN
 * @Date: 2019/1/14 11:37
 * @Description:错误页配置
 */
@Component
public class ErrorPageInterceptor implements  ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage[] errorPages=new ErrorPage[2];
        errorPages[0]=new ErrorPage(HttpStatus.NOT_FOUND,"/error/404");
        errorPages[1]=new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/error/500");
        errorPages[1]=new ErrorPage(HttpStatus.FORBIDDEN,"/error/403");
        registry.addErrorPages(errorPages);
    }
}