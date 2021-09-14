package com.data.display.mapper.commodityMapper;

import com.data.display.model.commodity.SpuCommodity;
import com.github.pagehelper.Page;

import java.util.List;

public interface SpuCommodityMapper {

	    int deleteByPrimaryKey(String id);

	    int insert(SpuCommodity record);

	    int insertSelective(SpuCommodity record);

	    Page<SpuCommodity> selectByPrimaryKey(SpuCommodity spuCommodity);

	    List<SpuCommodity> selectAll();

		/**
		 * 获取是banner图的数据
		 * @return
		 */
	    List<SpuCommodity> selectByIsBanner(String is_banner);

	    int updateByPrimaryKeySelective(SpuCommodity record);

	    int updateByPrimaryKeyWithBLOBs(SpuCommodity record);

	    int updateByPrimaryKey(SpuCommodity record);

		SpuCommodity selectById(String id);
		
		SpuCommodity selectBySpuid(String spuid);

		int editOnSale(SpuCommodity spuCommodity);
		/**
		 * spu审核列表
		 * @param spuCommodity
		 * @return
		 */
		Page<SpuCommodity> getExamineSpuData(SpuCommodity spuCommodity);
	
}
