package com.data.display.controller.userController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.user.SysMenuRole;
import com.data.display.service.userService.SysMenuRoleService;

/**
 * @Author: CYN
 * @Date: 2019/1/8 14:20
 * @Description:
 */
@Controller
@RequestMapping("/user")
public class SysMenuRoleController {
	
	@Autowired
	private SysMenuRoleService sysMenuRoleService;
	
	/**
	 * 菜单列表
	 * @return
	 */
	@RequestMapping("/menuRoleList")
    public String roleList(Model model,String roleId) {
		model.addAttribute("roleId",roleId);
        return "/user/permissionMenuData";
    }
	
	/**
	 * 获取所有菜单以及根据用户ID获取所拥有的菜单
	 * @return
	 */
	@RequestMapping("/getMenuRoleAllData")
	@ResponseBody
    public String getMenuRoleData(String roleId) {
		List<SysMenuRole> page = sysMenuRoleService.getMenuRoleData(roleId);
	    return JSON.toJSONString(page);
    }
	
	
	/**
	 * 更改角色菜单
	 * @param menuIds
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/updateMenuByRoleId")
	@ResponseBody
   public String updateMenuByRoleId(String menuIds,String roleId) {
		DataTableResult dataTableResult = sysMenuRoleService.updateMenuByRoleId(menuIds,roleId);
	    return JSON.toJSONString(dataTableResult);
   }
	
}
