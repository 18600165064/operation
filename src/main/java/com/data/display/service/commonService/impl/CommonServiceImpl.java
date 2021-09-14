package com.data.display.service.commonService.impl;

import com.data.display.mapper.userMapper.YmAssembleRefundMapper;
import com.data.display.model.dto.DataTableResult;
import com.data.display.quartz.CashCompensateJob;
import com.data.display.service.PayService;
import com.data.display.service.commonService.CommonService;
import com.data.display.util.DateUtil;
import com.data.display.util.RedisConstantUtil;
import com.data.display.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommonServiceImpl implements CommonService {

    private static Logger _log = LoggerFactory.getLogger(CashCompensateJob.class);

    @Resource
    YmAssembleRefundMapper ymAssembleRefundMapper;
    @Autowired
    PayService payService;
    @Resource
    RedisUtil redisUtil;


    public void refundTest(){
        _log.info("CashCompensateJob star");
        List<Map<String, Object>> list = ymAssembleRefundMapper.getTransfiniteUser();
        Map<Integer, Integer> countMap = new HashMap<>(16);
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> tempMap = list.get(i);
            int userId = Integer.parseInt(tempMap.get("user_id").toString());
            if (countMap.get(userId) == null) {
                countMap.put(userId, 1);
            } else {
                int tempCount = countMap.get(userId) + 1;
                countMap.put(userId, tempCount);
            }
            //判断超次数
            if (countMap.get(userId) <= 10) {
                int id = Integer.parseInt(tempMap.get("id").toString());
                String amount = tempMap.get("refund_price").toString();
                String openId = tempMap.get("open_id_small").toString();
                String refund_order_no = tempMap.get("refund_order_no").toString();
                //返现
                DataTableResult dataTableResult = payService.balanceWithdraw(userId, openId, amount, refund_order_no, "返现补偿");
                if (dataTableResult.getDraw().equals(1)) {
                    int upStatus = ymAssembleRefundMapper.updateYmAssembleRefundStatus(id, 1);
                    _log.info("CashCompensateJob 更新" + userId + ":" + upStatus);
                }
                _log.info("CashCompensateJob 状态" + userId + ":" + dataTableResult.getError());
            } else {
                _log.info("CashCompensateJob 超次数" + userId);
                //超次数 记录redis

                if (redisUtil.hHasKey(RedisConstantUtil.SENDNUM_LIMIT, String.valueOf(userId))) {
                    int num = Integer.parseInt(redisUtil.hget(RedisConstantUtil.SENDNUM_LIMIT, String.valueOf(userId)).toString()) + countMap.get(userId);
                    redisUtil.hset(RedisConstantUtil.SENDNUM_LIMIT, String.valueOf(userId), String.valueOf(num));
                } else {
                    LocalDateTime today_end = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);//当天23点59分59秒
                    long endLong = DateUtil.remainingSeconds(today_end) + 1;
                    redisUtil.hset(RedisConstantUtil.SENDNUM_LIMIT, String.valueOf(userId), countMap.get(userId), endLong);
                }
            }
        }
    }


}
