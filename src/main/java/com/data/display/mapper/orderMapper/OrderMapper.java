package com.data.display.mapper.orderMapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.data.display.model.order.Order;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
	
	 /**
	  * 添加
	  * @param record
	  * @return
	  */
	 int insert(Order record);
	 int insertSelective(Order record);
	 
	 /**
	  * 删除
	  * @param id
	  * @return
	  */
     int deleteByPrimaryKey(Long id);

   
     /**
      * 查询
      * @param
      * @return
      */
     Page<Order> selectByPrimaryKey(Order order);
     
     Page<Order> getOrderByAfterStatus(Order order);
     
     Page<Order> getOrderByAfterStatus2(Order order);
     
     Order selectById(String id); 

     /**
      * 修改
      * @param record
      * @return
      */
	 int updateByPrimaryKeySelective(Order record);

	 int updateByOrderNo(Order record);

	 int updateByPrimaryKey(Order record);
	 
	 List<Order> selectByOthers(Order order);

	 List<Order> selectByOthers2(Order order);

	 List<Order> selectByOthers3(Order order);

	 Map<String,Object> getOtherData(String id);

	 List<Map<String,Object>> exportPrem(@Param("s_id")String s_id,@Param("spuid")String spuid);

	/**
	 * 获取当日支付订单
	 * @return
	 */
	List<Order> selectPaymented(@Param("payTime") String payTime,@Param("type") String type);


	//获取合伙人对应的退款信息
	List<Order> getRefundData(@Param("beginDate")String beginDate,@Param("endDate")String endDate,@Param("parId")String parId,@Param("spuid")String spuid);
	//获取合伙人对应的所有订单信息
	List<Order> getCountData(@Param("beginDate")String beginDate,@Param("endDate")String endDate,@Param("parId")String parId,@Param("spuid")String spuid);

	/**
	 * 获取发货状态没更新的数据
	 * @return
	 */
	List<Order> getList();

	/**
	 * 获取合伙人/股东上月订单总盒数
	 * @param time
	 * @param parId
	 * @param stocId
	 */
	Integer getLastMonthData(@Param("time")String time,@Param("parId")String parId,@Param("stocId")String stocId);


    List<Map<String, Object>> exportRefund();

}