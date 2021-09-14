package com.data.display.model.order;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderSettle extends ParentComment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private Integer id;
	/**
	 * APP前端用户ID
	 */
	private Integer user_id;
	/**
	 * 订单ID
	 */
	private String order_no;
	/**
	 * 子订单号
	 */
	private String sub_order_no;
	/**
	 * 唯一值
	 */
	private String sub_order_no_skuid;
	/**
	 * 批次号
	 */
	private String batch_no;
	/**
	 * 供应商编号
	 */
	private Integer supplier_id;
	/**
	 * sku编号
	 */
	private String skuid;
	/**
	 * sku名称
	 */
	private String sku_name;
	/**
	 * sku主图
	 */
	private String sku_image;
	/**
	 * 供货价
	 */
	private BigDecimal supply_price;
	/**
	 * 单价
	 */
	private BigDecimal price;
	/**
	 *数量 
	 */
	private Integer num;
	/**
	 * 小计
	 */
	private BigDecimal subtotal;
	/**
	 *退款金额 
	 */
	private BigDecimal refund_amt;
	/**
	 * 清算金额
	 */
	private BigDecimal settle_amt;
	/**
	 * 清算状态(0:未清算 1:已清算2:处理中)
	 */
	private Integer settle_status;
	/**
	 * 
	 */
	private Date create_time;
	/**
	 * 运费
	 */
	private BigDecimal freight;
	
	private Integer settle_status2;
	
	
	
	public Integer getSettle_status2() {
		return settle_status2;
	}
	public void setSettle_status2(Integer settle_status2) {
		this.settle_status2 = settle_status2;
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
	public String getSub_order_no_skuid() {
		return sub_order_no_skuid;
	}
	public void setSub_order_no_skuid(String sub_order_no_skuid) {
		this.sub_order_no_skuid = sub_order_no_skuid;
	}
	public String getBatch_no() {
		return batch_no;
	}
	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}
	public Integer getSupplier_id() {
		return supplier_id;
	}
	public void setSupplier_id(Integer supplier_id) {
		this.supplier_id = supplier_id;
	}
	public String getSkuid() {
		return skuid;
	}
	public void setSkuid(String skuid) {
		this.skuid = skuid;
	}
	public String getSku_name() {
		return sku_name;
	}
	public void setSku_name(String sku_name) {
		this.sku_name = sku_name;
	}
	public String getSku_image() {
		return sku_image;
	}
	public void setSku_image(String sku_image) {
		this.sku_image = sku_image;
	}
	public BigDecimal getSupply_price() {
		return supply_price;
	}
	public void setSupply_price(BigDecimal supply_price) {
		this.supply_price = supply_price;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	public BigDecimal getRefund_amt() {
		return refund_amt;
	}
	public void setRefund_amt(BigDecimal refund_amt) {
		this.refund_amt = refund_amt;
	}
	public BigDecimal getSettle_amt() {
		return settle_amt;
	}
	public void setSettle_amt(BigDecimal settle_amt) {
		this.settle_amt = settle_amt;
	}
	public Integer getSettle_status() {
		return settle_status;
	}
	public void setSettle_status(Integer settle_status) {
		this.settle_status = settle_status;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public BigDecimal getFreight() {
		return freight;
	}
	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}
	
}
