package com.data.display.service.userService;

import java.util.Map;

import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.user.SysRole;
import com.github.pagehelper.Page;

public interface RoleService {

	/**
	 * 获取角色信息
	 * @param dataTableDTO
	 * @param userId
	 * @return
	 */
	Page<Map<String, Object>> getRoleData(DataTableDTO dataTableDTO, String userId,String description,String name);

	/**
	 * 删除所拥有的角色
	 * @param roleId
	 * @return
	 */
	DataTableResult delRole(String userId, String roleId);

	/**
	 * 新增角色
	 * @param sysRole
	 * @param userId 
	 * @return
	 */
	DataTableResult addRoleData(SysRole sysRole, String userId);

	/**
	 * 根据ID 获取信息
	 * @param roleId
	 * @return
	 */
	SysRole getRoleById(String roleId);

	/**
	 * 修改
	 * @param role
	 * @param userId
	 * @return
	 */
	DataTableResult updateRole(SysRole role, String userId);

}
