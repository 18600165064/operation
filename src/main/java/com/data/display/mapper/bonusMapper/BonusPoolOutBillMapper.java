package com.data.display.mapper.bonusMapper;

import com.data.display.model.bonus.BonusPoolOutBill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BonusPoolOutBillMapper {

    Integer addPoolOutBill(BonusPoolOutBill bonusPoolOutBill);

    List<BonusPoolOutBill> getData(@Param("pool_type") String pool_type,@Param("create_time") String create_time);

    List<BonusPoolOutBill> getData2(@Param("pool_type") String pool_type,@Param("create_time") String create_time);

    List<BonusPoolOutBill> getData3(@Param("pool_type") String pool_type,@Param("user_id") String user_id,@Param("beginDate") String beginDate,@Param("endDate") String endDate,@Param("spuid") String spuid);

    Integer updateOutBill(BonusPoolOutBill bonusPoolOutBill);

    Integer updateOutBillByUserid(BonusPoolOutBill bonusPoolOutBill);

    List<BonusPoolOutBill> getData4(@Param("user_id")String user_id, @Param("create_time")String create_time,@Param("pool_type")String pool_type);
}
