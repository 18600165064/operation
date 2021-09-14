package com.data.display.mapper.orderMapper;

import java.util.List;

import com.data.display.model.order.OrderDetail;

/**
 * 订单详情
 * @author l
 *
 */
public interface OrderDetailMapper {

	List<OrderDetail> selectByOthers(OrderDetail orderDetail);

	Integer updateOrderDetail(OrderDetail orderDetail);

	Integer updateOrderDetailByOrderNo(OrderDetail orderDetail);

}
