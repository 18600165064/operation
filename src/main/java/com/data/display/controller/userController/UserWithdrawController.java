package com.data.display.controller.userController;

import javax.annotation.Resource;

import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.ioc.aop.Aop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.data.display.mapper.userMapper.UserInfoFinaceMapper;
import com.data.display.mapper.userMapper.UserInfoMapper;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.user.UserWithdraw;
import com.data.display.service.userService.UserWithdrawService;
import com.data.display.util.BurroKit;
import com.github.pagehelper.Page;

@Controller
@RequestMapping("/userWithdraw")
public class UserWithdrawController {

	@Autowired
	private UserWithdrawService userWithdrawService;
	@Resource
	private UserInfoMapper userInfoMapper;
	@Resource
	private UserInfoFinaceMapper userInfoFinaceMapper;
	
	
	
	@RequestMapping("/userWithdrawMenu")
    public String supplierInfoMenu() {
        return "/user/userWithdrawMenu";
    }
	
	/**
	 * 用户提现审核列表
	 * @param dataTableDTO
	 * @param userWithdraw
	 * @return
	 */
	@RequestMapping("/getUserWithdrawData")
    @ResponseBody
    public String getUserWithdrawData(DataTableDTO dataTableDTO,UserWithdraw userWithdraw) {
        Page<UserWithdraw> list = userWithdrawService.getUserWithdrawData(dataTableDTO,userWithdraw);
        DataTableResult dataTableResult = new DataTableResult(dataTableDTO.getDraw(),list.getTotal(),list.getTotal(),list.getResult());
        return JSON.toJSONString(dataTableResult);
    }
	
	/**
	 * 转账
	 * @param userWithdraw
	 * @return
	 */
	@RequestMapping("/withdraw")
    @ResponseBody
	public String withdraw(UserWithdraw userWithdraw){
		DataTableResult result = userWithdrawService.withdraw(userWithdraw);
		return JSON.toJSONString(result);
	}
	
	
	
	/**
	 * 提现驳回
	 * @param userWithdraw
	 * @return
	 */
	@RequestMapping("/rejected")
    @ResponseBody
    public String rejected(UserWithdraw userWithdraw) {
		DataTableResult result = userWithdrawService.rejected(userWithdraw);
        return JSON.toJSONString(result);
    }
	
	public static void main(String[] args) {
		System.out.println(BurroKit.getRemoteIp());
	}
}
