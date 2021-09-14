package com.data.display.service.commodityService.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.crypto.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.data.display.mapper.commodityMapper.NoDeliveryAreaMapper;
import com.data.display.mapper.commodityMapper.RegionMapper;
import com.data.display.mapper.commodityMapper.SupplierAccountShippingMapper;
import com.data.display.mapper.commodityMapper.SupplierAccountShippingSettingsMapper;
import com.data.display.mapper.supplierMapper.SupplierInfoMapper;
import com.data.display.model.commodity.NoDeliveryArea;
import com.data.display.model.commodity.Region;
import com.data.display.model.commodity.SupplierAccountShipping;
import com.data.display.model.commodity.SupplierAccountShippingSettings;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.supplier.SupplierInfo;
import com.data.display.service.commodityService.SupplierAccountShippingService;
import com.data.display.util.StringUtil;
import com.data.display.util.UserUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class SupplierAccountShippingServiceImpl implements SupplierAccountShippingService{

	private static final Logger logger = LoggerFactory.getLogger(SupplierAccountShippingServiceImpl.class);
	
	@Resource
    private SupplierAccountShippingMapper supplierAccountShippingMapper;
	
	@Resource
    private SupplierAccountShippingSettingsMapper supplierAccountShippingSettingsMapper;
	
	@Resource
    private NoDeliveryAreaMapper noDeliveryAreaMapper;
	
	@Resource
    private RegionMapper regionMapper;
	
	@Resource
	private SupplierInfoMapper supplierInfoMapper;
	
	
	@Override
	public Page<SupplierAccountShipping> getSupplierAccountShippingData(DataTableDTO dataTableDTO,SupplierAccountShipping supplierAccountShipping) {
		PageHelper.startPage(dataTableDTO.getPageNum(), dataTableDTO.getLength());
		return supplierAccountShippingMapper.getSupplierAccountShippingData(supplierAccountShipping);
	}

	@Override
	public DataTableResult addSupplierAccountShipping(SupplierAccountShipping supplierAccountShipping) {
		DataTableResult result = new DataTableResult();
		try {

			//有特殊区域 以第一条为准 剩余的 首费/首单 要么都 大于  要么都小于
			if(supplierAccountShipping.getSupplierAccountShippingSettings() != null && supplierAccountShipping.getSupplierAccountShippingSettings().size() > 0){
				if (supplierAccountShipping.getSupplierAccountShippingSettings().size() > 1){
					BigDecimal firstPrice = BigDecimal.ZERO;
					BigDecimal firstTag = BigDecimal.ZERO;
					BigDecimal secondPrice = BigDecimal.ZERO;
					BigDecimal secondTag = BigDecimal.ZERO;
					for (int j = 0; j < supplierAccountShipping.getSupplierAccountShippingSettings().size(); j++) {
						if (j == 0) {
							firstPrice = supplierAccountShipping.getSupplierAccountShippingSettings().get(0).getFirst_price();
							secondPrice = supplierAccountShipping.getSupplierAccountShippingSettings().get(0).getSecond_price();
							firstTag = new BigDecimal(supplierAccountShipping.getSupplierAccountShippingSettings().get(0).getFirst_tag());
							secondTag = new BigDecimal(supplierAccountShipping.getSupplierAccountShippingSettings().get(0).getSecond_tag());
						}
						BigDecimal firstPrice2 = supplierAccountShipping.getSupplierAccountShippingSettings().get(j).getFirst_price();
						BigDecimal secondPrice2 = supplierAccountShipping.getSupplierAccountShippingSettings().get(j).getSecond_price();
						BigDecimal firstTag2 = new BigDecimal(supplierAccountShipping.getSupplierAccountShippingSettings().get(j).getFirst_tag());
						BigDecimal secondTag2 = new BigDecimal(supplierAccountShipping.getSupplierAccountShippingSettings().get(j).getSecond_tag());

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
				supplierAccountShipping.setS_id(supplierInfo.getS_id());
			}
			supplierAccountShipping.setCreate_time(new Date());
			supplierAccountShipping.setUpdate_time(new Date());
			result.setDraw(supplierAccountShippingMapper.addSupplierAccountShipping(supplierAccountShipping));
			//特殊地区
			if(supplierAccountShipping.getSas_id() != null && supplierAccountShipping.getSupplierAccountShippingSettings() != null && supplierAccountShipping.getSupplierAccountShippingSettings().size() > 0){
				for (int j = 0; j < supplierAccountShipping.getSupplierAccountShippingSettings().size(); j++) {
					supplierAccountShipping.getSupplierAccountShippingSettings().get(j).setShipping_id(supplierAccountShipping.getSas_id());
					supplierAccountShipping.getSupplierAccountShippingSettings().get(j).setCreate_time(new Date());
					result.setDraw(supplierAccountShippingSettingsMapper.addSupplierAccountShippingSettings(supplierAccountShipping.getSupplierAccountShippingSettings().get(j)));
				}
			}
			if(StringUtil.isNotBlank(supplierAccountShipping.getNodel())){
				NoDeliveryArea noDeliveryArea = new NoDeliveryArea();
				noDeliveryArea.setCity(supplierAccountShipping.getNodel());
				noDeliveryArea.setS_id(UserUtil.getUserMessage().getId());
				noDeliveryArea.setShipping_id(supplierAccountShipping.getSas_id());
				noDeliveryArea.setCreatedate(new Date());
				noDeliveryArea.setUpdatedate(new Date());
				result.setDraw(noDeliveryAreaMapper.addNoDeliveryAreaData(noDeliveryArea));
			}
			result.setError("保存成功");
		} catch (Exception e) {
			result.setError("保存失败");
			logger.error("保存失败", e);
		}
		return result;
	}

	@Override
	public DataTableResult editSupplierAccountShipping(SupplierAccountShipping supplierAccountShipping) {
		DataTableResult result = new DataTableResult();
		try {
			supplierAccountShippingMapper.editSupplierAccountShipping(supplierAccountShipping);
			if(supplierAccountShipping.getSupplierAccountShippingSettings() != null && supplierAccountShipping.getSupplierAccountShippingSettings().size() > 0){
				for (int i = 0; i < supplierAccountShipping.getSupplierAccountShippingSettings().size(); i++) {
					SupplierAccountShippingSettings supplierAccountShippingSettings = supplierAccountShipping.getSupplierAccountShippingSettings().get(i);
					if(supplierAccountShippingSettings.getId() != null){
						supplierAccountShippingSettingsMapper.editSupplierAccountShippingSettings(supplierAccountShippingSettings);
					}else{
						if(supplierAccountShippingSettings != null){
							supplierAccountShippingSettings.setShipping_id(supplierAccountShipping.getSas_id());
							supplierAccountShippingSettings.setCreate_time(new Date());
							supplierAccountShippingSettingsMapper.addSupplierAccountShippingSettings(supplierAccountShippingSettings);
						}
					}
				}
				
			}
			
			NoDeliveryArea noDeliveryArea = noDeliveryAreaMapper.selectByShippingId(String.valueOf(supplierAccountShipping.getSas_id()));
			if(noDeliveryArea != null){
				noDeliveryArea.setCity(supplierAccountShipping.getNodel());
				noDeliveryArea.setUpdatedate(new Date());
				noDeliveryAreaMapper.updateNoDelivery(noDeliveryArea);
			}else{
				NoDeliveryArea noDeliveryArea2 = new NoDeliveryArea();
				noDeliveryArea2.setCity(supplierAccountShipping.getNodel());
				noDeliveryArea2.setS_id(UserUtil.getUserMessage().getId());
				noDeliveryArea2.setShipping_id(supplierAccountShipping.getSas_id());
				noDeliveryArea2.setCreatedate(new Date());
				noDeliveryArea2.setUpdatedate(new Date());
				result.setDraw(noDeliveryAreaMapper.addNoDeliveryAreaData(noDeliveryArea));
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
			result.setDraw(supplierAccountShippingMapper.deleteByPrimaryKey(id));
			if(result.getDraw() ==1){
				result.setDraw(supplierAccountShippingSettingsMapper.deleteByShippingId(id));
			}
		} catch (Exception e) {
			result.setError("删除失败");
			logger.error("删除失败", e);
		}
		return result;
	}

	@Override
	public SupplierAccountShipping selectById(String id) {
		SupplierAccountShippingSettings supplierAccountShippingSettings = new SupplierAccountShippingSettings();
		SupplierAccountShipping supplierAccountShipping = supplierAccountShippingMapper.selectById(Integer.parseInt(id));
		if(supplierAccountShipping != null){
			supplierAccountShippingSettings.setShipping_id(Integer.parseInt(id));
			List<SupplierAccountShippingSettings> list = supplierAccountShippingSettingsMapper.getSupplierAccountShippingSettingsData(supplierAccountShippingSettings);
			for (SupplierAccountShippingSettings supplierAccountShippingSettings2 : list) {
				String[] ids = supplierAccountShippingSettings2.getCity().split(",");
				String cnames = "";
				for (String id2 : ids) {
					Region region = regionMapper.selectByRid(id2);
					cnames += region.getLocal_name() + ",";
				}
				supplierAccountShippingSettings2.setCityName(cnames);
			}
			supplierAccountShipping.setSupplierAccountShippingSettings(list);
			NoDeliveryArea noDeliveryArea = noDeliveryAreaMapper.selectByShippingId(id);
			if(noDeliveryArea != null){
				String names = "";
				String[] ids = noDeliveryArea.getCity().split(",");
				for (String id3 : ids) {
					Region region = regionMapper.selectByRid(id3);
					names += region.getLocal_name() + ",";
				}
				supplierAccountShipping.setNodel(noDeliveryArea.getCity());
				supplierAccountShipping.setNodelName(names);
			}
		}
		return supplierAccountShipping;
	}

	@Override
	public List<SupplierAccountShipping> shippingList(SupplierAccountShipping supplierAccountShipping) {
		return supplierAccountShippingMapper.getSupplierAccountShippingData(supplierAccountShipping);
	}

}
