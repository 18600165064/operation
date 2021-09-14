package com.data.display.mapper.userMapper;


import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface YmAssembleRefundMapper {

    /**
     * 获取超限的所有用户
     *
     * @return list
     */
    List<Map<String,Object>> getTransfiniteUser();

    /**
     * 更新返现状态
     * @param id  主键id
     * @param status 状态
     * @return int
     */
    int updateYmAssembleRefundStatus(@Param("id") int id, @Param("status") int status);
}