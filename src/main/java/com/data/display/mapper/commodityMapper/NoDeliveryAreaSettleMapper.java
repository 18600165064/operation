package com.data.display.mapper.commodityMapper;

import com.data.display.model.commodity.NoDeliveryAreaSettle;

public interface NoDeliveryAreaSettleMapper {

	int addNoDeliveryAreaSettleData(NoDeliveryAreaSettle noDeliveryAreaSettle);

	NoDeliveryAreaSettle selectByShippingId(String id);

	int updateNoDelivery(NoDeliveryAreaSettle noDeliveryAreaSettle);

}
