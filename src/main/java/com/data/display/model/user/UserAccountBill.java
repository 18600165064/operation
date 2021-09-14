package com.data.display.model.user;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户账户流水
 * @author l
 *
 */
public class UserAccountBill extends ParentComment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 流水编号
	 */
	private Integer bill_id;
	/**
	 * 前端用户id'
	 */
	private Integer user_id;
	/**
	 * 流水类型(+-)
	 */
	private String type;
	/**
	 * 变动金额
	 */
	private BigDecimal change_amt;
	/**
	 * '钱包余额
	 */
	private BigDecimal wallet;
	/**
	 * 流水说明
	 */
	private String remark;
	/**
	 * 创建时间
	 */
	private Date create_time;
	
	public Integer getBill_id() {
		return bill_id;
	}
	public void setBill_id(Integer bill_id) {
		this.bill_id = bill_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BigDecimal getChange_amt() {
		return change_amt;
	}
	public void setChange_amt(BigDecimal change_amt) {
		this.change_amt = change_amt;
	}
	public BigDecimal getWallet() {
		return wallet;
	}
	public void setWallet(BigDecimal wallet) {
		this.wallet = wallet;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}
