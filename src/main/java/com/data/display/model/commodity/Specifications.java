package com.data.display.model.commodity;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.util.Date;

public class Specifications extends ParentComment implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	private Integer id;
	/**
	 * 规格名称
	 */
	private String spec_name;
	/**
	 * 规格值
	 */
	private String spec_value;
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
	public String getSpec_name() {
		return spec_name;
	}
	public void setSpec_name(String spec_name) {
		this.spec_name = spec_name;
	}
	public String getSpec_value() {
		return spec_value;
	}
	public void setSpec_value(String spec_value) {
		this.spec_value = spec_value;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	
}
