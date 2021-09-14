package com.data.display.service.commodityService;

import java.util.List;

import com.data.display.model.commodity.ClassIfication;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.github.pagehelper.Page;

public interface ClassIficationService {

	/**
	 * 根据级别及父级ID获取列表
	 * @param dataTableDTO
	 * @param classIfication
	 * @return
	 */
	List<ClassIfication> getClassIficationData(ClassIfication classIfication);

	/**
	 * 新增
	 * @param classIfication
	 * @return
	 */
	DataTableResult addClassIficationData(ClassIfication classIfication,String sids);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	DataTableResult deleteByPrimaryKey(String id);

	Page<ClassIfication> getData(DataTableDTO dataTableDTO, ClassIfication classIfication);

	ClassIfication selectById(String id);

	DataTableResult updateClass(ClassIfication classIfication);

}
