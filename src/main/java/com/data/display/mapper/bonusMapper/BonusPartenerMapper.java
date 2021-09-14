package com.data.display.mapper.bonusMapper;

import com.data.display.model.bonus.BonusPartener;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BonusPartenerMapper {

    List<BonusPartener> getData(BonusPartener bonusPartener);

    List<BonusPartener> getData3(BonusPartener bonusPartener);

    Integer getCountBySpuid(@Param("spuid")String spuid,@Param("time") String time);

    Page<BonusPartener> getData2(BonusPartener bonusPartener);

    Integer updatePar(BonusPartener bonusPartener);

    /**
     * 根据spuid查询昨天有该spu销量的合伙人
     * @param spuid
     * @param time
     * @return
     */
    List<BonusPartener> getDataBySpuid(@Param("spuid")String spuid,@Param("time") String time);
}
