package com.data.display.mapper.userMapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.data.display.model.user.UserInfoFinace;

public interface UserInfoFinaceMapper {

	List<UserInfoFinace> getData(UserInfoFinace userInfoFinace);

	UserInfoFinace selectByUserId(UserInfoFinace userInfoFinace);
	
	int updateUserInfoFinace(UserInfoFinace userInfoFinace);

	int updateUserFinaceByUserId(@Param("userId")Integer userId, @Param("canAmount")BigDecimal canAmount, @Param("userMoney")BigDecimal userMoney,@Param("alMoney")BigDecimal alMoney,@Param("withdrawTimes")Integer withdrawTimes);

	int updateUserInfoFinaceByUserid(@Param("updateId")String updateId,@Param("userId")String userId);
}
