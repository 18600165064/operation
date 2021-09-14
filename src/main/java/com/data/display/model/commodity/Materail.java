package com.data.display.model.commodity;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.util.Date;

public class Materail extends ParentComment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * spu编号
	 */
	private String spuid;
	/**
	 * sku编号
	 */
	private String skuid;
	/**
	 * 描述
	 */
	private String descr;
	/**
	 * 轮播图(","分割)
	 */
	private String images;
	/**
	 * 视频地址
	 */
	private String video_url;
	/**
	 * 视频封面
	 */
	private String video_image;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 创建时间
	 * @return
	 */
	private Date create_time;
	
	private String list;
	
	public String getList() {
		return list;
	}
	public void setList(String list) {
		this.list = list;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSpuid() {
		return spuid;
	}
	public void setSpuid(String spuid) {
		this.spuid = spuid;
	}
	public String getSkuid() {
		return skuid;
	}
	public void setSkuid(String skuid) {
		this.skuid = skuid;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getVideo_url() {
		return video_url;
	}
	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}
	public String getVideo_image() {
		return video_image;
	}
	public void setVideo_image(String video_image) {
		this.video_image = video_image;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}
