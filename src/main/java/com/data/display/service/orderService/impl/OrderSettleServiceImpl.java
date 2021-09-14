package com.data.display.service.orderService.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.data.display.mapper.orderMapper.OrderSettleMapper;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.order.OrderSettle;
import com.data.display.service.orderService.OrderSettleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service("orderSettleService")
public class OrderSettleServiceImpl implements OrderSettleService{

	@Resource
    private OrderSettleMapper orderSettleMapper;
	
	@Override
	public Page<OrderSettle> getOrderSettleData(DataTableDTO dataTableDTO,OrderSettle orderSettle) {
		PageHelper.startPage(dataTableDTO.getPageNum(), dataTableDTO.getLength());
		return orderSettleMapper.getOrderSettleData(orderSettle);
	}

}
