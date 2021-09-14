package com.data.display.service.userService.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.data.display.mapper.supplierMapper.SupplierFinaceMapper;
import com.data.display.mapper.supplierMapper.SupplierInfoMapper;
import com.data.display.mapper.userMapper.StaffRelationMapper;
import com.data.display.mapper.userMapper.UserMapper;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.supplier.SupplierFinace;
import com.data.display.model.supplier.SupplierInfo;
import com.data.display.model.user.SysUser;
import com.data.display.service.userService.UserService;
import com.data.display.util.StringUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * @Author: CYN
 * @Date: 2019/1/14 17:56
 * @Description:
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;

    @Resource
    private StaffRelationMapper staffRelationMapper;
    
    @Resource
    private SupplierInfoMapper supplierInfoMapper;
    
    @Resource
    private SupplierFinaceMapper supplierFinaceMapper;

    /**
     * 修改密码
     */
    @Override
    public void updatePassword(String userId, String password) {
        userMapper.updatePassword(userId, password);
    }

    /**
     * 获取用户信息
     */
	@Override
	public Page<Map<String, Object>> getUserData(DataTableDTO dataTableDTO,String userName) {
		PageHelper.startPage(dataTableDTO.getPageNum(), dataTableDTO.getLength());
        Page<Map<String, Object>> roleData = userMapper.getUserData(userName);
		return roleData;
	}

	
	/**
	 * 修改
	 */
	@Override
	public DataTableResult updateUser(SysUser user) {
		DataTableResult result = new DataTableResult();
		SupplierInfo supplierInfo = new SupplierInfo();
		try {
			SysUser user3 = userMapper.findByUserId(String.valueOf(user.getId()));
			if(user3.getUsername().equals(user.getUsername())){
				result.setDraw(userMapper.updateUser(user));
				if(result.getDraw() == 1){
					supplierInfo.setJd_user_id(user.getId());
					supplierInfo.setS_name(user.getNick_name());
					supplierInfo.setS_manager(user.getUsername());
					supplierInfo.setS_phone(user.getPhone());
					result.setDraw(supplierInfoMapper.updateSupplierInfoByUserId(supplierInfo));
				}
				result.setError("修改成功");
			}else{
				SysUser user2 = userMapper.findByUserName(user.getUsername());
				if(user2 == null){
					result.setDraw(userMapper.updateUser(user));
					if(result.getDraw() == 1){
						supplierInfo.setJd_user_id(user.getId());
						supplierInfo.setS_name(user.getNick_name());
						supplierInfo.setS_manager(user.getUsername());
						supplierInfo.setS_phone(user.getPhone());
						result.setDraw(supplierInfoMapper.updateSupplierInfoByUserId(supplierInfo));
					}
					result.setError("修改成功");
				}else{
					result.setError("用户名已存在");
				}
			}
		} catch (Exception e) {
			result.setError("修改失败");
			logger.error("修改用户信息异常",e);
		}
		return result;
	}

	/**
	 * 根据名字获取到相对应的所有用户
	 */
	@Override
	public Page<Map<String, Object>> getDatasByNickname(DataTableDTO dataTableDTO, String nickName) {
		if(StringUtil.isNotBlank(nickName)){
			PageHelper.startPage(dataTableDTO.getPageNum(), dataTableDTO.getLength());
			Page<Map<String, Object>> roleData = userMapper.getDatasByNickname(nickName);
			return roleData;
		}
		return new Page<Map<String, Object>>();
	}

	/**
	 * 添加用户
	 * @param sysUser
	 * @param staffRelation
	 * @return
	 */
	@Override
	public DataTableResult addUserData(SupplierInfo supplierInfo,String types) {
		DataTableResult result = new DataTableResult();
		SysUser sysUser = new SysUser();
		try {
			SysUser user = userMapper.findByUserName(supplierInfo.getS_manager());
			if(user == null){
				result.setError("添加成功");
				BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
				//密码加密
		        String pwd = bCryptPasswordEncoder.encode("123456");
		        sysUser.setPassword(pwd);
		        sysUser.setNick_name(supplierInfo.getS_name());
		        sysUser.setUsername(supplierInfo.getS_manager());
		        sysUser.setPhone(supplierInfo.getS_phone());
		        sysUser.setCreate_date(new Date());
	    		result.setDraw(userMapper.addUserData(sysUser));
	    		if("1".equals(types)){
	    			 if(result.getDraw() == 1){
	    				 BigDecimal num = BigDecimal.ZERO;
			        	supplierInfo.setCreate_time(new Date());
			        	supplierInfo.setJd_user_id(sysUser.getId());
			        	result.setDraw(supplierInfoMapper.addSupplierInfo(supplierInfo));
			        	SupplierFinace supplierFinace = new SupplierFinace();
			        	supplierFinace.setSid(supplierInfo.getS_id());
			        	supplierFinace.setAlready_withdraw(num);
			        	supplierFinace.setBond(num);
			        	supplierFinace.setIncome(num);
			        	supplierFinace.setFrozen(num);
			        	supplierFinace.setCan_withdraw(num);
			        	supplierFinace.setRefund(num);
			        	supplierFinace.setCreate_time(new Date());
			        	supplierFinaceMapper.addSupplierFinace(supplierFinace);
			        }
	    		}
			}else{
				result.setError("用户名已存在");
			}
		} catch (Exception e) {
			result.setError("添加失败");
			logger.error("添加用户发生异常",e);
		}
		return result;
	}
}
