package com.data.display.service.bonusService.Impl;

import com.data.display.mapper.bonusMapper.BonusPoolOutBillMapper;
import com.data.display.model.bonus.BonusPoolOutBill;
import com.data.display.service.bonusService.YMBonusPoolOutBillService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class YMBonusPoolOutBillServiceImpl implements YMBonusPoolOutBillService {

    @Resource
    BonusPoolOutBillMapper bonusPoolOutBillMapper;

    @Override
    public Integer addPoolOutBill(BonusPoolOutBill bonusPoolOutBill) {
        return bonusPoolOutBillMapper.addPoolOutBill(bonusPoolOutBill);
    }

    @Override
    public List<BonusPoolOutBill> getData(String pool_type, String create_time) {
        return bonusPoolOutBillMapper.getData(pool_type,create_time);
    }

    @Override
    public List<BonusPoolOutBill> getData2(String pool_type, String create_time) {
        return bonusPoolOutBillMapper.getData2(pool_type,create_time);
    }

    @Override
    public Integer updateOutBill(BonusPoolOutBill bonusPoolOutBill) {
        return bonusPoolOutBillMapper.updateOutBill(bonusPoolOutBill);
    }

    @Override
    public Integer updateOutBillByUserid(BonusPoolOutBill bonusPoolOutBill) {
        return bonusPoolOutBillMapper.updateOutBillByUserid(bonusPoolOutBill);
    }

    @Override
    public List<BonusPoolOutBill> getData3(String pool_type,String user_id, String beginDate,String endDate,String spuid) {
        return bonusPoolOutBillMapper.getData3(pool_type,user_id,beginDate,endDate,spuid);
    }

    @Override
    public List<BonusPoolOutBill> getData4(String user_id, String create_time,String pool_type) {
        return bonusPoolOutBillMapper.getData4(user_id,create_time,pool_type);
    }
}
