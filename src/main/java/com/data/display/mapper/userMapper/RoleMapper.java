package com.data.display.mapper.userMapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.data.display.model.user.SysRole;
import com.github.pagehelper.Page;

public interface RoleMapper {

	/**
	 * 获取用户角色
	 * @param userId
	 * @return
	 */
	Page<Map<String, Object>> getRoleData(@Param("userId")String userId,@Param("description")String description,@Param("name")String name);

	/**
	 * 删除所拥有的的角色
	 * @param userId
	 * @param roleId
	 * @return 
	 * 
	 */
	int delUserRole(@Param("userId")String userId, @Param("roleId")String roleId);
	
	/**
	 * 删除角色，用户角色关联，菜单角色关联
	 * @param roleId
	 * @return
	 */
	int delRole(@Param("roleId")String roleId);
	/**
	 * 角色新增
	 * @param sysRole
	 * @return
	 */
	int addRoleData(SysRole sysRole);

	/**
	 * 关系新增
	 * @param id
	 * @param userId
	 * @return
	 */
	int addUserRole(@Param("sys_role_id")String sys_role_id, @Param("sys_user_id")String sys_user_id);

	/**
	 * 根据用户ID查看是否拥有角色
	 * @param roleId
	 * @param userId
	 * @return
	 */
	int userRoleCount(@Param("userId")String userId);

	/**
	 * 根据ID 获取信息
	 * @param roleId
	 * @return
	 */
	SysRole getRoleById(@Param("roleId")String roleId);

	/**
	 * 修改
	 * @param role
	 * @return
	 */
	int updateRole(SysRole role);

	/**
	 * 修改用户角色关联
	 * @param roleId
	 * @param userId
	 * @return
	 */
	int updateUserRole(@Param("roleId2")String roleId2,@Param("roleId")String roleId, @Param("userId")String userId);


}
