package com.data.display.service.commodityService;

import com.data.display.model.commodity.Product;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.github.pagehelper.Page;

public interface ProductService {

	/**
	 * 添加
	 * @param product
	 * @return
	 */
	DataTableResult addProduct(Product product);

	Page<Product> getSkuData(DataTableDTO dataTableDTO, Product product);

	Product selectById(String id);

	DataTableResult editSku(Product product);

	DataTableResult deleteById(String id);

	DataTableResult editOnSale(Product product);
	/**
	 * sku审核
	 * @param dataTableDTO
	 * @param product
	 * @return
	 */
	Page<Product> getExamineSkuData(DataTableDTO dataTableDTO, Product product);

}
