package com.data.display.service.supplierService.impl;

import javax.annotation.Resource;

import com.data.display.mapper.userMapper.UserMapper;
import com.data.display.util.StringUtil;
import org.springframework.stereotype.Service;

import com.data.display.mapper.supplierMapper.SupplierInfoMapper;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.supplier.SupplierInfo;
import com.data.display.model.user.SysUser;
import com.data.display.service.supplierService.SupplierInfoService;
import com.data.display.util.UserUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service("supplierInfoService")
public class SupplierInfoServiceImpl implements SupplierInfoService{

	@Resource
    private SupplierInfoMapper supplierInfoMapper;

	@Resource
	private UserMapper userMapper;

	@Override
	public Page<SupplierInfo> getSupplierInfoData(DataTableDTO dataTableDTO, SupplierInfo supplierInfo) {
		SysUser user = UserUtil.getUserMessage();
		supplierInfo.setJd_user_id(user.getId());
		PageHelper.startPage(dataTableDTO.getPageNum(), dataTableDTO.getLength());
		return supplierInfoMapper.getSupplierInfoData(supplierInfo);
	}

	@Override
	public SupplierInfo selectById(String id) {
		return supplierInfoMapper.selectById(id);
	}

	@Override
	public DataTableResult updateSupplierInfo(SupplierInfo supplierInfo) {
		DataTableResult result = new DataTableResult();
		try {
			if (StringUtil.isNotBlank(supplierInfo.getS_phone())){
				SysUser user = new SysUser();
				user.setPhone(supplierInfo.getS_phone());
				user.setNick_name(supplierInfo.getS_name());
				user.setId(supplierInfo.getJd_user_id());
				userMapper.updateUser(user);
			}
			result.setError("修改成功");
			result.setDraw(supplierInfoMapper.updateSupplierInfo(supplierInfo));
		} catch (Exception e) {
			result.setError("修改失败");
		}
		return result;
	}
	
}
