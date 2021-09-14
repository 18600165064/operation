package com.data.display.service.userService;

import java.util.Map;

import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.supplier.SupplierInfo;
import com.data.display.model.user.StaffRelation;
import com.data.display.model.user.SysUser;
import com.github.pagehelper.Page;

/**
 * @Author: CYN
 * @Date: 2019/1/14 17:56
 * @Description:
 */
public interface UserService {
    /**
     * 修改密码
     * @param userId
     * @param password
     */
    void updatePassword(String userId,String password);

    /**
     * 获取用户信息
     * @param dataTableDTO
     * @param userName
     * @return
     */
	Page<Map<String, Object>> getUserData(DataTableDTO dataTableDTO,String userName);

	/**
	 * 修改
	 * @param user
	 * @return
	 */
	DataTableResult updateUser(SysUser user);

	/**
	 * 根据名字获取到相对应的所有用户
	 * @param dataTableDTO
	 * @param nickName
	 * @return
	 */
	Page<Map<String, Object>> getDatasByNickname(DataTableDTO dataTableDTO, String nickName);

	/**
	 * 添加用户
	 * @param types 
	 * @param sysUser
	 * @param staffRelation
	 * @return
	 */
	DataTableResult addUserData(SupplierInfo supplierInfo, String types);
}
