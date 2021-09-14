package com.data.display.model.operation;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.util.Date;

public class OperationRecord extends ParentComment implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;  //id
	private Integer uid; //用户id
	private String request_url;  //请求url
	private String request_method; //请求方式
	private String request_parameter; //请求参数
	private Date craete_time; // 创建时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getRequest_url() {
		return request_url;
	}
	public void setRequest_url(String request_url) {
		this.request_url = request_url;
	}
	public String getRequest_method() {
		return request_method;
	}
	public void setRequest_method(String request_method) {
		this.request_method = request_method;
	}
	public String getRequest_parameter() {
		return request_parameter;
	}
	public void setRequest_parameter(String request_parameter) {
		this.request_parameter = request_parameter;
	}
	public Date getCraete_time() {
		return craete_time;
	}
	public void setCraete_time(Date craete_time) {
		this.craete_time = craete_time;
	}
	
	
}
