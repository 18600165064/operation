package com.data.display.mapper.commodityMapper;

import com.data.display.model.commodity.Materail;
import com.github.pagehelper.Page;

public interface MaterailMapper {

	Integer insertSelective(Materail materail);

	Page<Materail> selectMaterail(Materail materail);
	
	Integer deleteByPrimaryKey(String id);

	Materail selectMaterailById(String id);

	Integer updateByPrimaryKeySelective(Materail materail);

}
