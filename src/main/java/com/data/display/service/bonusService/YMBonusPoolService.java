package com.data.display.service.bonusService;

import com.data.display.model.bonus.BonusPool;
import java.util.List;

public interface YMBonusPoolService {

    BonusPool getData(BonusPool bonusPool);

    List<BonusPool> getDataBySpuid(BonusPool bonusPool);

    Integer updateBonusPool(BonusPool bonusPool);
}
