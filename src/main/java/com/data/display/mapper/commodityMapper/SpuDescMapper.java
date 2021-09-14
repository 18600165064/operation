package com.data.display.mapper.commodityMapper;

import com.data.display.model.commodity.SpuDesc;

public interface SpuDescMapper {

	SpuDesc selectByOther(SpuDesc spuDesc);
	
	int addSpuDesc(SpuDesc spuDesc);
	
	int updateSpuDesc(SpuDesc spuDesc);
	
}
