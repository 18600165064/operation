package com.data.display.service.commodityService.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.data.display.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.data.display.mapper.commodityMapper.ProductMapper;
import com.data.display.mapper.commodityMapper.SpecificationsMapper;
import com.data.display.mapper.commodityMapper.SpuCommodityMapper;
import com.data.display.mapper.commodityMapper.SpuDescMapper;
import com.data.display.mapper.commodityMapper.TopLevelMapper;
import com.data.display.mapper.richMapper.RichMapper;
import com.data.display.mapper.supplierMapper.SupplierInfoMapper;
import com.data.display.model.commodity.Materail;
import com.data.display.model.commodity.Product;
import com.data.display.model.commodity.Specifications;
import com.data.display.model.commodity.SpuCommodity;
import com.data.display.model.commodity.SpuDesc;
import com.data.display.model.commodity.TopLevel;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.rich.Rich;
import com.data.display.model.supplier.SupplierInfo;
import com.data.display.model.user.SysUser;
import com.data.display.service.IJobAndTriggerService;
import com.data.display.service.commodityService.SpuCommodityService;
import com.data.display.util.CronUtil;
import com.data.display.util.StringUtil;
import com.data.display.util.UserUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class SpuCommodityServiceImpl implements SpuCommodityService{

	private static final Logger logger = LoggerFactory.getLogger(SpuCommodityServiceImpl.class);
	
	@Resource
    private SpuCommodityMapper spuCommodityMapper;
	 
	@Resource
	private SpecificationsMapper specificationsMapper;
	
	@Resource
	private SupplierInfoMapper supplierInfoMapper;
	
	@Resource
	private TopLevelMapper topLevelMapper; 
	
	@Autowired
	private IJobAndTriggerService jobAndTriggerService;
	
	@Resource
	private ProductMapper productMapper; 
	
	@Resource
	private SpuDescMapper spuDescMapper;
	
	@Resource
	private RichMapper richMapper;
	
	@Override
	public Page<SpuCommodity> getSpuData(DataTableDTO dataTableDTO,SpuCommodity spuCommodity) {
		PageHelper.startPage(dataTableDTO.getPageNum(), dataTableDTO.getLength());
		return spuCommodityMapper.selectByPrimaryKey(spuCommodity);
	}

	@Override
	public SpuCommodity selectById(String id) {
		SpuCommodity spuCommodity = spuCommodityMapper.selectById(id);
		SpuDesc spuDesc = new SpuDesc();
		spuDesc.setSpuid(spuCommodity.getSpuid());
		spuDesc = spuDescMapper.selectByOther(spuDesc);
		if(spuDesc != null){
			spuCommodity.setSpuDesc(spuDesc);
		}
		return spuCommodity;
	}

	@Override
	public DataTableResult deleteByPrimaryKey(String id) {
		DataTableResult result = new DataTableResult();
		try {
			result.setError("删除成功");
			result.setDraw(spuCommodityMapper.deleteByPrimaryKey(id));
		} catch (Exception e) {
			result.setError("删除失败");
			logger.error("SPU删除失败", e);
		}
		return result;
	}

	@Override
	public DataTableResult addSpu(SpuCommodity spuCommodity) {
		DataTableResult result = new DataTableResult();
		SysUser user = UserUtil.getUserMessage();
		List<Map<String,String>> list = new ArrayList<>();
		BigDecimal newBig = BigDecimal.ZERO;
		try {
			result.setError("保存成功");
			String[] url = spuCommodity.getImages().split(",");
			spuCommodity.setImages(JSON.toJSONString(url));
			spuCommodity.setSpuid(StringUtil.zyRandom());
			spuCommodity.setSale_amount(1230);
			String[] sid = spuCommodity.getSpu_spec().split(",");
			for (int i = 0; i < sid.length; i++) {
				Map<String,String> map = new HashMap<>();
				Specifications specifications = specificationsMapper.selectById(sid[i]);
				map.put("spec_name", specifications.getSpec_name());
				map.put("spec_value", specifications.getSpec_value());
				map.put("id", String.valueOf(specifications.getId()));
				list.add(map);
			}
			spuCommodity.setSpu_spec(JSON.toJSONString(list));
			SupplierInfo supplierInfo = supplierInfoMapper.selectByUserId(String.valueOf(user.getId()));
			if(supplierInfo != null){
				spuCommodity.setS_id(supplierInfo.getS_id());
			} 
			spuCommodity.setOn_sale(1);
			//手续比例
			BigDecimal serviceAmount = new BigDecimal(0.02);
			BigDecimal serviceCharge = serviceAmount.setScale(2,BigDecimal.ROUND_FLOOR);
			//手续费
			BigDecimal servicePr = spuCommodity.getPrice().multiply(serviceCharge);
			BigDecimal servicePrice = servicePr.setScale(2,BigDecimal.ROUND_FLOOR);
			//8人团差额 = 8人拼团价-(服务费+成本+佣金+(单价*0.02))
//			BigDecimal eightAmount = spuCommodity.getService_price().add(spuCommodity.getSupply_price()).add(spuCommodity.getCommission_price_eight()).add(servicePrice).add(spuCommodity.getRefund_price());
//			spuCommodity.setEight_difference_price(spuCommodity.getPrice().subtract(eightAmount));
			//5人团差额 = 5人拼团价-(服务费+成本+佣金+(单价*0.02))
//			BigDecimal fiveAmount = spuCommodity.getService_price().add(spuCommodity.getSupply_price()).add(spuCommodity.getCommission_price_five()).add(servicePrice).add(spuCommodity.getRefund_price());
//			spuCommodity.setFive_difference_price(spuCommodity.getPrice().subtract(fiveAmount));
			//3人团差额 = 3人拼团价-(服务费+成本+佣金+(单价*0.02))
//			BigDecimal threeAmount = spuCommodity.getService_price().add(spuCommodity.getSupply_price()).add(spuCommodity.getCommission_price_three()).add(servicePrice).add(spuCommodity.getRefund_price());
//			spuCommodity.setThree_difference_price(spuCommodity.getPrice().subtract(threeAmount));

//			if (
//				spuCommodity.getEight_difference_price().compareTo(newBig) == -1 ||
//				spuCommodity.getFive_difference_price().compareTo(newBig) == -1 ||
//				spuCommodity.getThree_difference_price().compareTo(newBig) == -1
//			){
//				result.setError("金额错误");
//				return result;
//			}

			if(spuCommodity.getSpuDesc() != null){
				if(StringUtil.isNotBlank(spuCommodity.getSpuDesc().getSkudesc())){
					SpuDesc spuDesc = new SpuDesc();
					spuDesc.setCreate_time(new Date());
					spuDesc.setSkudesc(spuCommodity.getSpuDesc().getSkudesc());
					spuDesc.setSpuid(spuCommodity.getSpuid());
					spuDescMapper.addSpuDesc(spuDesc);
				}
			}
			spuCommodity.setCreate_time(new Date());
			result.setDraw(spuCommodityMapper.insertSelective(spuCommodity));
		} catch (Exception e) {
			result.setError("保存失败");
			logger.error("SPU保存失败", e);
		}
		return result;
	}

	@Override
	public DataTableResult addSupplierSpuData(SpuCommodity spuCommodity) {
		DataTableResult result = new DataTableResult();
		SysUser user = UserUtil.getUserMessage();
		List<Map<String,String>> list = new ArrayList<>();
		try {
			result.setError("保存成功");
			if (spuCommodity.getIs_banner() == 1){
				List<SpuCommodity> spuList = spuCommodityMapper.selectByIsBanner("1");
				if (spuList != null && spuList.size() > 0){
						result.setError("当前已经存在banner图");
						return result;
				}
			}
			if(StringUtil.isNotBlank(spuCommodity.getImages())){
				String[] url = spuCommodity.getImages().split(",");
				spuCommodity.setImages(JSON.toJSONString(url));
			}
			spuCommodity.setSpuid(StringUtil.zyRandom());
			spuCommodity.setSale_amount(1230);
			String[] sid = spuCommodity.getSpu_spec().split(",");
			for (int i = 0; i < sid.length; i++) {
				Map<String,String> map = new HashMap<>();
				Specifications specifications = specificationsMapper.selectById(sid[i]);
				map.put("spec_name", specifications.getSpec_name());
				map.put("spec_value", specifications.getSpec_value());
				map.put("id", String.valueOf(specifications.getId()));
				list.add(map);
			}
			spuCommodity.setSpu_spec(JSON.toJSONString(list));
			SupplierInfo supplierInfo = supplierInfoMapper.selectByUserId(String.valueOf(user.getId()));
			if(supplierInfo != null){
				spuCommodity.setS_id(supplierInfo.getS_id());
			}
			spuCommodity.setOn_sale(1);
			if(spuCommodity.getSpuDesc() != null){
				if(StringUtil.isNotBlank(spuCommodity.getSpuDesc().getSkudesc())){
					SpuDesc spuDesc = new SpuDesc();
					spuDesc.setCreate_time(new Date());
					spuDesc.setSkudesc(spuCommodity.getSpuDesc().getSkudesc());
					spuDesc.setSpuid(spuCommodity.getSpuid());
					spuDescMapper.addSpuDesc(spuDesc);
				}
			}
			spuCommodity.setCreate_time(new Date());
			result.setDraw(spuCommodityMapper.insertSelective(spuCommodity));
		} catch (Exception e) {
			result.setError("保存失败");
			logger.error("SPU保存失败", e);
		}
		return result;
	}
	
	@Override
	public DataTableResult editSupplierSpuData(SpuCommodity spuCommodity) {
		DataTableResult result = new DataTableResult();
		try {
			result.setError("修改成功");
			SpuCommodity spuCommodity2 = this.selectById(String.valueOf(spuCommodity.getId()));
			if(!spuCommodity2.getImages().equals(spuCommodity.getImages())){
				String[] url = spuCommodity.getImages().split(",");
				spuCommodity.setImages(JSON.toJSONString(url));
			}
			if(StringUtil.isNotBlank(spuCommodity.getSpuDesc().getSkudesc())){
				SpuDesc spuDesc = new SpuDesc();
				spuDesc.setSpuid(spuCommodity.getSpuid());
				SpuDesc spuDesc2 = spuDescMapper.selectByOther(spuDesc);
				if(spuDesc2 != null){
					spuDesc2.setSkudesc(spuCommodity.getSpuDesc().getSkudesc());
					spuDescMapper.updateSpuDesc(spuDesc2);
				}else{
					spuDesc.setCreate_time(new Date());
					spuDesc.setSkudesc(spuCommodity.getSpuDesc().getSkudesc());
					spuDescMapper.addSpuDesc(spuDesc);
				}
			}
			spuCommodity.setUpdate_time(new Date());
			result.setDraw(spuCommodityMapper.updateByPrimaryKeySelective(spuCommodity));
		} catch (Exception e) {
			result.setError("修改失败");
			logger.error("SPU修改失败", e);
		}
		return result;
	}
	
	
	@Override
	public DataTableResult editSpu(SpuCommodity spuCommodity) {
		DataTableResult result = new DataTableResult();
		BigDecimal newBig = BigDecimal.ZERO;
		try {
			result.setError("修改成功");

			List<SpuCommodity> spuList = spuCommodityMapper.selectByIsBanner("1");
			if (spuList != null && spuList.size() > 0 && spuCommodity.getIs_banner() == 1){
				if (!spuList.get(0).getId().equals(spuCommodity.getId())){
					result.setError("当前已经存在banner图");
					return result;
				}
			}

				if(StringUtil.isNotBlank(spuCommodity.getImages())){
					String[] url = spuCommodity.getImages().split(",");
					spuCommodity.setImages(JSON.toJSONString(url));
				}
			if(StringUtil.isNotBlank(spuCommodity.getSpuDesc().getSkudesc())){
				SpuDesc spuDesc = new SpuDesc();
				spuDesc.setSpuid(spuCommodity.getSpuid());
				SpuDesc spuDesc2 = spuDescMapper.selectByOther(spuDesc);
				if(spuDesc2 != null){
					spuDesc2.setSkudesc(spuCommodity.getSpuDesc().getSkudesc());
					spuDescMapper.updateSpuDesc(spuDesc2);
				}else{
					spuDesc.setCreate_time(new Date());
					spuDesc.setSkudesc(spuCommodity.getSpuDesc().getSkudesc());
					spuDescMapper.addSpuDesc(spuDesc);
				}
			}
			//手续比例
			BigDecimal serviceAmount = new BigDecimal(0.02);
			BigDecimal serviceCharge = serviceAmount.setScale(2,BigDecimal.ROUND_FLOOR);
			//手续费
//			BigDecimal servicePr = spuCommodity.getPrice().multiply(serviceCharge);
//			BigDecimal servicePrice = servicePr.setScale(2,BigDecimal.ROUND_FLOOR);
			//8人团差额 = 8人拼团价-(服务费+成本+佣金+(单价*0.02))
//			BigDecimal eightAmount2 = spuCommodity.getService_price().add(spuCommodity.getSupply_price()).add(spuCommodity.getCommission()).add(servicePrice).add(spuCommodity.getRefund_price());
//			spuCommodity.setEight_difference_price(spuCommodity.getPrice().subtract(eightAmount2));
			//5人团差额 = 5人拼团价-(服务费+成本+佣金+(单价*0.02))
//			BigDecimal fiveAmount2 = spuCommodity.getService_price().add(spuCommodity.getSupply_price()).add(spuCommodity.getCommission()).add(servicePrice).add(spuCommodity.getRefund_price());
//			spuCommodity.setFive_difference_price(spuCommodity.getPrice().subtract(fiveAmount2));
			//3人团差额 = 3人拼团价-(服务费+成本+佣金+(单价*0.02))
//			BigDecimal threeAmount2 = spuCommodity.getService_price().add(spuCommodity.getSupply_price()).add(spuCommodity.getCommission()).add(servicePrice).add(spuCommodity.getRefund_price());
//			spuCommodity.setThree_difference_price(spuCommodity.getPrice().subtract(threeAmount2));
			spuCommodity.setUpdate_time(new Date());

//			if (
//				spuCommodity.getEight_difference_price().compareTo(newBig) == -1 ||
//				spuCommodity.getFive_difference_price().compareTo(newBig) == -1 ||
//				spuCommodity.getThree_difference_price().compareTo(newBig) == -1
//			){
//				result.setError("金额错误");
//				return result;
//			}

			result.setDraw(spuCommodityMapper.updateByPrimaryKeySelective(spuCommodity));
			if(result.getDraw() == 1){
				Product product = new Product();
				product.setSpuid(spuCommodity.getSpuid());
				List<Product> productList = productMapper.selectByOther(product);
				for (Product product2 : productList) {
					product2.setGroup_price_eight(spuCommodity.getGroup_price_eight());
					product2.setGroup_price_five(spuCommodity.getGroup_price_five());
					product2.setGroup_price_three(spuCommodity.getGroup_price_three());
					product2.setCommission(spuCommodity.getCommission());
					product2.setMarket_price(spuCommodity.getMarket_price());
					product2.setSupply_price(spuCommodity.getSupply_price());
					product2.setService_price(spuCommodity.getService_price());
					product2.setPrice(spuCommodity.getPrice());
					product2.setCommission(spuCommodity.getCommission());
					product2.setCommission_price_three(spuCommodity.getCommission_price_three());
					product2.setCommission_price_five(spuCommodity.getCommission_price_five());
					product2.setCommission_price_eight(spuCommodity.getCommission_price_eight());
					product2.setRefund_price(spuCommodity.getRefund_price());
					productMapper.updateByPrimaryKeySelective(product2);
				}
			}
		} catch (Exception e) {
			result.setError("修改失败");
			logger.error("SPU修改失败", e);
		}
		return result;
	}

	@Override
	public DataTableResult maintainSpu(SpuCommodity spuCommodity) {
		DataTableResult result = new DataTableResult();
		Calendar rightNow = Calendar.getInstance();
    	rightNow.setTime(new Date());
		try {
			SpuCommodity spuCommodity2 = spuCommodityMapper.selectById(String.valueOf(spuCommodity.getId()));
			int a = 0;
			Rich rich = new Rich();
			rich.setSpuid(spuCommodity2.getSpuid());
			List<Rich> list = richMapper.selectRichByOthers(rich);
			for (Rich rich2 : list) {
				if(rich2.getType() == 3 || rich2.getType() == 12){
					a = 1;
				}
			}
			if(a == 0){
				result.setError("选择维护成功,请等待");
				BigDecimal duration = spuCommodity2.getValid_duration();
				BigDecimal one = new BigDecimal(1.00);
				BigDecimal hours = new BigDecimal(60.00);
				int duration2 = duration.intValue();

				spuCommodity.setMaintain_status(1);
				spuCommodity.setMaintain_start_time(new Date());
				Date endTime = DateUtil.getAddMinute(spuCommodity2.getValid_duration().multiply(hours).intValue());
				spuCommodity.setMaintain_end_time(endTime);
				if(duration.compareTo(one)==1 || duration.compareTo(one)==0){
					rightNow.add(Calendar.HOUR_OF_DAY,duration2);
				}if(duration.compareTo(one)==-1){
					int duration3 = duration.multiply(hours).intValue();
					rightNow.add(Calendar.MINUTE, duration3);
				}
				String cron = CronUtil.getCron(rightNow.getTime());  
				Map<String,Object> map = new HashMap<>();
				map.put("id",spuCommodity.getId());
				jobAndTriggerService.addJob("com.data.display.quartz.ProductMaintainJob","adminGroup-"+spuCommodity.getId()+"", cron, "维护商品任务", map);
				result.setDraw(spuCommodityMapper.editOnSale(spuCommodity));
			}else{
				result.setError("当前SPU中包含首页海报或首页弹窗海报,请移植其他Spu中再进行维护");
			}
		} catch (Exception e) {
			result.setError("维护失败");
			logger.error("SPU维护失败", e);
		}
		return result;
	}

	@Override
	public DataTableResult editOnSale(SpuCommodity spuCommodity) {
		DataTableResult result = new DataTableResult();
		try {
			result.setError("审核成功");
			SpuCommodity spuCommodity2 = this.selectById(String.valueOf(spuCommodity.getId()));
			TopLevel topLevel = new TopLevel();
			topLevel.setSpuid(spuCommodity2.getSpuid());
			List<TopLevel> list = topLevelMapper.selectBySpuid(topLevel);
			if(list.size() <= 0){
				result.setError("该SPU下没有顶级账户,请选择顶级账户后再进行上架");
				return result;
			}
			if(spuCommodity2.getOn_sale() == 0){
				result.setError("已经为上架状态");
				return result;
			}
			Product product = new Product();
			product.setSpuid(spuCommodity2.getSpuid());
			List<Product> pList = productMapper.selectByOther(product);
			int s = 0;
			if(pList.size() > 0 && pList != null){
				for (Product product2 : pList) {
					if(product2.getOn_sale()==0){
						s = 1;
						break;
					}
				}
				if(s == 0){
					result.setError("该SPU下没有上架状态的SKU，不能上架");
					return result;
				}
			}else{
				result.setError("该SPU下没有SKU，不能上架");
				return result;
			}
			spuCommodity.setMaintain_status(0);
			result.setDraw(spuCommodityMapper.editOnSale(spuCommodity));
		} catch (Exception e) {
			result.setError("审核失败");
		}
		return result;
	}

	@Override
	public Page<SpuCommodity> getExamineSpuData(DataTableDTO dataTableDTO, SpuCommodity spuCommodity) {
		PageHelper.startPage(dataTableDTO.getPageNum(), dataTableDTO.getLength());
		return spuCommodityMapper.getExamineSpuData(spuCommodity);
	}

}
