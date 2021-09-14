package com.data.display.service.platformService;

import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.order.YMGrouper;
import com.data.display.model.platform.BonusParents;
import com.data.display.model.user.UserInfo;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface PlatformService {

    Map<String, Object> getPlatformData(String strDate);

    String getToken();

    Map<String, Object> getPersonCountData(String beginDate,String endDate);

    Map<String, Object> getOrderCountData(String beginDate,String endDate,String spuid);

    Map<String, Object> getBonusCountData(String beginDate,String endDate,String spuid);

//    List<Map<String,Object>> getBonusParentsData2(String strDate,String parentsId);

    Page<BonusParents> getBonusParentsData(DataTableDTO dataTableDTO, String beginDate,String endDate, String parentsId,String spuid);

    Page<BonusParents> getBonusSupplerData(DataTableDTO dataTableDTO, String beginDate, String endDate, String parentsId,String spuid);

    List<Map<String,Object>> getBonusEstimateData(String amount,String spuid);

    DataTableResult updatePoolAmount(String amount,String type,String spuid);

    List<Map<String, Object>> getBonusSupplierEstimateData(String amount,String spuid);

    Page<YMGrouper> getGrouperData(DataTableDTO dataTableDTO, YMGrouper grouper);

    List<UserInfo> getNewPersionData(String beginDate, String endDate, String parentsId, String spuid);
}
