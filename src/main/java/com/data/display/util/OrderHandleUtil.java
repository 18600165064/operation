package com.data.display.util;

import com.alibaba.fastjson.JSON;
import com.data.display.mapper.orderMapper.OrderMapper;
import com.data.display.model.order.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class OrderHandleUtil implements Runnable{

    private static Logger _log = LoggerFactory.getLogger(OrderHandleUtil.class);

    private OrderMapper orderMapper;

    public OrderHandleUtil(OrderMapper orderMapper){
        this.orderMapper = orderMapper;
    }

    @Override
    public void run() {
        List<Order> list = orderMapper.getList();
        for (int i = 0; i < list.size(); i++) {
            _log.info("更新数据单号为============="+list.get(i).getOrder_no());
            list.get(i).setOrder_status("dsh");
            try{
                orderMapper.updateByPrimaryKeySelective(list.get(i));
            }catch (Exception e){
                _log.error("更新数据错误----------"+ JSON.toJSON(e));
            }
        }
    }
}
