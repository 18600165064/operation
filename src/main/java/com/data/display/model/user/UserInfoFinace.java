package com.data.display.model.user;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UserInfoFinace extends ParentComment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * ID
	 */
	private Integer id;
	/**
	 * userId
	 */
	private Integer user_id;
	/**
	 * 冻结金额
	 */
	private BigDecimal frozen;
	/**
	 * 可提现金额
	 */
	private BigDecimal can_withdraw;
	/**
	 * 累计提现金额
	 */
	private BigDecimal already_withdraw;
	/**
	 * 创建时间
	 */
	private Date create_time;
	/**
	 * 可提现次数
	 * @return
	 */
	private Integer can_withdraw_times;


	public Integer getCan_withdraw_times() {
		return can_withdraw_times;
	}

	public void setCan_withdraw_times(Integer can_withdraw_times) {
		this.can_withdraw_times = can_withdraw_times;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public BigDecimal getFrozen() {
		return frozen;
	}
	public void setFrozen(BigDecimal frozen) {
		this.frozen = frozen;
	}
	public BigDecimal getCan_withdraw() {
		return can_withdraw;
	}
	public void setCan_withdraw(BigDecimal can_withdraw) {
		this.can_withdraw = can_withdraw;
	}
	public BigDecimal getAlready_withdraw() {
		return already_withdraw;
	}
	public void setAlready_withdraw(BigDecimal already_withdraw) {
		this.already_withdraw = already_withdraw;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}
