package com.data.display.service.commodityService;

import java.util.List;
import java.util.Map;

import com.data.display.model.commodity.TopLevel;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.user.UserInfo;
import com.github.pagehelper.Page;

public interface TopLevelService {

	Page<Map<String, Object>> getTopLevelData(DataTableDTO dataTableDTO, UserInfo userIfo);

	DataTableResult addTopLevel(String userIds, String spuid);

	List<TopLevel> selectBySpuid(TopLevel topLevel);

}
