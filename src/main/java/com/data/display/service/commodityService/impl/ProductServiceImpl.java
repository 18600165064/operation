package com.data.display.service.commodityService.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.data.display.mapper.commodityMapper.ProductMapper;
import com.data.display.mapper.commodityMapper.SpecificationsMapper;
import com.data.display.mapper.commodityMapper.SpuCommodityMapper;
import com.data.display.mapper.commodityMapper.SupplierAccountShippingMapper;
import com.data.display.mapper.commodityMapper.TopLevelMapper;
import com.data.display.mapper.supplierMapper.SupplierInfoMapper;
import com.data.display.model.commodity.Product;
import com.data.display.model.commodity.Specifications;
import com.data.display.model.commodity.SpuCommodity;
import com.data.display.model.commodity.SupplierAccountShipping;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.supplier.SupplierInfo;
import com.data.display.model.user.SysUser;
import com.data.display.service.commodityService.ProductService;
import com.data.display.util.StringUtil;
import com.data.display.util.UserUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


@Service
public class ProductServiceImpl implements ProductService{

private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Resource
    private ProductMapper productMapper;
	
	@Resource
    private SpuCommodityMapper spuCommodityMapper;
	
	@Resource
	private SpecificationsMapper specificationsMapper;
	
	@Resource
	private SupplierInfoMapper supplierInfoMapper;
	
	@Resource
	private TopLevelMapper topLevelMapper;
	
	@Resource
	private SupplierAccountShippingMapper supplierAccountShippingMapper; 
	
	
	
	@Override
	public DataTableResult addProduct(Product product) {
		List<Map<String,String>> list = new ArrayList<>();
		SysUser user = UserUtil.getUserMessage();
		DataTableResult result = new DataTableResult();
		BigDecimal big = BigDecimal.ZERO;
		try {
			result.setError("保存成功");
			String[] sid = product.getSpec().split(",");
			
			for (int i = 0; i < sid.length; i++) {
				Map<String,String> map = new HashMap<>();
				Specifications specifications = specificationsMapper.selectById(sid[i]);
				map.put("spec_name", specifications.getSpec_name());
				map.put("spec_value", specifications.getSpec_value());
				map.put("id", String.valueOf(specifications.getId()));
				list.add(map);
			}
			product.setSpec(JSON.toJSONString(list));
			product.setSkuid(StringUtil.zyRandom());
			product.setS_id(UserUtil.getUserMessage().getId());
			product.setCreate_time(new Date());
			product.setSub_dis_fee(big);
			product.setSub_dis_value(1);
			product.setDefault_number(1);
			product.setDefault_price(big);
//			if(product.getIs_top() == 1){
//				product.setUpdate_time(new Date());
//			}
			SupplierInfo supplierInfo = supplierInfoMapper.selectByUserId(String.valueOf(user.getId()));
			if(supplierInfo != null){
				product.setS_id(supplierInfo.getS_id());
			}
			product.setOn_sale(1);
			
			
			SpuCommodity spuCommodity = spuCommodityMapper.selectBySpuid(product.getSpuid());
			
			product.setGroup_price_eight(spuCommodity.getGroup_price_eight());
			product.setGroup_price_five(spuCommodity.getGroup_price_five());
			product.setGroup_price_three(spuCommodity.getGroup_price_three());
			product.setCommission(spuCommodity.getCommission());
			product.setMarket_price(spuCommodity.getMarket_price());
			product.setSupply_price(spuCommodity.getSupply_price());
			product.setService_price(spuCommodity.getService_price());
			product.setPrice(spuCommodity.getPrice());
			product.setCommission(spuCommodity.getCommission());
			product.setCommission_price_eight(spuCommodity.getCommission_price_eight());
			product.setCommission_price_five(spuCommodity.getCommission_price_five());
			product.setCommission_price_three(spuCommodity.getCommission_price_three());
			product.setRefund_price(spuCommodity.getRefund_price());
			
			result.setDraw(productMapper.addProduct(product));
		} catch (Exception e) {
			result.setError("保存失败");
			logger.error("保存失败", e);
		}
		return result;
	}

	@Override
	public Page<Product> getSkuData(DataTableDTO dataTableDTO, Product product) {
		PageHelper.startPage(dataTableDTO.getPageNum(), dataTableDTO.getLength());
		return productMapper.getProductData(product);
	}

	@Override
	public Product selectById(String id) {
		return productMapper.selectById(id);
	}

	@Override
	public DataTableResult editSku(Product product) {
		DataTableResult result = new DataTableResult();
		List<Map<String,String>> list = new ArrayList<>();
		try {
			result.setError("修改成功");
			
			String[] sid = product.getSpec().split(",");
			for (int i = 0; i < sid.length; i++) {
				Map<String,String> map = new HashMap<>();
				Specifications specifications = specificationsMapper.selectById(sid[i]);
				map.put("spec_name", specifications.getSpec_name());
				map.put("spec_value", specifications.getSpec_value());
				map.put("id", String.valueOf(specifications.getId()));
				list.add(map);
			}
			product.setSpec(JSON.toJSONString(list));
//			if(product.getIs_top() == 1){
//				product.setUpdate_time(new Date());
//			}
			result.setDraw(productMapper.updateByPrimaryKeySelective(product));
		} catch (Exception e) {
			result.setError("修改失败");
			logger.error("SKU修改失败", e);
		}
		return result;
	}

	@Override
	public DataTableResult deleteById(String id) {
		DataTableResult result = new DataTableResult();
		try {
			result.setError("删除成功");
			result.setDraw(productMapper.deleteByPrimaryKey(id));
		} catch (Exception e) {
			result.setError("删除失败");
			logger.error("SKU删除失败", e);
		}
		return result;
	}

	@Override
	public DataTableResult editOnSale(Product product) {
		DataTableResult result = new DataTableResult();
		try {
			SpuCommodity spuCommodity = spuCommodityMapper.selectBySpuid(product.getSpuid());
			result.setError("审核成功");
			//SKU下架
			if(product.getOn_sale() == 1){
				//SPU上架
				if(spuCommodity.getOn_sale() == 0){
					List<Product> pList = productMapper.selectByOther(product);
					int count = 0;
					for (Product product2 : pList) {
						if(product2.getOn_sale() == 0){
							count ++;
						}
					}
					if(count <= 1){
						result.setError("此SKU为该SPU下最后一个上架的SKU,请先对该SPU进行维护操作");
						return result;
					}
				}
			}
			result.setDraw(productMapper.updateByPrimaryKeySelective(product));
		} catch (Exception e) {
			result.setError("审核失败");
			logger.error("SKU审核失败", e);
		}
		return result;
	}

	@Override
	public Page<Product> getExamineSkuData(DataTableDTO dataTableDTO, Product product) {
		PageHelper.startPage(dataTableDTO.getPageNum(), dataTableDTO.getLength());
		return productMapper.getExamineSkuData(product);
	}

}
