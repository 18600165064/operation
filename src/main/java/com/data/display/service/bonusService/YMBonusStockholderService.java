package com.data.display.service.bonusService;

import com.data.display.model.bonus.BonusStockholder;

import java.util.List;

public interface YMBonusStockholderService {

    List<BonusStockholder> getData(BonusStockholder bonusStockholder);

    Integer getCountBySpuid(String spuid,String time);
}
