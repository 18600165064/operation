package com.data.display.service.bonusService.Impl;

import com.data.display.mapper.bonusMapper.BonusPartenerMapper;
import com.data.display.model.bonus.BonusPartener;
import com.data.display.service.bonusService.YMBonusPartenerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class YMBonusPartenerServiceImpl implements YMBonusPartenerService {

    @Resource
    BonusPartenerMapper bonusPartenerMapper;

    @Override
    public List<BonusPartener> getData(BonusPartener bonusPartener) {
        List<BonusPartener> list = bonusPartenerMapper.getData(bonusPartener);
        return list;
    }

    @Override
    public Integer getCountBySpuid(String spuid,String time) {
        return bonusPartenerMapper.getCountBySpuid(spuid,time);
    }

}
