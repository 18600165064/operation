package com.data.display.controller.userController;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.user.UserInfo;
import com.data.display.service.userService.UserInfoService;
import com.github.pagehelper.Page;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;
	
	
	@RequestMapping("/userInfoMenu")
    public String roleList() {
        return "/user/userInfoMenu";
    }
	
	@RequestMapping("/getUserInfoData")
    @ResponseBody
    public String getUserInfoData(DataTableDTO dataTableDTO,UserInfo userInfo) {
        Page<Map<String, Object>> page = userInfoService.getUserInfoData(dataTableDTO,userInfo);
		DataTableResult dataTableResult = new DataTableResult(dataTableDTO.getDraw(), page.getTotal(), page.getTotal(), page.getResult());
        return JSON.toJSONString(dataTableResult);
    }
	
	/**
	 * 获取顶级账户  id在 650  到 10000  
	 * @param userInfo
	 * @return
	 */
	@RequestMapping("/getTopData")
    @ResponseBody
    public String getTopData(UserInfo userInfo) {
		 List<UserInfo> list = userInfoService.getTopData(userInfo);
        return JSON.toJSONString(list);
    }
	
	@RequestMapping("/selectById")
    @ResponseBody
    public String selectById(String user_id) {
		UserInfo userInfo = userInfoService.selectById(user_id);
        return JSON.toJSONString(userInfo);
    }
	
	
	
	/**
	 * 用户属性设置
	 * @param userInfo
	 * @param type
	 * @return
	 */
	@RequestMapping("/updateUserInfo")
    @ResponseBody
    public String updateUserInfo(UserInfo userInfo,String type,String spuid) {
		DataTableResult dataTableResult = userInfoService.updateUserInfo(userInfo,type,spuid);
        return JSON.toJSONString(dataTableResult);
    }
	
}
