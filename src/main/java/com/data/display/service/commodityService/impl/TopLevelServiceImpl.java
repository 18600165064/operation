package com.data.display.service.commodityService.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.data.display.mapper.commodityMapper.SpuCommodityMapper;
import com.data.display.mapper.commodityMapper.TopLevelMapper;
import com.data.display.mapper.userMapper.UserInfoMapper;
import com.data.display.model.commodity.SpuCommodity;
import com.data.display.model.commodity.TopLevel;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.user.UserInfo;
import com.data.display.service.commodityService.TopLevelService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class TopLevelServiceImpl implements TopLevelService{

	@Resource
    private TopLevelMapper topLevelMapper;
	
	@Resource
	 private UserInfoMapper userInfoMapper;
	
	@Resource
    private SpuCommodityMapper spuCommodityMapper;

	@Override
	public Page<Map<String, Object>> getTopLevelData(DataTableDTO dataTableDTO, UserInfo userIfo) {
		PageHelper.startPage(dataTableDTO.getPageNum(), dataTableDTO.getLength());
		return userInfoMapper.getTopDataPage(userIfo);
	}

	@Override
	public DataTableResult addTopLevel(String userIds, String spuid) {
		DataTableResult result = new DataTableResult();
		try {
			SpuCommodity spuCommodity = spuCommodityMapper.selectById(spuid);
			if(spuCommodity != null){
				result.setDraw(topLevelMapper.deleteBySpuid(spuCommodity.getSpuid()));
				result.setError("保存成功");
				String ids[] = userIds.split(",");
				for (int i = 0; i < ids.length; i++) {
					TopLevel topLevel = new TopLevel();
					topLevel.setSpuid(spuCommodity.getSpuid());
					topLevel.setCreate_time(new Date());
					topLevel.setStatus(0);
					topLevel.setIdentity(1);
					topLevel.setUser_id(Integer.parseInt(ids[i]));
					result.setDraw(topLevelMapper.addTopLevel(topLevel));
				}
			}else{
				result.setError("系统错误");
				return result;
			}
		} catch (Exception e) {
			result.setError("保存失败");
		}
		return result;
	}

	@Override
	public List<TopLevel> selectBySpuid(TopLevel topLevel) {
		return topLevelMapper.selectBySpuid(topLevel);
	}

}
