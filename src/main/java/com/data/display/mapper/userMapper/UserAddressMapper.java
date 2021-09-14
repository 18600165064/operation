package com.data.display.mapper.userMapper;

import com.data.display.model.user.UserAddress;
import com.data.display.model.user.UserWithdraw;
import com.github.pagehelper.Page;

public interface UserAddressMapper {

	UserAddress selectById(String id);
}
