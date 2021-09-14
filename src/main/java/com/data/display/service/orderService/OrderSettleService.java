package com.data.display.service.orderService;

import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.order.OrderSettle;
import com.github.pagehelper.Page;

public interface OrderSettleService {

	Page<OrderSettle> getOrderSettleData(DataTableDTO dataTableDTO,OrderSettle orderSettle);

}
