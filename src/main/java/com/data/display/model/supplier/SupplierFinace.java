package com.data.display.model.supplier;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SupplierFinace extends ParentComment implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Integer id;
	/**
	 * 供应商id
	 */
	private Integer sid;
	/**
	 * 保证金
	 */
	private BigDecimal bond;
	/**
	 * 总收入
	 */
	private BigDecimal income;
	/**
	 * 冻结金额
	 */
	private BigDecimal frozen;
	/**
	 * 可提现金额
	 */
	private BigDecimal can_withdraw;
	/**
	 * 退款金额
	 */
	private BigDecimal refund;
	/**
	 * 累计提现金额
	 */
	private BigDecimal already_withdraw;
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
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public BigDecimal getBond() {
		return bond;
	}
	public void setBond(BigDecimal bond) {
		this.bond = bond;
	}
	public BigDecimal getIncome() {
		return income;
	}
	public void setIncome(BigDecimal income) {
		this.income = income;
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
	public BigDecimal getRefund() {
		return refund;
	}
	public void setRefund(BigDecimal refund) {
		this.refund = refund;
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
