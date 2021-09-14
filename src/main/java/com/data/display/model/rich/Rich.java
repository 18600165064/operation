package com.data.display.model.rich;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.util.Date;

public class Rich extends ParentComment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 图片
	 */
	private String image;
	/**
	 * spuid
	 */
	private String spuid;
	/**
	 * 视频地址
	 */
	private String video_url;
	/**
	 * 富文本
	 */
	private String content;
	/**
	 * 跳转页
	 */
	private Integer jump_page;
	/**
	 * 浏览数量
	 */
	private Integer browse_num;
	/**
	 * 是否置顶
	 */
	private Integer is_top;
	/**
	 * 类型 1 欢迎语 2 常见问题 3 首页海报 4 系统公告 5 拼团赚钱 6 拼团规则 7 他咋拼  8宣言  9我要开团海报
	 */
	private Integer type;
	/**
	 * 创建时间
	 */
	private Date create_time;
	/**
	 * 更新时间
	 */
	private Date update_time;
	/**
	 * 状态
	 */
	private Integer status;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getSpuid() {
		return spuid;
	}
	public void setSpuid(String spuid) {
		this.spuid = spuid;
	}
	public String getVideo_url() {
		return video_url;
	}
	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getJump_page() {
		return jump_page;
	}
	public void setJump_page(Integer jump_page) {
		this.jump_page = jump_page;
	}
	public Integer getBrowse_num() {
		return browse_num;
	}
	public void setBrowse_num(Integer browse_num) {
		this.browse_num = browse_num;
	}
	public Integer getIs_top() {
		return is_top;
	}
	public void setIs_top(Integer is_top) {
		this.is_top = is_top;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
