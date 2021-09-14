package com.data.display.mapper.userMapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.data.display.model.user.SysUser;
import com.github.pagehelper.Page;
/**
 *
 * 功能描述:
 * @author CYN
 * @date 2019/1/15 9:52
 */
public interface UserMapper {
     /**
      * 通过名称获取用户
      * @param username
      * @return
      */
     SysUser findByUserName(@Param("username")String username);

     /**
      * 通过id用户
      * @param userid
      * @return
      */
     SysUser findByUserId(@Param("userid")String userid);

     /**
      * 修改密码
      * @param userId
      * @param password
      */
    void updatePassword(@Param("userId")String userId, @Param("password")String password);

    /**
     * 获取用户信息
     * @param userName
     * @return
     */
	Page<Map<String, Object>> getUserData(@Param("username")String username);

	/**
	 * 新增用户
	 * @param sysUser
	 * @return
	 */
	int addUserData(SysUser sysUser);
	
	/**
	 * 修改
	 * @param user
	 * @return
	 */
	int updateUser(SysUser user);

	/**
	 * 根据名字获取到相对应的所有用户
	 * @param nickName
	 * @return
	 */
	Page<Map<String, Object>> getDatasByNickname(String nickName);

}
