package com.data.display.service.commodityService;

import com.data.display.model.commodity.SpuCommodity;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.github.pagehelper.Page;

public interface SpuCommodityService {
	
	/**
	 * 分页列表
	 * @param dataTableDTO
	 * @param spuCommodity
	 * @return
	 */
	Page<SpuCommodity> getSpuData(DataTableDTO dataTableDTO,SpuCommodity spuCommodity);
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	SpuCommodity selectById(String id);

	/**
	 * 根据ID删除
	 * @param id
	 * @return
	 */
	DataTableResult deleteByPrimaryKey(String id);

	/**
	 * 添加
	 * @param spuCommodity
	 * @return
	 */
	DataTableResult addSpu(SpuCommodity spuCommodity);

	/**
	 * 修改
	 * @param spuCommodity
	 * @return
	 */
	DataTableResult editSpu(SpuCommodity spuCommodity);

	DataTableResult maintainSpu(SpuCommodity spuCommodity);

	DataTableResult editOnSale(SpuCommodity spuCommodity);

	/**
	 * 供应商添加商品
	 * @param spuCommodity
	 * @return
	 */
	DataTableResult addSupplierSpuData(SpuCommodity spuCommodity);
	/**
	 * 供应商编辑商品
	 * @param spuCommodity
	 * @return
	 */
	DataTableResult editSupplierSpuData(SpuCommodity spuCommodity);

	/**
	 * 审核SPU列表
	 * @param dataTableDTO
	 * @param spuCommodity
	 * @return
	 */
	Page<SpuCommodity> getExamineSpuData(DataTableDTO dataTableDTO, SpuCommodity spuCommodity);

}
