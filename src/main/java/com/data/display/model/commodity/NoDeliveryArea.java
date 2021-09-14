package com.data.display.model.commodity;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.util.Date;

/**
 * 不发货地区
 * @author l
 *
 */
public class NoDeliveryArea extends ParentComment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * ID
	 */
	private Integer n_id;
	/**
	 * 供应商id
	 */
	private Integer s_id;
	/**
	 * 运费模板id
	 */
	private Integer shipping_id;
	/**
	 * 区域id
	 */
	private String city;
	/**
	 * 创建时间
	 */
	private Date createdate;
	/**
	 * 创建时间
	 */
	private Date updatedate;
	public Integer getN_id() {
		return n_id;
	}
	public void setN_id(Integer n_id) {
		this.n_id = n_id;
	}
	public Integer getS_id() {
		return s_id;
	}
	public void setS_id(Integer s_id) {
		this.s_id = s_id;
	}
	public Integer getShipping_id() {
		return shipping_id;
	}
	public void setShipping_id(Integer shipping_id) {
		this.shipping_id = shipping_id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	
}
