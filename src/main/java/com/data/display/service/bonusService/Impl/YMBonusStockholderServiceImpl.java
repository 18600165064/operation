package com.data.display.service.bonusService.Impl;

import com.data.display.mapper.bonusMapper.BonusStockholderMapper;
import com.data.display.model.bonus.BonusStockholder;
import com.data.display.service.bonusService.YMBonusStockholderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class YMBonusStockholderServiceImpl implements YMBonusStockholderService {

    @Resource
    BonusStockholderMapper bonusStockholderMapper;

    @Override
    public List<BonusStockholder> getData(BonusStockholder bonusStockholder) {
        List<BonusStockholder> list = bonusStockholderMapper.getData(bonusStockholder);
        return list;
    }

    @Override
    public Integer getCountBySpuid(String spuid,String time) {
        return bonusStockholderMapper.getCountBySpuid(spuid,time);
    }
}
