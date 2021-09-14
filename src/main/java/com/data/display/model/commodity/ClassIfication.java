package com.data.display.model.commodity;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.util.Date;

public class ClassIfication extends ParentComment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 类目级别
	 */
	private Integer cate_level;
	/**
	 * 上级类目id
	 */
	private Integer uper_id;
	/**
	 * 类目名称
	 */
	private String cate_name;
	/**
	 * 类目图标
	 */
	private String cate_icon;
	/**
	 * 创建时间
	 */
	private Date create_time;
	/**
	 * 是否显示
	 */
	private Integer is_show;
	
	public Integer getIs_show() {
		return is_show;
	}
	public void setIs_show(Integer is_show) {
		this.is_show = is_show;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCate_level() {
		return cate_level;
	}
	public void setCate_level(Integer cate_level) {
		this.cate_level = cate_level;
	}
	public Integer getUper_id() {
		return uper_id;
	}
	public void setUper_id(Integer uper_id) {
		this.uper_id = uper_id;
	}
	public String getCate_name() {
		return cate_name;
	}
	public void setCate_name(String cate_name) {
		this.cate_name = cate_name;
	}
	public String getCate_icon() {
		return cate_icon;
	}
	public void setCate_icon(String cate_icon) {
		this.cate_icon = cate_icon;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}
