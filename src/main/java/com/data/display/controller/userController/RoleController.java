package com.data.display.controller.userController;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.data.display.mapper.userMapper.RoleMapper;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.user.SysRole;
import com.data.display.service.userService.RoleService;
import com.data.display.util.StringUtil;
import com.github.pagehelper.Page;


/**
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/user")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@Resource
	private RoleMapper roleMapper;

	
	/**
	 * 角色列表页
	 * @return
	 */
	@RequestMapping("/roleList")
    public String roleList(Model model,String userId) {
			model.addAttribute("userId", userId);
        return "/user/roleList";
    }
	
	/**
	 * 角色编辑
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping("/editRole")
    public String editRole(Model model,String roleId,String userId) {
			model.addAttribute("roleId", roleId);
			model.addAttribute("userId", userId);
        return "/user/editRole";
    }
	
	/**
	 * 新增角色
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping("/addRole")
    public String addRole(Model model,String userId) {
			model.addAttribute("userId", userId);
        return "/user/addRole";
    }
	
	/**
	 * 跳转至权限页
	 * @param attr
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/forwardMenuList")
    public ModelAndView forwardPermissionList(RedirectAttributes attr,String roleId) {
		 attr.addAttribute("userId", roleId);
		return new ModelAndView("forward:/user/menuRoleList");
    }
	
	
	/**
	 * 根据ID获取信息
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/getRoleById")
    @ResponseBody
    public String getRoleById(String roleId) {
        SysRole role = roleService.getRoleById(roleId);
        return JSON.toJSONString(role);
    }
	
	
	/**
	 * 角色信息 有分页
	 * @param dataTableDTO
	 * @param
	 * @return
	 */
	@RequestMapping("/getRoleData")
    @ResponseBody
    public String getRoleData(Model model,DataTableDTO dataTableDTO, String userId,String description,String name) {
        Page<Map<String, Object>> page = roleService.getRoleData(dataTableDTO, userId,description,name);
        model.addAttribute("count", page.getTotal());
        DataTableResult dataTableResult = new DataTableResult(dataTableDTO.getDraw(), page.getTotal(), page.getTotal(), page.getResult());
        return JSON.toJSONString(dataTableResult);
    }
	
	/**
	 * 角色信息 没有分页
	 * @param userId
	 * @param description
	 * @param name
	 * @return
	 */
	@RequestMapping("/getAllData")
    @ResponseBody
    public String getAllData(String userId,String description,String name) {
        Page<Map<String, Object>> page = roleMapper.getRoleData(userId, description, name);
        return JSON.toJSONString(page);
    }
	
	/**
	 * 新增角色
	 * @param sysRole
	 * @return
	 */
	@RequestMapping("/addRoleData")
    @ResponseBody
    public String addRoleData(SysRole sysRole,String userId) {
		DataTableResult dataTableResult = new DataTableResult();
		if(StringUtil.isBlank(sysRole.getName())){
			dataTableResult.setDraw(0);
			dataTableResult.setError("操作错误");
		}else{
			dataTableResult = roleService.addRoleData(sysRole,userId);
		}
        return JSON.toJSONString(dataTableResult);
    }
	
	/**
	 * 修改
	 * @param role
	 * @param userId
	 * @return
	 */
	@RequestMapping("/updateRole")
    @ResponseBody
	public String updateRole(SysRole role,String userId){
		DataTableResult dataTableResult = roleService.updateRole(role,userId);
		return JSON.toJSONString(dataTableResult);
	}
	
	
	/**
	 * 删除角色
	 * @param roleId
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping("/delRole")
    @ResponseBody
    public String delRole(String roleId,String userId){
		DataTableResult dataTableResult = roleService.delRole(userId,roleId);
		return JSON.toJSONString(dataTableResult);
    }


}
