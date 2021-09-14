package com.data.display.service.userService;

import java.util.List;
import java.util.Map;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.user.UserInfo;
import com.github.pagehelper.Page;

public interface UserInfoService {

	Page<Map<String, Object>> getUserInfoData(DataTableDTO dataTableDTO, UserInfo userInfo);

	DataTableResult updateUserInfo(UserInfo userInfo, String type,String spuid);

	 List<UserInfo> getTopData(UserInfo userInfo);

	UserInfo selectById(String user_id);

}
