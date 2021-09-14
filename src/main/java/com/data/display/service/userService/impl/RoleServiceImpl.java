package com.data.display.service.userService.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.data.display.mapper.userMapper.RoleMapper;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.user.SysRole;
import com.data.display.service.userService.RoleService;
import com.data.display.util.StringUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service("roleService")
public class RoleServiceImpl implements RoleService{

	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

	 @Resource
	 private RoleMapper roleMapper;

	 /**
	  * 获取角色信息
	  */
	@Override
	public Page<Map<String, Object>> getRoleData(DataTableDTO dataTableDTO, String userId,String description,String name) {
			PageHelper.startPage(dataTableDTO.getPageNum(), dataTableDTO.getLength());
	        Page<Map<String, Object>> roleData = roleMapper.getRoleData(userId,description,name);
			return roleData;
	}
	
	/**
	 * 删除所拥有的角色
	 * @param roleId,userId
	 * @return
	 */
	@Override
	public DataTableResult delRole(String userId, String roleId) {
		DataTableResult result = new DataTableResult();
		result.setError("删除成功");
		result.setDraw(1);
		try{
			if(StringUtil.isNotBlank(userId)){
				roleMapper.delUserRole(userId,roleId);
			}else{
				roleMapper.delRole(roleId);
			}
		}catch (Exception e){
			result.setError("删除失败");
			logger.error("角色删除失败",e);
		}
		return result;
	}

	/**
	 * 新增角色
	 */
	@Override
	public DataTableResult addRoleData(SysRole sysRole,String userId) {
		logger.info("新增角色信息为：{["+userId+","+ JSON.toJSONString(sysRole)+"]}");
		DataTableResult result = new DataTableResult();
		result.setError("保存成功");
		List<Map<String, Object>> roleList = roleMapper.getRoleData(null, null, sysRole.getName());
		if("".equals(userId)){
			if(roleList.size() < 1){
				result.setDraw(roleMapper.addRoleData(sysRole));
			}else{
				result.setError("该角色已存在");
			}
		}else{
			if(roleList.size() > 0){
				SysRole sysRole2 = (SysRole) roleList.get(0);
				int count = roleMapper.userRoleCount(userId);
				if(count > 0){
					result.setError("当前用户已拥有角色"); 
				}else{
					result.setDraw(roleMapper.addUserRole(String.valueOf(sysRole2.getId()),userId));
				}
			}else{
				result.setError("当前没有该角色");
			}
		}
		return result;
	}

	/**
	 * 根据ID 获取信息
	 */
	@Override
	public SysRole getRoleById(String roleId) {
		return roleMapper.getRoleById(roleId);
	}

	/**
	 * 修改
	 */
	@Override
	public DataTableResult updateRole(SysRole role, String userId) {
		DataTableResult result = new DataTableResult();
		try {
			if(StringUtil.isNotBlank(userId)){
				result.setDraw(roleMapper.updateUserRole(String.valueOf(role.getId2()),String.valueOf(role.getId()),userId));
			}else{
				result.setDraw(roleMapper.updateRole(role));
			}
			result.setError("修改成功");
		} catch (Exception e) {
			result.setError("修改失败");
			logger.error("角色修改失败",e);
		}
		return result;
	}

	
}
