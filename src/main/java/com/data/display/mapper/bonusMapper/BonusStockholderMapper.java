package com.data.display.mapper.bonusMapper;

import com.data.display.model.bonus.BonusStockholder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BonusStockholderMapper {

    List<BonusStockholder> getData(BonusStockholder bonusStockholder);

    List<BonusStockholder> getData3(BonusStockholder bonusStockholder);

    /**
     * 根据spuid查询昨天有该spu销量的股东
     * @param spuid
     * @param time
     * @return
     */
    List<BonusStockholder> getDataBySpuid(@Param("spuid") String spuid, @Param("time") String time);

    Integer getCountBySpuid(@Param("spuid") String spuid, @Param("time") String time);

    Integer updateStoc(BonusStockholder bonusStockholder);
}
