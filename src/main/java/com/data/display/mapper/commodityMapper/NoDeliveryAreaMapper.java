package com.data.display.mapper.commodityMapper;

import com.data.display.model.commodity.NoDeliveryArea;

public interface NoDeliveryAreaMapper {

	int addNoDeliveryAreaData(NoDeliveryArea noDeliveryArea);

	NoDeliveryArea selectByShippingId(String id);

	int updateNoDelivery(NoDeliveryArea noDeliveryArea);

}
