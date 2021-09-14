package com.data.display.model.order;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.util.Date;

/**
 * 快递公司
 * @author l
 *
 */
public class ExpressCompany extends ParentComment implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 快递公司名称
	 */
	private String express_name;
	/**
	 * 公司编码
	 */
	private String company_code;
	/**
	 * 说明
	 */
	private String remark;
	/**
	 * 公司类型
	 */
	private String company_type;
	/**
	 * 国家编码
	 */
	private String country_code;
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
	public String getExpress_name() {
		return express_name;
	}
	public void setExpress_name(String express_name) {
		this.express_name = express_name;
	}
	public String getCompany_code() {
		return company_code;
	}
	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCompany_type() {
		return company_type;
	}
	public void setCompany_type(String company_type) {
		this.company_type = company_type;
	}
	public String getCountry_code() {
		return country_code;
	}
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}
