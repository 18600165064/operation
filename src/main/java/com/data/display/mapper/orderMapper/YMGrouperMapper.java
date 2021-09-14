package com.data.display.mapper.orderMapper;

import com.data.display.model.order.YMGrouper;
import com.data.display.model.user.UserInfo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface YMGrouperMapper {

    YMGrouper selectGrouper(YMGrouper grouper);

    Integer updateGrouper(YMGrouper grouper2);

    Page<YMGrouper> selectGrouperByTime(YMGrouper grouper);

    List<UserInfo> selectNewGrouper(@Param("beginDate") String beginDate, @Param("endDate")String endDate, @Param("spuid")String spuid);
}
