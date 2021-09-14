package com.data.display.mapper.commodityMapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.data.display.model.commodity.ClassIfication;
import com.data.display.model.commodity.SpecAndCate;
import com.github.pagehelper.Page;

public interface ClassIficationMapper {

	/**
	 * 根据级别及父级ID获取列表
	 * @param classIfication
	 * @return
	 */
	List<ClassIfication> getClassIficationData(ClassIfication classIfication);

	/**
	 * 新增
	 * @param classIfication
	 * @return
	 */
	Integer addClassIficationData(ClassIfication classIfication);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	Integer deleteByPrimaryKey(@Param("id")String id);
	
	Integer deleteByCid(@Param("cid")String cid);

	void addSpecAndCate(SpecAndCate specAndCate);

	Page<ClassIfication> getData(ClassIfication classIfication);

	ClassIfication selectById(String id);

	Integer updateByPrimaryKey(ClassIfication classIfication);

}
