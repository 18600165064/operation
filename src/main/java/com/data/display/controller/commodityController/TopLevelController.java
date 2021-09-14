package com.data.display.controller.commodityController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.data.display.model.commodity.SpuCommodity;
import com.data.display.model.commodity.TopLevel;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.user.UserInfo;
import com.data.display.service.commodityService.SpuCommodityService;
import com.data.display.service.commodityService.TopLevelService;
import com.data.display.service.userService.UserInfoService;
import com.github.pagehelper.Page;

@Controller
@RequestMapping("/topLevel")
public class TopLevelController {

	@Autowired
	private TopLevelService topLevelService;
	
	@Autowired
	private SpuCommodityService spuCommodityService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	/**
	 * spu选择账户
	 * @param model
	 * @param spuid
	 * @return
	 */
	@RequestMapping("/topLevelMneu")
    public String topLevelMenu(Model model,String spuid) {
		model.addAttribute("spuid", spuid);
        return "/commodity/topLevelMenu";
    }
	
	/**
	 * 顶级账户列表
	 * @param model
	 * @param spuid
	 * @return
	 */
	@RequestMapping("/topLevelList")
    public String topLevelList(Model model,String spuid) {
		model.addAttribute("spuid", spuid);
        return "/commodity/topLevelList";
    }
	
	
	@RequestMapping("/getTopLevelData")
    @ResponseBody
    public String getTopLevelData(DataTableDTO dataTableDTO,UserInfo userIfo) {
		Page<Map<String, Object>> page = topLevelService.getTopLevelData(dataTableDTO,userIfo);
		DataTableResult dataTableResult = new DataTableResult(dataTableDTO.getDraw(), page.getTotal(), page.getTotal(), page.getResult());
        return JSON.toJSONString(dataTableResult);
    }
	
	
	@RequestMapping("/selectBySpuid")
    @ResponseBody
    public String selectBySpuid(TopLevel topLevel) {
		SpuCommodity spuCommodity = spuCommodityService.selectById(topLevel.getSpuid());
		List<TopLevel> list = new ArrayList<>();
		List<TopLevel> list2 = new ArrayList<>();
		if(spuCommodity != null){
			topLevel.setSpuid(spuCommodity.getSpuid());
			list = topLevelService.selectBySpuid(topLevel);
			for (TopLevel topLevel2 : list) {
				UserInfo userInfo = userInfoService.selectById(String.valueOf(topLevel2.getUser_id()));
				if(userInfo != null){
					if(userInfo.getUsage_status() == 0){
						list2.add(topLevel2);
					}
				}
			}
		}
        return JSON.toJSONString(list2);
    }
	
	/**
	 * 新增
	 * @param userIds
	 * @param spuid
	 * @return
	 */
	@RequestMapping("/addTopLevel")
    @ResponseBody
	public String addTopLevel(String userIds,String spuid) {
		DataTableResult dataTableResult = topLevelService.addTopLevel(userIds,spuid);
        return JSON.toJSONString(dataTableResult);
    }
	
	
}
