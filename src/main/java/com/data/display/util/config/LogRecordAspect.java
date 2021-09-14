package com.data.display.util.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.data.display.model.dto.ParentComment;
import com.data.display.model.operation.OperationRecord;
import com.data.display.model.user.SysUser;
import com.data.display.service.operationService.OperationRecordService;
import com.data.display.util.StringUtil;
import com.data.display.util.UserUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

@Aspect
@Component
public class LogRecordAspect{

    private static final Logger logger = LoggerFactory.getLogger(LogRecordAspect.class);

    @Autowired
    private OperationRecordService operationRecordService;


    // 定义切点Pointcut
    @Pointcut("execution(* com.data.display.controller..*.*(..))")
    public void excudeService() {
    }


    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = null;
        StringBuilder sbLog = new StringBuilder("\n");
        String names = "";
        String parems = "";
        try {
            sbLog.append(String.format("类名：%s\r\n", proceedingJoinPoint.getTarget().getClass().getName()));
            MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
            sbLog.append(String.format("方法：%s\r\n", methodSignature.getMethod().getName()));

            names = proceedingJoinPoint.getTarget().getClass().getName() +"."+ methodSignature.getMethod().getName();


            Object[] args = proceedingJoinPoint.getArgs();
            for (Object o : args) {
                try {
                    if(o != null){
                        if (o instanceof ParentComment){
                            JSONObject jsonStu = (JSONObject) JSONObject.toJSON(o);
                            sbLog.append(jsonStu.toJSONString());
                            sbLog.append(String.format("参数：%s\r\n", JSON.toJSON(o)));
                            parems += JSON.toJSON(o);
                        }
                    }
                }catch (Exception e){
                    logger.error("系统错误"+e);
                    break;
                }
            }

            long startTime = System.currentTimeMillis();
            result = proceedingJoinPoint.proceed();
            long endTime = System.currentTimeMillis();
            sbLog.append(String.format("返回：%s\r\n", JSON.toJSON(result)));
            sbLog.append(String.format("耗时：%ss", endTime - startTime));


        } catch (Exception ex) {
            sbLog.append(String.format("异常：%s", ex.getMessage()));
            logger.error("系统错误"+ex);
        } finally {
            logger.info(sbLog.toString());
        }

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        String url = request.getRequestURL().toString();
        String method = request.getMethod();
//        String queryString = request.getQueryString();

        OperationRecord operationRecord = new OperationRecord();
        if(!url.substring(url.length()-5, url.length()).equals("login")){
            SysUser user = UserUtil.getUserMessage();
            operationRecord.setUid(user.getId());
        }

//        if(StringUtil.isBlank(parems) && "GET".equals(method)){
//            if(request.getQueryString()!=null) {
//                try {
//                    parems = URLDecoder.decode(request.getQueryString(), "utf-8");//将中文转码
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }
        operationRecord.setCraete_time(new Date());
        operationRecord.setRequest_method(method);
        operationRecord.setRequest_url(names);
        operationRecord.setRequest_parameter(parems);
        operationRecordService.addOperationRecord(operationRecord);
        return result;


    }

}