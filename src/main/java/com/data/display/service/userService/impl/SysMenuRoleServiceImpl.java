package com.data.display.service.userService.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.data.display.mapper.userMapper.SysMenuRoleMapper;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.user.SysMenuRole;
import com.data.display.service.userService.SysMenuRoleService;

@Service
public class SysMenuRoleServiceImpl implements SysMenuRoleService{

	private static final Logger logger = LoggerFactory.getLogger(SysMenuRoleServiceImpl.class);

	@Resource
	private SysMenuRoleMapper sysMenuRoleMapper;
	
	/**
	 * 获取所有菜单
	 */
	@Override
	public List<SysMenuRole> getMenuRoleData(String roleId) {
		List<SysMenuRole> roleData = sysMenuRoleMapper.getMenuRoleData(); 
		List<SysMenuRole> isCheckedData = sysMenuRoleMapper.isCheckedData(roleId);
		for (int i = 0; i < roleData.size(); i++) {
			for (int j = 0; j < isCheckedData.size(); j++) {
				if(roleData.get(i).getMenu_name().equals(isCheckedData.get(j).getMenu_name()) &&
				    roleData.get(i).getMenu_level().equals(isCheckedData.get(j).getMenu_level())
				  ){
					roleData.get(i).setIsChecked(true);
				}
			}
		}
		return roleData;
	}

	/**
	 * 更改角色菜单
	 */
	@Override
	public DataTableResult updateMenuByRoleId(String menuIds, String roleId) {
		DataTableResult result = new DataTableResult();
		try {
			result.setError("编辑成功");
			int rId = Integer.parseInt(roleId);
			String[] ids = menuIds.split(",");
			List<Map<String,Object>> map = sysMenuRoleMapper.countMenu();
			List<SysMenuRole> isCheckedData = sysMenuRoleMapper.isCheckedData(roleId);
			for (int i = 0; i < map.size(); i++) {
				for (int j = 0; j < isCheckedData.size(); j++) {
					if(map.get(i).get("menuName").equals(isCheckedData.get(j).getMenu_name())){
						int count = Integer.parseInt(String.valueOf(map.get(i).get("count"))); 
						if(count > 1){
							result.setDraw(sysMenuRoleMapper.delete(isCheckedData.get(j).getId()));
						}
					}
				}
			}
			int parentId = 0;
			for (int i = 0; i < ids.length; i++) {
				SysMenuRole menuRole = sysMenuRoleMapper.getById(ids[i]);
				if(menuRole != null){
					menuRole.setRole(rId);
					menuRole.setId(null);
					result.setDraw(sysMenuRoleMapper.insert(menuRole));
					System.out.println(menuRole.getId());
					if(menuRole.getMenu_level() == 1){
						parentId = menuRole.getId();
					}
					menuRole.setMenu_parent(parentId);
					sysMenuRoleMapper.update(menuRole);
				}
			}
		} catch (Exception e) {
			result.setError("编辑失败");
			logger.error("菜单编辑失败",e);
		}
		return result;
	}

}
