package com.data.display.mapper.bonusMapper;

import com.data.display.model.bonus.BonusPool;

import java.util.List;

public interface BonusPoolMapper {

    BonusPool getData(BonusPool bonusPool);

    List<BonusPool> getDataBySpuid(BonusPool bonusPool);

    Integer updateBonusPool(BonusPool bonusPool);
}
