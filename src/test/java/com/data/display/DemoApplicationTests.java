package com.data.display;

import com.data.display.mapper.orderMapper.OrderDetailMapper;
import com.data.display.mapper.orderMapper.OrderMapper;
import com.data.display.mapper.orderMapper.RebateMapper;
import com.data.display.mapper.richMapper.InformationMapper;
import com.data.display.mapper.userMapper.PermissionMapper;
import com.data.display.mapper.userMapper.UserMapper;
import com.data.display.model.bonus.BonusPoolOutBill;
import com.data.display.model.order.Order;
import com.data.display.model.order.OrderDetail;
import com.data.display.model.rich.Information;
import com.data.display.model.user.YmAcountBill;
import com.data.display.quartz.UserOrderSettleJob;
import com.data.display.service.IJobAndTriggerService;
import com.data.display.service.bonusService.YMBonusPoolOutBillService;
import com.data.display.service.orderService.impl.OrderServiceImpl;
import com.data.display.util.DateUtil;
import com.github.pagehelper.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(DemoApplicationTests.class);

    @Resource
    private OrderMapper orderMapper;

    @Autowired
    private YMBonusPoolOutBillService bonusPoolOutBillService;

    @Resource
    private OrderDetailMapper orderDetailMapper;


    @Autowired
    IJobAndTriggerService jobAndTriggerService;

    @Test
    public void contextLoads() throws Exception {
//        Order order = orderMapper.selectById("7306");
//        OrderDetail orderDetail = new OrderDetail();
//        orderDetail.setSub_order_no(order.getSub_order_no());
//        String time = DateUtil.dateToString(order.getPay_time(),DateUtil.YYYY_MM_DD);
//        logger.info("《=====合伙人和股东退款时间======》"+time);
//        //获取子订单对应的详情
//        List<OrderDetail> detailList = orderDetailMapper.selectByOthers(orderDetail);
//        if (order.getStockerholder_id() != 0){
//            List<BonusPoolOutBill> billList =  bonusPoolOutBillService.getData3(String.valueOf(order.getStockerholder_id()),time);
//            for (int i = 0; i < billList.size(); i++) {
//                if (detailList != null && detailList.size() > 0){
//                    BigDecimal num = new BigDecimal(detailList.get(0).getNum());
//                    BigDecimal deduct = billList.get(i).getDeduct_amount();
//                    billList.get(i).setDeduct_amount(deduct.add(billList.get(i).getEven_bonus().multiply(num)));
//                    bonusPoolOutBillService.updateOutBill(billList.get(i));
//                }
//            }
//        }
//        if (order.getPartener_id() != 0){
//            List<BonusPoolOutBill> billList =  bonusPoolOutBillService.getData3(String.valueOf(order.getPartener_id()),time);
//            for (int i = 0; i < billList.size(); i++) {
//                if (detailList != null && detailList.size() > 0){
//                    BigDecimal num = new BigDecimal(detailList.get(0).getNum());
//                    BigDecimal deduct = billList.get(i).getDeduct_amount();
//                    billList.get(i).setDeduct_amount(deduct.add(billList.get(i).getEven_bonus().multiply(num)));
//                    bonusPoolOutBillService.updateOutBill(billList.get(i));
//                }
//            }
//        }

        jobAndTriggerService.deleteJob("bonusJob","bonusJob");


    }

}

