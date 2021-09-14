package com.data.display.mapper.commodityMapper;

import java.util.List;
import java.util.Map;

import com.data.display.model.commodity.Region;

/**
 * 地址
 * @author l
 *
 */
public interface RegionMapper {

	List<Region> getRegionData(Region region);

	List<Region> getOneData(Region region);

	List<Region> getTwoData(Region region);

	Region selectByRid(String id);
	
}
