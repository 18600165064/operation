package com.data.display.controller;

import com.data.display.model.dto.DataTableResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: CYN
 * @Date: 2019/2/19 15:53
 * @Description: 全局异常处理
 */
@RestControllerAdvice
public class ExceptionController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    /**
     * 异常结果返回
     */
    @ExceptionHandler(Exception.class)
    public DataTableResult errorHandler(Exception e) {
        DataTableResult result = new DataTableResult();
         logger.error("system"+e.getMessage());
        e.printStackTrace();
        result.setError("系统错误");
        return result;
    }

    /**
     * 自定义异常结果返回
     */
    @ExceptionHandler(RuntimeException.class)
    public DataTableResult customErrorHandler(RuntimeException e) {
        DataTableResult result = new DataTableResult();
        logger.error("custom"+e.getMessage());
        e.printStackTrace();
        result.setError("系统错误");
        return result;
    }


}
