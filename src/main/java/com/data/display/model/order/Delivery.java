package com.data.display.model.order;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.util.Date;

/**
 * 物流详情
 * @author l
 *
 */
public class Delivery extends ParentComment implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	private Integer id;
	/**
	 * 物流公司编号
	 */
	private String ec_code;
	/**
	 * 运单编号
	 */
	private String nu;
	/**
	 * 物流详情
	 */
	private String details;
	/**
	 * 状态包括 0在途中、1已揽收、2疑难、3已签收、4退签、5同城派送中、6退回、7转单
	 */
	private String  state;
	/**
	 * 创建时间
	 */
	private Date create_time;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEc_code() {
		return ec_code;
	}
	public void setEc_code(String ec_code) {
		this.ec_code = ec_code;
	}
	public String getNu() {
		return nu;
	}
	public void setNu(String nu) {
		this.nu = nu;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}
