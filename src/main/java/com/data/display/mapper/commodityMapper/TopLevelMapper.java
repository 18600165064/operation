package com.data.display.mapper.commodityMapper;


import java.util.List;

import com.data.display.model.commodity.TopLevel;

public interface TopLevelMapper {

	Integer addTopLevel(TopLevel topLevel);

	TopLevel selectById(String id);

	int selectMinByUserId(String userId);

	List<TopLevel> selectBySpuid(TopLevel topLevel);

	List<TopLevel> selectByUserid(String userId);

	Integer deleteBySpuid(String spuid);

	Integer deleteByUserId(String userId);
}
