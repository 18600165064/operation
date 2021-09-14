package com.data.display.mapper.userMapper;


import java.util.List;

import com.data.display.model.user.Permission;
/**
 *
 * 功能描述:
 * @author CYN
 * @date 2019/1/15 9:52
 */
public interface PermissionMapper {
    /**
     * 获取所有权限
     * @return
     */
     List<Permission> findAll();

    /**
     * 根据用户id获取权限
     * @param userId
     * @return
     */
     List<Permission> findByAdminUserId(int userId);
}
