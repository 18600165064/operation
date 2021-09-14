package com.data.display.service.commodityService;

import java.util.List;

import com.data.display.model.commodity.Specifications;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.github.pagehelper.Page;

public interface SpecificationsService {

	/**
	 * 根据第三级类目ID获取对应的规格
	 * @param cid
	 * @return
	 */
	List<Specifications> getAllSpecificationsByCid(String cid);

	/**
	 * 查询所有规格
	 * @param specifications
	 * @return
	 */
	List<Specifications> getSpecificationsData(Specifications specifications);
	
	List<Specifications> selSpecName();

	/**
	 * 根据ID获取
	 * @param id
	 * @return
	 */
	Specifications selectById(String id);

	Page<Specifications> getData(DataTableDTO dataTableDTO, Specifications specifications);

	DataTableResult addSpecificationsData(Specifications specifications);

	DataTableResult updateSpecifications(Specifications specifications);

	DataTableResult addspecByClassData(String cid, String sids);

}
