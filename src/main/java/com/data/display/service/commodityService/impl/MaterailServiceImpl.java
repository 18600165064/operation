package com.data.display.service.commodityService.impl;

import java.util.Date;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.data.display.mapper.commodityMapper.MaterailMapper;
import com.data.display.model.commodity.Materail;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.service.commodityService.MaterailService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class MaterailServiceImpl implements MaterailService{

	private static final Logger logger = LoggerFactory.getLogger(MaterailServiceImpl.class);
	
	@Resource
    private MaterailMapper materailMapper;

	@Override
	public DataTableResult addMaterailData(Materail materail) {
		DataTableResult result = new DataTableResult();
		try {
			result.setError("保存成功");
			materail.setCreate_time(new Date());
			result.setDraw(materailMapper.insertSelective(materail));
		} catch (Exception e) {
			result.setError("保存失败");
			logger.error("素材保存失败", e);
		}
		return result;
	}

	@Override
	public Page<Materail> selectMaterail(DataTableDTO dataTableDTO,Materail materail) {
		PageHelper.startPage(dataTableDTO.getPageNum(), dataTableDTO.getLength());
		return materailMapper.selectMaterail(materail);
	}

	@Override
	public DataTableResult deleteById(String id) {
		DataTableResult result = new DataTableResult();
		try {
			result.setError("删除成功");
			result.setDraw(materailMapper.deleteByPrimaryKey(id));
		} catch (Exception e) {
			result.setError("删除失败");
			logger.error("素材删除失败", e);
		}
		return result;
	}

	@Override
	public Materail selectMaterailById(String id) {
		return materailMapper.selectMaterailById(id);
	}

	@Override
	public DataTableResult editMaterailData(Materail materail) {
		DataTableResult result = new DataTableResult();
		try {
			result.setError("修改成功");
			result.setDraw(materailMapper.updateByPrimaryKeySelective(materail));
		} catch (Exception e) {
			result.setError("修改失败");
			logger.error("素材修改失败", e);
		}
		return result;
	}
	
}
