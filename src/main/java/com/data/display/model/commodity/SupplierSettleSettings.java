package com.data.display.model.commodity;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SupplierSettleSettings extends ParentComment implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
 	 * 指定区域运费表id
 	 */
    private Integer id;
	/**
 	 * 首件
 	 */
    private Integer first_tag;
	/**
 	 * 首费
 	 */
    private BigDecimal first_price;
	/**
 	 * 续件
 	 */
    private Integer second_tag;
	/**
 	 * 续费
 	 */
    private BigDecimal second_price;
	/**
 	 * 区域
 	 */
    private String city;
	/**
 	 * 模板id
 	 */
    private Integer shipping_id;
	/**
 	 * 创建时间
 	 */
    private Date create_time;
    
    private String cityName;
    
    private String nodels;
    
    
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getNodels() {
		return nodels;
	}
	public void setNodels(String nodels) {
		this.nodels = nodels;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getFirst_tag() {
		return first_tag;
	}
	public void setFirst_tag(Integer first_tag) {
		this.first_tag = first_tag;
	}
	public BigDecimal getFirst_price() {
		return first_price;
	}
	public void setFirst_price(BigDecimal first_price) {
		this.first_price = first_price;
	}
	public Integer getSecond_tag() {
		return second_tag;
	}
	public void setSecond_tag(Integer second_tag) {
		this.second_tag = second_tag;
	}
	public BigDecimal getSecond_price() {
		return second_price;
	}
	public void setSecond_price(BigDecimal second_price) {
		this.second_price = second_price;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Integer getShipping_id() {
		return shipping_id;
	}
	public void setShipping_id(Integer shipping_id) {
		this.shipping_id = shipping_id;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

}
