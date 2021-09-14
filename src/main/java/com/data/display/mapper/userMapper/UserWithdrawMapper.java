package com.data.display.mapper.userMapper;

import com.data.display.model.user.UserWithdraw;
import com.github.pagehelper.Page;

public interface UserWithdrawMapper {

	Page<UserWithdraw> getUserWithdrawData(UserWithdraw userWithdraw);

	UserWithdraw selectById(Integer id);

	Integer updateUserWithdraw(UserWithdraw userWithdraw);

}
