package com.data.display.mapper.userMapper;

import java.util.List;
import java.util.Map;

import com.data.display.model.user.UserInfo;
import com.github.pagehelper.Page;

public interface UserInfoMapper {

	Page<Map<String, Object>> getUserInfoData(UserInfo userInfo);

	Integer updateUserInfo(UserInfo userInfo);

	String getMaxUserId();
	
	UserInfo selectById(String user_id);

	List<UserInfo> getTopData(UserInfo userInfo);

	Page<Map<String, Object>> getGroupTeamData(String other_status);

	List<UserInfo> getUserInfoListByOthers(UserInfo userInfo);

	Page<Map<String, Object>> getTopDataPage(UserInfo userIfo);

}
