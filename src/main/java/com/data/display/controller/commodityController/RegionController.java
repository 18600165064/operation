package com.data.display.controller.commodityController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.data.display.model.commodity.Region;
import com.data.display.service.commodityService.RegionService;


/**
 * 地址
 * @author l
 *
 */
@Controller
@RequestMapping("/region")
public class RegionController {

	@Autowired
	private RegionService regionService;

	@RequestMapping("/allData")
    @ResponseBody
    public String allData(Region region) {
		List<Region> list = regionService.getAllData(region);
        return JSON.toJSONString(list);
    }
	
	
	
	@RequestMapping("/list")
    @ResponseBody
    public String getRegionData(Region region) {
		List<Region> list = regionService.list(region);
        return JSON.toJSONString(list);
    }
	
}
