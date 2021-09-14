package com.data.display.mapper.userMapper;


import java.util.List;
import java.util.Map;
import com.data.display.model.user.SysMenuRole;
import com.github.pagehelper.Page;
import io.lettuce.core.dynamic.annotation.Param;
/**
 *
 * 功能描述:
 * @author CYN
 * @date 2019/1/15 9:52
 */
public interface SysMenuRoleMapper {
    /**
     *
     * 根据用户id获取菜单
     * @param userId
     * @return
     */
    List<SysMenuRole> getSysMenu(int userId);

    /**
     * 获取所有的菜单
     * @return
     */
	Page<SysMenuRole> getMenuRoleData();

	/**
	 * 根据角色ID获取拥有的菜单
	 * @param userId
	 * @return
	 */
	List<SysMenuRole> isCheckedData(@Param("roleId")String roleId);
	/**
	 * 根据ID获取信息
	 * @param id
	 * @return
	 */
	SysMenuRole getById(@Param("id")String id);
	/**
	 * 修改
	 * @param menuRole
	 * @return
	 */
	Integer update(SysMenuRole menuRole);
	/**
	 * 新增
	 * @param menuRole
	 * @return
	 */
	Integer insert(SysMenuRole menuRole);
	/**
	 * 统计所有的菜单
	 * @return
	 */
	List<Map<String, Object>> countMenu();

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	Integer delete(@Param("id")Integer id);
	
}