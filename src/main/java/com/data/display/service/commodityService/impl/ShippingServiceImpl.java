package com.data.display.service.commodityService.impl;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.data.display.mapper.commodityMapper.ShippingMapper;
import com.data.display.model.commodity.Shipping;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.service.commodityService.ShippingService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class ShippingServiceImpl implements ShippingService{

	private static final Logger logger = LoggerFactory.getLogger(ShippingServiceImpl.class);
	
	@Resource
    private ShippingMapper shippingMapper;
	
	@Override
	public DataTableResult addShipping(Shipping shipping) {
		DataTableResult result = new DataTableResult();
		try {
			result.setError("保存成功");
			result.setDraw(shippingMapper.addShipping(shipping));
		} catch (Exception e) {
			result.setError("保存失败");
			logger.error("保存失败", e);
		}
		return result;
	}

	@Override
	public Page<Shipping> getShippingData(DataTableDTO dataTableDTO, Shipping shipping) {
		PageHelper.startPage(dataTableDTO.getPageNum(), dataTableDTO.getLength());
		return shippingMapper.getShippingData(shipping);
	}

	@Override
	public DataTableResult editShipping(Shipping shipping) {
		DataTableResult result = new DataTableResult();
		try {
			result.setError("修改成功");
			result.setDraw(shippingMapper.editShipping(shipping));
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
			result.setDraw(shippingMapper.deleteByPrimaryKey(id));
		} catch (Exception e) {
			result.setError("删除失败");
			logger.error("删除失败", e);
		}
		return result;
	}

	@Override
	public Shipping selectById(String id) {
		return shippingMapper.selectById(id);
	}

}
