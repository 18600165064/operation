package com.data.display.model.user;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.util.Date;

public class UserInfo extends ParentComment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	private Integer user_id;
	/**
	 * 唯一标识
	 */
	private String unionid;
	/**
	 * 小程序open_id
	 */
	private String open_id_small;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 昵称
	 */
	private String nick_name;
	/**
	 * 头像
	 */
	private String head_icon;
	/**
	 * 性别
	 */
	private Integer sex;
	/**
	 *年龄 
	 */
	private String age;
	/**
	 * 创建时间
	 */
	private Date create_time;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 0 普通用户  1 白名单用户 2 黑名单用户
	 */
	private Integer roster;
	/**
	 * 生日
	 */
	private String birthday;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 宣言
	 */
	private String declaration;
	/**
	 * 二维码
	 */
	private String two_code;
	/**
	 * 微信号码
	 */
	private String wechat_num;
	/**
	 * 0 可提现 1 不可提现
	 */
	private Integer identity;
	/**
	 * 0 三级分佣 1 无限分佣
	 */
	private Integer infinite;
	/**
	 * 0 启用  1 禁用
	 */
	private Integer usage_status;
	/**
	 * 角色状态
	 */
	private Integer other_status;
	/**
	 * 更新时间
	 */
	private Date update_time;
	
	private Integer updateId;

	private Integer protect_day;

	private String spuid;

	public String getSpuid() {
		return spuid;
	}

	public void setSpuid(String spuid) {
		this.spuid = spuid;
	}

	public Integer getProtect_day() {
		return protect_day;
	}

	public void setProtect_day(Integer protect_day) {
		this.protect_day = protect_day;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public Integer getOther_status() {
		return other_status;
	}
	public void setOther_status(Integer other_status) {
		this.other_status = other_status;
	}
	public Integer getUsage_status() {
		return usage_status;
	}
	public void setUsage_status(Integer usage_status) {
		this.usage_status = usage_status;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDeclaration() {
		return declaration;
	}
	public void setDeclaration(String declaration) {
		this.declaration = declaration;
	}
	public String getTwo_code() {
		return two_code;
	}
	public void setTwo_code(String two_code) {
		this.two_code = two_code;
	}
	public String getWechat_num() {
		return wechat_num;
	}
	public void setWechat_num(String wechat_num) {
		this.wechat_num = wechat_num;
	}
	public Integer getIdentity() {
		return identity;
	}
	public void setIdentity(Integer identity) {
		this.identity = identity;
	}
	public Integer getInfinite() {
		return infinite;
	}
	public void setInfinite(Integer infinite) {
		this.infinite = infinite;
	}
	public Integer getUpdateId() {
		return updateId;
	}
	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getOpen_id_small() {
		return open_id_small;
	}
	public void setOpen_id_small(String open_id_small) {
		this.open_id_small = open_id_small;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public String getHead_icon() {
		return head_icon;
	}
	public void setHead_icon(String head_icon) {
		this.head_icon = head_icon;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getRoster() {
		return roster;
	}
	public void setRoster(Integer roster) {
		this.roster = roster;
	}
	  
}
