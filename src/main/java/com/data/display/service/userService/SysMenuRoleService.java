package com.data.display.service.userService;

import java.util.List;

import com.data.display.model.dto.DataTableResult;
import com.data.display.model.user.SysMenuRole;

public interface SysMenuRoleService {

	/**
	 * 获取所有菜单
	 */
	List<SysMenuRole> getMenuRoleData(String roleId);

	/**
	 * 更改角色菜单
	 * @param menuIds
	 * @param roleId
	 * @return
	 */
	DataTableResult updateMenuByRoleId(String menuIds, String roleId);

}
