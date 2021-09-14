package com.data.display.mapper.commodityMapper;

import java.util.List;

import com.data.display.model.commodity.Product;
import com.github.pagehelper.Page;

/**
 * 产品
 * @author l
 *
 */
public interface ProductMapper {

	Integer addProduct(Product product);

	Page<Product> getProductData(Product product);

	List<Product> selectByOther(Product product);
	
	Product selectById(String id);

	Integer updateByPrimaryKeySelective(Product product);

	Integer deleteByPrimaryKey(String id);

	Page<Product> getExamineSkuData(Product product);

}
