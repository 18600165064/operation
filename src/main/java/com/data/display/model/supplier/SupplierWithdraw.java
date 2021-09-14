package com.data.display.model.supplier;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SupplierWithdraw extends ParentComment implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	private Integer id;
	/**
	 * 供应商ID
	 */
	private Integer s_id;
	/**
	 * 后台登录账户ID
	 */
	private Integer back_user_id;
	/**
	 * 账户类型 0：公对公 ，1：公对私
	 */
	private Integer public_or_private;
	/**
	 * 供应商名称
	 */
	private String s_name;
	/**
	 * 联系人名称
	 */
	private String s_manager;
	/**
	 * 联系人办公电话
	 */
	private String office_phone;
	/**
	 * 币种
	 */
	private String currency;
	/**
	 * 银行代码
	 */
	private String bank_code;
	/**
	 * 银行名称
	 */
	private String bank_name;
	/**
	 * 银行账户号码
	 */
	private String bank_account_no;
	/**
	 * 账户持有人
	 */
	private String account_holder;
	/**
	 * 批次号
	 */
	private String batch_no;
	/**
	 * 批次清算金额
	 */
	private BigDecimal settle_amt_total;
	/**
	 * 确认清算金额
	 */
	private BigDecimal confirm_amt_total;
	/**
	 * 批次清算状态(0:待清算 1:已清算)
	 */
	private String settle_status;
	/**
	 * 清算时间
	 */
	private Date settle_time;
	/**
	 * 创建时间
	 */
	private Date create_time;
	/**
	 * 商品总额
	 */
	private BigDecimal total_product;
	/**
	 * 运费总额
	 */
	private BigDecimal dis_fee_total;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getS_id() {
		return s_id;
	}
	public void setS_id(Integer s_id) {
		this.s_id = s_id;
	}
	public Integer getBack_user_id() {
		return back_user_id;
	}
	public void setBack_user_id(Integer back_user_id) {
		this.back_user_id = back_user_id;
	}
	public Integer getPublic_or_private() {
		return public_or_private;
	}
	public void setPublic_or_private(Integer public_or_private) {
		this.public_or_private = public_or_private;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public String getS_manager() {
		return s_manager;
	}
	public void setS_manager(String s_manager) {
		this.s_manager = s_manager;
	}
	public String getOffice_phone() {
		return office_phone;
	}
	public void setOffice_phone(String office_phone) {
		this.office_phone = office_phone;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getBank_account_no() {
		return bank_account_no;
	}
	public void setBank_account_no(String bank_account_no) {
		this.bank_account_no = bank_account_no;
	}
	public String getAccount_holder() {
		return account_holder;
	}
	public void setAccount_holder(String account_holder) {
		this.account_holder = account_holder;
	}
	public String getBatch_no() {
		return batch_no;
	}
	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}
	public BigDecimal getSettle_amt_total() {
		return settle_amt_total;
	}
	public void setSettle_amt_total(BigDecimal settle_amt_total) {
		this.settle_amt_total = settle_amt_total;
	}
	public BigDecimal getConfirm_amt_total() {
		return confirm_amt_total;
	}
	public void setConfirm_amt_total(BigDecimal confirm_amt_total) {
		this.confirm_amt_total = confirm_amt_total;
	}
	public String getSettle_status() {
		return settle_status;
	}
	public void setSettle_status(String settle_status) {
		this.settle_status = settle_status;
	}
	public Date getSettle_time() {
		return settle_time;
	}
	public void setSettle_time(Date settle_time) {
		this.settle_time = settle_time;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public BigDecimal getTotal_product() {
		return total_product;
	}
	public void setTotal_product(BigDecimal total_product) {
		this.total_product = total_product;
	}
	public BigDecimal getDis_fee_total() {
		return dis_fee_total;
	}
	public void setDis_fee_total(BigDecimal dis_fee_total) {
		this.dis_fee_total = dis_fee_total;
	}
	
	
}
