package com.data.display.model.user;

import com.data.display.model.dto.ParentComment;

import java.util.Date;

public class StaffRelation extends ParentComment {

	private Integer user_id;
	
	private Integer director_id;
	
	private Integer charge_id;
	
	private Integer level_u;
	
	private Integer status;
	
	private Date create_time;
	
	private String nick_name;


	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getDirector_id() {
		return director_id;
	}

	public void setDirector_id(Integer director_id) {
		this.director_id = director_id;
	}

	public Integer getCharge_id() {
		return charge_id;
	}

	public void setCharge_id(Integer charge_id) {
		this.charge_id = charge_id;
	}

	public Integer getLevel_u() {
		return level_u;
	}

	public void setLevel_u(Integer level_u) {
		this.level_u = level_u;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
