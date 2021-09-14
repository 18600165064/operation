package com.data.display.model.user;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UserWithdraw extends ParentComment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 记录ID
	 */
	private Integer id;
	/**
	 * 前台用户ID
	 */
	private Integer user_id;
	/**
	 * 后台管理员编号
	 */
	private Integer back_user_id;
	/**
	 * 订单号
	 */
	private String order_no;
	/**
	 * 兑现总金额
	 */
	private BigDecimal total;
	/**
	 * 从余额部分提取金额
	 */
	private BigDecimal money;
	/**
	 * 卡号
	 */
	private String card_no;
	/**
	 * '提现请求状态，0提交,1审核,2打款,3完成,4拒绝,5关闭
	 */
	private Integer status;
	/**
	 * 创建时间
	 */
	private Date create_time;
	/**
	 * 审核时间
	 */
	private Date audit_time;
	/**
	 * 处理时间
	 */
	private Date process_time;
	/** 
	 * 总金额
	 */
	private BigDecimal total_money;
	/**
	 * 完成时间
	 */
	private Date finish_time;
	/**
	 * 手续费
	 */
	private BigDecimal poundage;

	/**
	 * 开始时间
	 * @return
	 */
	private String strDate;
	private String endDate;


	public String getStrDate() {
		return strDate;
	}

	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
	public Integer getBack_user_id() {
		return back_user_id;
	}
	public void setBack_user_id(Integer back_user_id) {
		this.back_user_id = back_user_id;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public String getCard_no() {
		return card_no;
	}
	public void setCard_no(String card_no) {
		this.card_no = card_no;
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
	public Date getAudit_time() {
		return audit_time;
	}
	public void setAudit_time(Date audit_time) {
		this.audit_time = audit_time;
	}
	public Date getProcess_time() {
		return process_time;
	}
	public void setProcess_time(Date process_time) {
		this.process_time = process_time;
	}
	public BigDecimal getTotal_money() {
		return total_money;
	}
	public void setTotal_money(BigDecimal total_money) {
		this.total_money = total_money;
	}
	public Date getFinish_time() {
		return finish_time;
	}
	public void setFinish_time(Date finish_time) {
		this.finish_time = finish_time;
	}
	public BigDecimal getPoundage() {
		return poundage;
	}
	public void setPoundage(BigDecimal poundage) {
		this.poundage = poundage;
	}
}
