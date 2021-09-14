package com.data.display.service.commodityService.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.data.display.mapper.commodityMapper.ClassIficationMapper;
import com.data.display.mapper.commodityMapper.SpecificationsMapper;
import com.data.display.model.commodity.SpecAndCate;
import com.data.display.model.commodity.Specifications;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.service.commodityService.SpecificationsService;
import com.data.display.util.StringUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class SpecificationsServiceImpl implements SpecificationsService{

	private static final Logger logger = LoggerFactory.getLogger(SpecificationsServiceImpl.class);
	
	@Resource
    private SpecificationsMapper specificationsMapper;
	
	@Resource
    private ClassIficationMapper classIficationMapper;

	@Override
	public List<Specifications> getAllSpecificationsByCid(String cid) {
		return specificationsMapper.getAllSpecificationsByCid(cid);
	}

	@Override
	public List<Specifications> getSpecificationsData(Specifications specifications) {
		return specificationsMapper.getSpecificationsData(specifications);
	}

	@Override
	public List<Specifications> selSpecName() {
		return specificationsMapper.selSpecName();
	}
	
	
	@Override
	public Specifications selectById(String id) {
		return specificationsMapper.selectById(id);
	}

	@Override
	public Page<Specifications> getData(DataTableDTO dataTableDTO, Specifications specifications) {
		PageHelper.startPage(dataTableDTO.getPageNum(), dataTableDTO.getLength());
		return specificationsMapper.getData(specifications);
	}

	@Override
	public DataTableResult addSpecificationsData(Specifications specifications) {
		DataTableResult result = new DataTableResult();
		result.setError("添加成功");
		try {
			if(specifications != null){
				Specifications specifications2 = new Specifications();
				specifications2.setSpec_name(specifications.getSpec_name());
				List<Specifications> list = specificationsMapper.getSpecificationsData(specifications2);
				for (Specifications specifications3 : list) {
					if(specifications3.getSpec_value().equals(specifications.getSpec_value())){
						result.setError("该规格名称下已存在该规格值,不能重复添加");
						return result;
					}
				}
				specifications.setCreate_time(new Date());
				result.setDraw(specificationsMapper.addSpecificationsData(specifications));
			}
		} catch (Exception e) {
			result.setError("添加失败");
			logger.error("规格添加失败",e);
		}
		return result;
	}

	@Override
	public DataTableResult updateSpecifications(Specifications specifications) {
		DataTableResult result = new DataTableResult();
		result.setError("修改成功");
		try {
			if(specifications != null){
				Specifications specifications2 = new Specifications();
				specifications2.setSpec_name(specifications.getSpec_name());
				List<Specifications> list = specificationsMapper.getSpecificationsData(specifications2);
				for (Specifications specifications3 : list) {
					if(specifications3.getSpec_value().equals(specifications.getSpec_value())){
						result.setError("该规格名称下已存在该规格值,不能重复添加");
						return result;
					}
				}
				specifications.setCreate_time(new Date());
				result.setDraw(specificationsMapper.updateSpecifications(specifications));
			}
		} catch (Exception e) {
			result.setError("修改失败");
			logger.error("规格修改失败",e);
		}
		return result;
	}

	@Override
	public DataTableResult addspecByClassData(String cid, String sids) {
		DataTableResult result = new DataTableResult();
		try {
				classIficationMapper.deleteByCid(cid);
				if(StringUtil.isNotBlank(sids)){
					String[] sid = sids.split(",");
					for (int i = 0; i < sid.length; i++) {
						SpecAndCate specAndCate = new SpecAndCate();
						specAndCate.setCreate_time(new Date());
						specAndCate.setSid(Integer.parseInt(sid[i]));
						specAndCate.setCid(Integer.parseInt(cid));
						classIficationMapper.addSpecAndCate(specAndCate);
					}
				}
				result.setDraw(1);
		        result.setError("添加成功");
		} catch (Exception e) {
			result.setError("添加失败");
			logger.error("系统错误",e);
		}
		return result;
	}
	
}
