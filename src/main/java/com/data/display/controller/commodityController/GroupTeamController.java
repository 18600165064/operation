package com.data.display.controller.commodityController;

import com.alibaba.fastjson.JSON;
import com.data.display.mapper.commodityMapper.SpuCommodityMapper;
import com.data.display.model.commodity.SpuCommodity;
import com.data.display.service.commodityService.GroupTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;


@Controller
@RequestMapping("/groupTeam")
public class GroupTeamController {

    @Autowired
    GroupTeamService groupTeamService;

    @Resource
    SpuCommodityMapper spuCommodityMapper;




    @RequestMapping("/getSpuData")
    @ResponseBody
    public String getSpuData() {
        List<SpuCommodity> spuCommodityList = spuCommodityMapper.selectAll();
        return JSON.toJSONString(spuCommodityList);
    }




}
