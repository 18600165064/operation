package com.data.display.model.order;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 退款详情
 * @author l
 *
 */
public class RefundDetails extends ParentComment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * ID
	 */
	private Integer id;
	/**
	 * 主订单编号
	 */
	private String order_no;
	/**
	 * 子订单编号
	 */
	private String sub_order_no;
	/**
	 * 订单金额（主订单金额）
	 */
	private BigDecimal order_money;
	/**
	 * 退款金额
	 */
	private BigDecimal refund_money;
	/**
	 * 1：部分退款 2：全部退款
	 */
	private Integer if_refund;
	/**
	 * 退款时间
	 */
	private Date create_time;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getSub_order_no() {
		return sub_order_no;
	}
	public void setSub_order_no(String sub_order_no) {
		this.sub_order_no = sub_order_no;
	}
	public BigDecimal getOrder_money() {
		return order_money;
	}
	public void setOrder_money(BigDecimal order_money) {
		this.order_money = order_money;
	}
	public BigDecimal getRefund_money() {
		return refund_money;
	}
	public void setRefund_money(BigDecimal refund_money) {
		this.refund_money = refund_money;
	}
	public Integer getIf_refund() {
		return if_refund;
	}
	public void setIf_refund(Integer if_refund) {
		this.if_refund = if_refund;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}
