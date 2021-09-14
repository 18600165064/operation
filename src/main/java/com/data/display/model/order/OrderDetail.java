package com.data.display.model.order;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单详情
 * @author l
 *
 */
public class OrderDetail extends ParentComment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 用户ID
	 */
	private Integer user_id;
	/**
	 * 订单号
	 */
	private String order_no;
	/**
	 * 子订单号
	 */
	private String sub_order_no;
	/**
	 * 产品标识
	 */
	private String skuid;
	/**
	 * 商品名
	 */
	private String sku_name;
	/**
	 * 图片链接
	 */
	private String imagePath;
	/**
	 * 数量
	 */
	private Integer num;
	/**
	 * 供货价
	 */
	private BigDecimal supply_price;
	/**
	 * 单价
	 */
	private BigDecimal price;
	/**
	 * 小计
	 */
	private BigDecimal subtotal;
	/**
	 * 佣金小计
	 */
	private BigDecimal commission;
	/**
	 *创建时间 
	 */
	private Date create_time;
	/**
	 * 0,未支付;1,已支付
	 */
	private Integer pay_status;
	/**
	 * 退货原因
	 */
	private String refund_reason;
	/** 
	 * 退款金额
	 */
	private BigDecimal refund_amt;
	/**
	 * 退货数量
	 */
	private Integer refund_num;
	/**
	 * 退款说明
	 */
	private String refund_remark;
	/**
	 * 应结算金额
	 */
	private BigDecimal should_liquidation_money;
	/**
	 * 退款凭证(图片)
	 */ 
	private String refund_voucher;
	/**
	 * 退货地址ID
	 */
	private Integer refund_addr_id;
	/**
	 * 退货联系人
	 */
	private String refund_name;
	/**
	 * 退货联系电话
	 */
	private String refund_mobile;
	/**
	 * 退货详细地址
	 */
	private String refund_addr_detail;
	/**
	 * 物流公司代码
	 */
	private String trans_com;
	/**
	 * 物流单号
	 */
	private String trans_id;
	/**
	 * 退款状态(0:待审核,1:审核拒绝,2:审核通过,3:退换货入库,4:已退款)
	 */
	private String refund_status;
	/**
	 * 申请退款时间
	 */
	private Date apply_time;
	/**
	 * 客服审核备注
	 */
	private String kefu_remark;
	/**
	 * 客服审核时间
	 */
	private Date kefu_time;
	/**
	 * 供应商操作入库时间
	 */
	private Date supplier_time;
	/**
	 * 审核通过,确认退款时间
	 */
	private Date check_time;
	/**
	 * 订单包裹号
	 */
	private String package_id;
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
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
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
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	public BigDecimal getCommission() {
		return commission;
	}
	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Integer getPay_status() {
		return pay_status;
	}
	public void setPay_status(Integer pay_status) {
		this.pay_status = pay_status;
	}
	public String getRefund_reason() {
		return refund_reason;
	}
	public void setRefund_reason(String refund_reason) {
		this.refund_reason = refund_reason;
	}
	public BigDecimal getRefund_amt() {
		return refund_amt;
	}
	public void setRefund_amt(BigDecimal refund_amt) {
		this.refund_amt = refund_amt;
	}
	public Integer getRefund_num() {
		return refund_num;
	}
	public void setRefund_num(Integer refund_num) {
		this.refund_num = refund_num;
	}
	public String getRefund_remark() {
		return refund_remark;
	}
	public void setRefund_remark(String refund_remark) {
		this.refund_remark = refund_remark;
	}
	public BigDecimal getShould_liquidation_money() {
		return should_liquidation_money;
	}
	public void setShould_liquidation_money(BigDecimal should_liquidation_money) {
		this.should_liquidation_money = should_liquidation_money;
	}
	public String getRefund_voucher() {
		return refund_voucher;
	}
	public void setRefund_voucher(String refund_voucher) {
		this.refund_voucher = refund_voucher;
	}
	public Integer getRefund_addr_id() {
		return refund_addr_id;
	}
	public void setRefund_addr_id(Integer refund_addr_id) {
		this.refund_addr_id = refund_addr_id;
	}
	public String getRefund_name() {
		return refund_name;
	}
	public void setRefund_name(String refund_name) {
		this.refund_name = refund_name;
	}
	public String getRefund_mobile() {
		return refund_mobile;
	}
	public void setRefund_mobile(String refund_mobile) {
		this.refund_mobile = refund_mobile;
	}
	public String getRefund_addr_detail() {
		return refund_addr_detail;
	}
	public void setRefund_addr_detail(String refund_addr_detail) {
		this.refund_addr_detail = refund_addr_detail;
	}
	public String getTrans_com() {
		return trans_com;
	}
	public void setTrans_com(String trans_com) {
		this.trans_com = trans_com;
	}
	public String getTrans_id() {
		return trans_id;
	}
	public void setTrans_id(String trans_id) {
		this.trans_id = trans_id;
	}
	public String getRefund_status() {
		return refund_status;
	}
	public void setRefund_status(String refund_status) {
		this.refund_status = refund_status;
	}
	public Date getApply_time() {
		return apply_time;
	}
	public void setApply_time(Date apply_time) {
		this.apply_time = apply_time;
	}
	public String getKefu_remark() {
		return kefu_remark;
	}
	public void setKefu_remark(String kefu_remark) {
		this.kefu_remark = kefu_remark;
	}
	public Date getKefu_time() {
		return kefu_time;
	}
	public void setKefu_time(Date kefu_time) {
		this.kefu_time = kefu_time;
	}
	public Date getSupplier_time() {
		return supplier_time;
	}
	public void setSupplier_time(Date supplier_time) {
		this.supplier_time = supplier_time;
	}
	public Date getCheck_time() {
		return check_time;
	}
	public void setCheck_time(Date check_time) {
		this.check_time = check_time;
	}
	public String getPackage_id() {
		return package_id;
	}
	public void setPackage_id(String package_id) {
		this.package_id = package_id;
	}
	
}
