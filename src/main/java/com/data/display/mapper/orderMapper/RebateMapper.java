package com.data.display.mapper.orderMapper;

import java.util.List;

import com.data.display.model.order.Rebate;
import com.data.display.model.user.YmAcountBill;

public interface RebateMapper {

	List<Rebate> selectRebateByOthers(Rebate rebate);

	Integer updateRebate(Rebate rebate);

	Integer updateYmAcountBill(YmAcountBill ymAcountBill);

	List<Rebate> test(Rebate rebate);
}
