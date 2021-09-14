package com.data.display.service.bonusService;

import com.data.display.model.bonus.BonusPartener;

import java.util.List;

public interface YMBonusPartenerService {

    List<BonusPartener> getData(BonusPartener bonusPartener);

    Integer getCountBySpuid(String spuid,String time);

}
