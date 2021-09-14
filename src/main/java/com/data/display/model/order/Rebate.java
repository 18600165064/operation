package com.data.display.model.order;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Rebate extends ParentComment implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	private Integer id;
	/**
	 * 获取佣金的用户
	 */
	private Integer user_id;
	/**
	 * 来源用户id
	 */
	private Integer source_id;
	/**
	 * 订单号
	 */
	private String order_no;
	/**
	 * 子订单号
	 */
	private String sub_order_no;
	/**
	 * 团号
	 */
	private String group_num;
	/**
	 * sku
	 */
	private String skuid;
	/**
	 * 佣金
	 */
	private BigDecimal commission;
	/**
	 * 层数
	 */
	private Integer layer;
	/**
	 * 分佣比例
	 */
	private BigDecimal ratio;
	/**
	 * 创建时间
	 */
	private Date create_time;
	/**
	 * 更新时间
 	 */
	private Date update_time;
	/**
	 * 结算时间
	 */
	private Date settle_time;
	/**
	 * 状态 0,待确认;2,已确认;3已取消,1,已结算
	 */
	private Integer status;

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public String getSub_order_no() {
		return sub_order_no;
	}
	public void setSub_order_no(String sub_order_no) {
		this.sub_order_no = sub_order_no;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getSource_id() {
		return source_id;
	}
	public void setSource_id(Integer source_id) {
		this.source_id = source_id;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getGroup_num() {
		return group_num;
	}
	public void setGroup_num(String group_num) {
		this.group_num = group_num;
	}
	public String getSkuid() {
		return skuid;
	}
	public void setSkuid(String skuid) {
		this.skuid = skuid;
	}
	public BigDecimal getCommission() {
		return commission;
	}
	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}
	public Integer getLayer() {
		return layer;
	}
	public void setLayer(Integer layer) {
		this.layer = layer;
	}
	public BigDecimal getRatio() {
		return ratio;
	}
	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getSettle_time() {
		return settle_time;
	}
	public void setSettle_time(Date settle_time) {
		this.settle_time = settle_time;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
