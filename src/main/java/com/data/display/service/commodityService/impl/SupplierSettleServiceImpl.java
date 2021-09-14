package com.data.display.service.commodityService.impl;

import com.data.display.mapper.commodityMapper.NoDeliveryAreaSettleMapper;
import com.data.display.mapper.commodityMapper.RegionMapper;
import com.data.display.mapper.commodityMapper.SupplierSettleMapper;
import com.data.display.mapper.commodityMapper.SupplierSettleSettingsMapper;
import com.data.display.mapper.supplierMapper.SupplierInfoMapper;
import com.data.display.model.commodity.*;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.supplier.SupplierInfo;
import com.data.display.service.commodityService.SupplierSettleService;
import com.data.display.util.StringUtil;
import com.data.display.util.UserUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class SupplierSettleServiceImpl implements SupplierSettleService {

	private static final Logger logger = LoggerFactory.getLogger(SupplierSettleServiceImpl.class);
	
	@Resource
    private SupplierSettleMapper supplierSettleMapper;
	
	@Resource
    private NoDeliveryAreaSettleMapper noDeliveryAreaSettleMapper;

	@Resource
	private SupplierSettleSettingsMapper supplierSettleSettingsMapper;

	@Resource
    private RegionMapper regionMapper;
	
	@Resource
	private SupplierInfoMapper supplierInfoMapper;
	
	
	@Override
	public Page<SupplierSettle> getSupplierSettleData(DataTableDTO dataTableDTO, SupplierSettle supplierSettle) {
		PageHelper.startPage(dataTableDTO.getPageNum(), dataTableDTO.getLength());
		return supplierSettleMapper.getSupplierSettleData(supplierSettle);
	}

	@Override
	public DataTableResult addSupplierSettleDate(SupplierSettle supplierSettle) {
		DataTableResult result = new DataTableResult();
		try {
			//有特殊区域 以第一条为准 剩余的 首费/首单 要么都 大于  要么都小于
			if(supplierSettle.getSettleSettingsList() != null && supplierSettle.getSettleSettingsList().size() > 0){
				if (supplierSettle.getSettleSettingsList().size() > 1){
					BigDecimal firstPrice = BigDecimal.ZERO;
					BigDecimal firstTag = BigDecimal.ZERO;
					BigDecimal secondPrice = BigDecimal.ZERO;
					BigDecimal secondTag = BigDecimal.ZERO;
					for (int j = 0; j < supplierSettle.getSettleSettingsList().size(); j++) {
						if (j == 0) {
							firstPrice = supplierSettle.getSettleSettingsList().get(0).getFirst_price();
							secondPrice = supplierSettle.getSettleSettingsList().get(0).getSecond_price();
							firstTag = new BigDecimal(supplierSettle.getSettleSettingsList().get(0).getFirst_tag());
							secondTag = new BigDecimal(supplierSettle.getSettleSettingsList().get(0).getSecond_tag());
						}
						BigDecimal firstPrice2 = supplierSettle.getSettleSettingsList().get(j).getFirst_price();
						BigDecimal secondPrice2 = supplierSettle.getSettleSettingsList().get(j).getSecond_price();
						BigDecimal firstTag2 = new BigDecimal(supplierSettle.getSettleSettingsList().get(j).getFirst_tag());
						BigDecimal secondTag2 = new BigDecimal(supplierSettle.getSettleSettingsList().get(j).getSecond_tag());

						BigDecimal firstValue = firstPrice.divide(firstTag,2,BigDecimal.ROUND_FLOOR);
						BigDecimal secondValue = secondPrice.divide(secondTag,2,BigDecimal.ROUND_FLOOR);
						BigDecimal firstValue2 = firstPrice2.divide(firstTag2,2,BigDecimal.ROUND_FLOOR);
						BigDecimal secondValue2 = secondPrice2.divide(secondTag2,2,BigDecimal.ROUND_FLOOR);
						if (firstValue2.compareTo(firstValue) == -1){
							if (secondValue.compareTo(secondValue2) == -1){
								result.setDraw(0);
								result.setError("特殊区域设置错误");
								return result;
							}
						}
						if (firstValue.compareTo(firstValue2) == -1){
							if (secondValue2.compareTo(secondValue) == -1){
								result.setDraw(0);
								result.setError("特殊区域设置错误");
								return result;
							}
						}

					}
				}
			}

			SupplierInfo supplierInfo = supplierInfoMapper.selectByUserId(String.valueOf(UserUtil.getUserMessage().getId()));
			if(supplierInfo != null){
				supplierSettle.setS_id(supplierInfo.getS_id());
			}
			supplierSettle.setCreate_time(new Date());
			supplierSettle.setUpdate_time(new Date());
			result.setDraw(supplierSettleMapper.addSupplierSettleDate(supplierSettle));
			//特殊地区
			if(supplierSettle.getSas_id() != null && supplierSettle.getSettleSettingsList() != null && supplierSettle.getSettleSettingsList().size() > 0){
				for (int j = 0; j < supplierSettle.getSettleSettingsList().size(); j++) {
					supplierSettle.getSettleSettingsList().get(j).setShipping_id(supplierSettle.getSas_id());
					supplierSettle.getSettleSettingsList().get(j).setCreate_time(new Date());
					result.setDraw(supplierSettleSettingsMapper.addSupplierSettleSettings(supplierSettle.getSettleSettingsList().get(j)));
				}
			}
			//不发货地区
			if(StringUtil.isNotBlank(supplierSettle.getNodel())){
				NoDeliveryAreaSettle noDeliveryAreaSettle = new NoDeliveryAreaSettle();
				noDeliveryAreaSettle.setCity(supplierSettle.getNodel());
				noDeliveryAreaSettle.setS_id(UserUtil.getUserMessage().getId());
				noDeliveryAreaSettle.setShipping_id(supplierSettle.getSas_id());
				noDeliveryAreaSettle.setCreatedate(new Date());
				noDeliveryAreaSettle.setUpdatedate(new Date());
				result.setDraw(noDeliveryAreaSettleMapper.addNoDeliveryAreaSettleData(noDeliveryAreaSettle));
			}
			result.setError("保存成功");
		} catch (Exception e) {
			result.setError("保存失败");
			logger.error("保存失败", e);
		}
		return result;
	}

	@Override
	public DataTableResult editSupplierSettleDate(SupplierSettle supplierSettle) {
		DataTableResult result = new DataTableResult();
		try {
			supplierSettleMapper.editSupplierSettleDate(supplierSettle);

			NoDeliveryAreaSettle noDeliveryAreaSettle = noDeliveryAreaSettleMapper.selectByShippingId(String.valueOf(supplierSettle.getSas_id()));
			if(noDeliveryAreaSettle != null){
				noDeliveryAreaSettle.setCity(supplierSettle.getNodel());
				noDeliveryAreaSettle.setUpdatedate(new Date());
				noDeliveryAreaSettleMapper.updateNoDelivery(noDeliveryAreaSettle);
			}else{
				NoDeliveryAreaSettle noDeliveryAreaSettle2 = new NoDeliveryAreaSettle();
				noDeliveryAreaSettle2.setCity(supplierSettle.getNodel());
				noDeliveryAreaSettle2.setS_id(UserUtil.getUserMessage().getId());
				noDeliveryAreaSettle2.setShipping_id(supplierSettle.getSas_id());
				noDeliveryAreaSettle2.setCreatedate(new Date());
				noDeliveryAreaSettle2.setUpdatedate(new Date());
				result.setDraw(noDeliveryAreaSettleMapper.addNoDeliveryAreaSettleData(noDeliveryAreaSettle2));
			}
			result.setError("修改成功");
			result.setDraw(1);
		} catch (Exception e) {
			result.setError("修改失败");
			logger.error("SPU修改失败", e);
		}
		return result;
	}

	@Override
	public DataTableResult deleteByPrimaryKey(String id) {
		DataTableResult result = new DataTableResult();
		try {
			result.setError("删除成功");
			result.setDraw(supplierSettleMapper.deleteByPrimaryKey(id));
		} catch (Exception e) {
			result.setError("删除失败");
			logger.error("删除失败", e);
		}
		return result;
	}

	@Override
	public SupplierSettle selectById(String id) {
		SupplierSettleSettings supplierSettleSettings = new SupplierSettleSettings();
		SupplierSettle supplierSettle = supplierSettleMapper.selectById(Integer.parseInt(id));
		if(supplierSettle != null){

			supplierSettleSettings.setShipping_id(Integer.parseInt(id));
			List<SupplierSettleSettings> list = supplierSettleSettingsMapper.getSupplierSettleSettingsData(supplierSettleSettings);
			for (SupplierSettleSettings supplierSettleSettings2 : list) {
				String[] ids = supplierSettleSettings2.getCity().split(",");
				String cnames = "";
				for (String id2 : ids) {
					Region region = regionMapper.selectByRid(id2);
					cnames += region.getLocal_name() + ",";
				}
				supplierSettleSettings2.setCityName(cnames);
			}
			supplierSettle.setSettleSettingsList(list);

			NoDeliveryAreaSettle noDeliveryAreaSettle = noDeliveryAreaSettleMapper.selectByShippingId(id);
			if(noDeliveryAreaSettle != null){
				String names = "";
				String[] ids = noDeliveryAreaSettle.getCity().split(",");
				for (String id3 : ids) {
					Region region = regionMapper.selectByRid(id3);
					names += region.getLocal_name() + ",";
				}
				supplierSettle.setNodel(noDeliveryAreaSettle.getCity());
				supplierSettle.setNodelName(names);
			}
		}
		return supplierSettle;
	}

	@Override
	public List<SupplierSettle> supplierSettleList(SupplierSettle supplierSettle) {
		return supplierSettleMapper.getSupplierSettleData(supplierSettle);
	}

}
