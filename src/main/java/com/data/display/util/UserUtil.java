package com.data.display.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.data.display.model.user.SysUser;

/**
 * @Author: CYN
 * @Date: 2019/1/15 14:36
 * @Description:获取登录用户信息
 */
public class UserUtil {

	private static final Logger logger = LoggerFactory.getLogger(UserUtil.class);
	
    public static SysUser getUserMessage(){
    	SysUser user = new SysUser();
    	try {
    		user = (SysUser) SecurityContextHolder.getContext() .getAuthentication().getPrincipal();
		} catch (Exception e) {
			logger.error("获取用户信息错误", e);
		}
         return user;
    }
}
