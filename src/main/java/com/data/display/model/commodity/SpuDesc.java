package com.data.display.model.commodity;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.util.Date;

public class SpuDesc extends ParentComment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	private Integer id;
	/**
	 * sku编号
	 */
	private String skuid;
	/**
	 * spu编号
	 */
	private String spuid;
	/**
	 * 详情
	 */
	private String skudesc;
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
	public String getSkuid() {
		return skuid;
	}
	public void setSkuid(String skuid) {
		this.skuid = skuid;
	}
	public String getSpuid() {
		return spuid;
	}
	public void setSpuid(String spuid) {
		this.spuid = spuid;
	}
	public String getSkudesc() {
		return skudesc;
	}
	public void setSkudesc(String skudesc) {
		this.skudesc = skudesc;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}
