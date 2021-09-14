package com.data.display.service.commodityService.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.data.display.mapper.commodityMapper.ClassIficationMapper;
import com.data.display.model.commodity.ClassIfication;
import com.data.display.model.commodity.SpecAndCate;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.service.commodityService.ClassIficationService;
import com.data.display.util.StringUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class ClassIficationServiceImpl implements ClassIficationService{

	private static final Logger logger = LoggerFactory.getLogger(ClassIficationServiceImpl.class);

    @Resource
    private ClassIficationMapper classIficationMapper;
	
	@Override
	public List<ClassIfication> getClassIficationData(ClassIfication classIfication) {
		return classIficationMapper.getClassIficationData(classIfication);
	}

	@Override
	public DataTableResult addClassIficationData(ClassIfication classIfication,String sids) {
		DataTableResult result = new DataTableResult();
		try {
			if(classIfication != null){
				classIfication.setCreate_time(new Date());
				if(classIfication.getId() != null){
					result.setDraw(classIficationMapper.deleteByCid(String.valueOf(classIfication.getId())));
				}else{
					classIfication.setIs_show(0);
					result.setDraw(classIficationMapper.addClassIficationData(classIfication));
				}
				if(StringUtil.isNotBlank(sids)){
					String[] sid = sids.split(",");
					for (int i = 0; i < sid.length; i++) {
						SpecAndCate specAndCate = new SpecAndCate();
						specAndCate.setCreate_time(new Date());
						specAndCate.setSid(Integer.parseInt(sid[i]));
						specAndCate.setCid(classIfication.getId());
						classIficationMapper.addSpecAndCate(specAndCate);
					}
				}
		        	result.setError("添加成功");
			}
		} catch (Exception e) {
			result.setError("添加失败");
			logger.error("系统错误",e);
		}
		
		return result;
	}

	@Override
	public DataTableResult deleteByPrimaryKey(String id) {
		DataTableResult result = new DataTableResult();
		result.setError("删除成功");
		try{
			if(StringUtil.isNotBlank(id)){
				result.setDraw(classIficationMapper.deleteByPrimaryKey(id));
			}else{
				result.setError("参数错误");
			}
		}catch (Exception e){
			result.setError("删除失败");
			logger.error("角色删除失败",e);
		}
		return result;
	}

	@Override
	public Page<ClassIfication> getData(DataTableDTO dataTableDTO, ClassIfication classIfication) {
		PageHelper.startPage(dataTableDTO.getPageNum(), dataTableDTO.getLength());
		return classIficationMapper.getData(classIfication);
	}

	@Override
	public ClassIfication selectById(String id) {
		return classIficationMapper.selectById(id);
	}

	@Override
	public DataTableResult updateClass(ClassIfication classIfication) {
		DataTableResult result = new DataTableResult();
		result.setError("修改成功");
		try{
			if(classIfication.getId() != null){
				result.setDraw(classIficationMapper.updateByPrimaryKey(classIfication));
			}else{
				result.setError("参数错误");
			}
		}catch (Exception e){
			result.setError("修改失败");
			logger.error("修改失败",e);
		}
		return result;
	}

}
