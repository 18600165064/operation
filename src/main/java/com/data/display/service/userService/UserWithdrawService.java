package com.data.display.service.userService;

import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.user.UserWithdraw;
import com.github.pagehelper.Page;

public interface UserWithdrawService {

	Page<UserWithdraw> getUserWithdrawData(DataTableDTO dataTableDTO, UserWithdraw userWithdraw);

	UserWithdraw selectById(Integer id);

	Integer updateUserWithdraw(UserWithdraw userWithdraw);

	DataTableResult withdraw(UserWithdraw userWithdraw);

	DataTableResult rejected(UserWithdraw userWithdraw);


}
