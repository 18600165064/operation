package com.data.display.service.commodityService;

import com.data.display.model.commodity.Materail;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.github.pagehelper.Page;

public interface MaterailService {

	DataTableResult addMaterailData(Materail materail);

	Page<Materail> selectMaterail(DataTableDTO dataTableDTO, Materail materail);

	DataTableResult deleteById(String id);

	Materail selectMaterailById(String id);

	DataTableResult editMaterailData(Materail materail);

}
