package com.data.display.mapper.commodityMapper;

import java.util.List;

import com.data.display.model.commodity.Specifications;
import com.github.pagehelper.Page;

public interface SpecificationsMapper {

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
	
	Specifications selectById(String id);

	Page<Specifications> getData(Specifications specifications);

	Integer addSpecificationsData(Specifications specifications);

	Integer updateSpecifications(Specifications specifications);

}
