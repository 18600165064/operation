package com.data.display.model.supplier;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 供应商账户流水
 * @author l
 *
 */
public class SupplierWithdrawJournal extends ParentComment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * ID
	 */
	private Integer id;
	/**
	 * 审核状态 0未审核 1已转账 2 未转账
	 */
	private 	Integer status; 
	/**
	 * 供应商id
	 */
	private Integer sid;
	/**
	 *提现金额 
	 */
	private BigDecimal withdraw;
	/**
	 * 创建时间
	 */
	private Date create_time;
	/**
	 * 提现单号
	 * @return
	 */
	private String withdrawal_no;
	
	public String getWithdrawal_no() {
		return withdrawal_no;
	}
	public void setWithdrawal_no(String withdrawal_no) {
		this.withdrawal_no = withdrawal_no;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public BigDecimal getWithdraw() {
		return withdraw;
	}
	public void setWithdraw(BigDecimal withdraw) {
		this.withdraw = withdraw;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}
