package com.data.display.service.userService.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.data.display.mapper.commodityMapper.GroupTeamMapper;
import com.data.display.mapper.commodityMapper.SpuCommodityMapper;
import com.data.display.model.commodity.GroupTeam;
import com.data.display.model.commodity.SpuCommodity;
import com.data.display.model.commodity.TopLevel;
import com.data.display.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.data.display.mapper.commodityMapper.TopLevelMapper;
import com.data.display.mapper.userMapper.UserInfoFinaceMapper;
import com.data.display.mapper.userMapper.UserInfoMapper;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.user.UserInfo;
import com.data.display.model.user.UserInfoFinace;
import com.data.display.service.userService.UserInfoService;
import com.data.display.util.StringUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService{

	private static final Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);
	
	 @Resource
	 private UserInfoMapper userInfoMapper;
	 
	 @Resource
	 private UserInfoFinaceMapper userInfoFinaceMapper;
	
	@Resource
    private TopLevelMapper topLevelMapper;

	@Resource
	GroupTeamMapper groupTeamMapper;

	@Resource
	SpuCommodityMapper spuCommodityMapper;

	@Override
	public Page<Map<String, Object>> getUserInfoData(DataTableDTO dataTableDTO, UserInfo userInfo) {
		PageHelper.startPage(dataTableDTO.getPageNum(), dataTableDTO.getLength());
		return userInfoMapper.getUserInfoData(userInfo);
	}

	@Override
	public DataTableResult updateUserInfo(UserInfo userInfo,String type,String spuid) {
		DataTableResult result = new DataTableResult();
		try {
			result.setError("设置成功");
			if("100".equals(type)){
				if(userInfo.getUsage_status() == 1){
					List<TopLevel> userList = topLevelMapper.selectByUserid(String.valueOf(userInfo.getUser_id()));
					if (userList.size() == 0){
						result.setError("该用户暂未绑定SPU，无需禁用");
						return result;
					}
					int count = topLevelMapper.selectMinByUserId(String.valueOf(userInfo.getUser_id()));
					if(count > 1){
						result.setDraw(userInfoMapper.updateUserInfo(userInfo));
						result.setError("禁用成功");
					}else{
						result.setError("SPU下只绑定了该账户，请绑定多个账户后再进行操作");
					}
				}if(userInfo.getUsage_status() == 0){
					result.setDraw(userInfoMapper.updateUserInfo(userInfo));
					result.setError("启用成功");
				}
			}else{
				if("4".equals(type)){
					String updateId = userInfoMapper.getMaxUserId();
					if(StringUtil.isNotBlank(updateId)){
						userInfo.setUpdateId(Integer.parseInt(updateId)+1);
						int uid = Integer.parseInt(updateId)+1;
						userInfoFinaceMapper.updateUserInfoFinaceByUserid(String.valueOf(uid),String.valueOf(userInfo.getUser_id()));
					}else{
						userInfo.setUpdateId(650);
						userInfoFinaceMapper.updateUserInfoFinaceByUserid(String.valueOf(650),String.valueOf(userInfo.getUser_id()));
					}
				}

				GroupTeam group = groupTeamMapper.selectDataBySpuid(String.valueOf(userInfo.getUser_id()));
				if (StringUtil.isNotBlank(spuid)){
					SpuCommodity spu = spuCommodityMapper.selectBySpuid(spuid);
					if (group == null){
						GroupTeam groupTeam = new GroupTeam();
						groupTeam.setSpuid(spu.getSpuid());
						groupTeam.setUser_id(userInfo.getUser_id());
						groupTeam.setCreate_time(new Date());
						groupTeam.setEnd_time(DateUtil.getAddDay(userInfo.getProtect_day()));
						groupTeamMapper.insertData(groupTeam);
					}else{
						group.setSpuid(spu.getSpuid());
						group.setUpdate_time(new Date());
						group.setEnd_time(DateUtil.getAddDay(userInfo.getProtect_day()));
						groupTeamMapper.updateGroup(group);
					}
				}else{
					if (group != null) {
						groupTeamMapper.deleteByPrimaryKey(String.valueOf(group.getId()));
					}
				}

				userInfo.setUpdate_time(new Date());
				result.setDraw(userInfoMapper.updateUserInfo(userInfo));
			}
		} catch (Exception e) {
			result.setError("设置失败");
			logger.error("设置用户属性失败",e);
		}
		return result;
	}

	@Override
	public  List<UserInfo> getTopData(UserInfo userInfo) {
		return userInfoMapper.getTopData(userInfo);
	}

	@Override
	public UserInfo selectById(String user_id) {
		return userInfoMapper.selectById(user_id);
	}
	
}
