package com.data.display.service.commodityService.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.data.display.mapper.commodityMapper.RegionMapper;
import com.data.display.model.commodity.Region;
import com.data.display.service.commodityService.RegionService;

import net.sf.json.JSONArray;

@Service
public class RegionServiceImpl implements RegionService{

	@Resource
    private RegionMapper regionMapper;

	@Override
	public List<Region> list(Region region) {
		List<Region> array = new ArrayList<>();
		List<Region> list = regionMapper.getRegionData(region);
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getRegion_id().equals(list.get(i).getRegion_path())){
				List<Region> regionList = new ArrayList<>();
				for (int j = 0; j < list.size(); j++) {
					if(list.get(j).getRegion_path().equals(list.get(i).getRegion_id()) && list.get(j).getRegion_id() != list.get(i).getRegion_id()){
						regionList.add(list.get(j));
					}
				}
				list.get(i).setRegionList(regionList);
				array.add(list.get(i));
			}
		}
		return array;
	}

	@Override
	public List<Region> getAllData(Region region) {
		List<Region> oneList = regionMapper.getOneData(region);
		 JSONArray jsonArrayTemp = null;
         JSONObject jsonObjectTemp = null;
         JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < oneList.size(); i++) {
			
			JSONObject jsonObject = new JSONObject();
            jsonObject.put(String.valueOf(oneList.get(i).getRegion_id()), oneList.get(i).getLocal_name());
            jsonArrayTemp = new JSONArray();
			
			List<Region> twoList = regionMapper.getTwoData(oneList.get(i));
			for (int j = 0; j < twoList.size(); j++) {
				 jsonObjectTemp = new JSONObject();
                 jsonObjectTemp.put(String.valueOf(twoList.get(j).getRegion_id()), twoList.get(j).getLocal_name());
                 jsonArrayTemp.add(jsonObjectTemp);
			}
			if (jsonArrayTemp.size() > 0) {
                jsonObject.put("childCity", jsonArrayTemp);
            }
            jsonArray.add(jsonObject);
		}
		
		return jsonArray;
	}
	
}
