package com.data.display.mapper.platformMapper;

import com.data.display.model.bonus.BonusPartener;
import com.data.display.model.order.Order;
import com.data.display.model.platform.BonusParents;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PlatformMapper {

    Map<String, Object> getPlatformData(@Param("strDate") String strDate, @Param("yesterday")String yesterday,@Param("beforeday")String beforeday);

    List<Order> selectOrderByUser(String id);

    List<Order> selectOrderByUser2(@Param("id")String id,@Param("strDate")String strDate);

    Map<String, Object> getPersonCountData(@Param("beginDate")String beginDate, @Param("endDate")String endDate);

    Map<String, Object> getOrderCountData(@Param("beginDate")String beginDate,@Param("endDate")String endDate,@Param("yesterday")String yesterday,@Param("spuid")String spuid);

    Map<String, Object> getBonusCountData(@Param("beginDate")String beginDate,@Param("endDate")String endDate, @Param("yesterday")String yesterday,@Param("spuid")String spuid);

    Page<BonusParents> getBonusParentsData(@Param("beginDate")String beginDate,@Param("endDate")String endDate, @Param("parentsId")String parentsId,@Param("spuid")String spuid);

    Page<BonusParents> getBonusSupplerData(@Param("beginDate")String beginDate,@Param("endDate")String endDate, @Param("parentsId")String parentsId,@Param("spuid")String spuid);
}
