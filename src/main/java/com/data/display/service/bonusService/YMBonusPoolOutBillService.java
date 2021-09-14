package com.data.display.service.bonusService;

import com.data.display.model.bonus.BonusPoolOutBill;

import java.util.List;

public interface YMBonusPoolOutBillService {

    Integer addPoolOutBill(BonusPoolOutBill bonusPoolOutBill);

    List<BonusPoolOutBill> getData(String pool_type, String create_time);

    List<BonusPoolOutBill> getData2(String pool_type, String create_time);

    Integer updateOutBill(BonusPoolOutBill bonusPoolOutBill);

    Integer updateOutBillByUserid(BonusPoolOutBill bonusPoolOutBill);

    List<BonusPoolOutBill> getData3(String pool_type,String user_id, String beginDate,String endDate,String spuid);

    List<BonusPoolOutBill> getData4(String user_id, String create_time,String pool_type);

}
