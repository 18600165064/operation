package com.data.display.model.commodity;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Shipping extends ParentComment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 运费模板主键id
	 */
	private Integer id;
	/**
	 * 模板名称
	 */
	private String name;
	/**
	 * 供应商ID
	 */
	private Integer s_id;
	/**
	 * 加价包邮邮费
	 */
	private BigDecimal type;
	/**
	 * 创建时间
	 */
	private Date create_time;
	/**
	 *邮费 
	 */
	private BigDecimal paid_shipping;
	/**
	 * 满免
	 */
	private BigDecimal full;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getS_id() {
		return s_id;
	}
	public void setS_id(Integer s_id) {
		this.s_id = s_id;
	}
	public BigDecimal getType() {
		return type;
	}
	public void setType(BigDecimal type) {
		this.type = type;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public BigDecimal getPaid_shipping() {
		return paid_shipping;
	}
	public void setPaid_shipping(BigDecimal paid_shipping) {
		this.paid_shipping = paid_shipping;
	}
	public BigDecimal getFull() {
		return full;
	}
	public void setFull(BigDecimal full) {
		this.full = full;
	}
}
