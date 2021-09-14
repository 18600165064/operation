package com.data.display.service.bonusService.Impl;

import com.data.display.mapper.bonusMapper.BonusPoolMapper;
import com.data.display.model.bonus.BonusPool;
import com.data.display.service.bonusService.YMBonusPoolService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class YMBonusPoolServiceImpl implements YMBonusPoolService {

    @Resource
    BonusPoolMapper bonusPoolMapper;

    @Override
    public BonusPool getData(BonusPool bonusPool) {
        bonusPool = bonusPoolMapper.getData(bonusPool);
        return bonusPool;
    }

    @Override
    public List<BonusPool> getDataBySpuid(BonusPool bonusPool) {
        List<BonusPool> list = bonusPoolMapper.getDataBySpuid(bonusPool);
        return list;
    }

    @Override
    public Integer updateBonusPool(BonusPool bonusPool) {
        return bonusPoolMapper.updateBonusPool(bonusPool);
    }
}
