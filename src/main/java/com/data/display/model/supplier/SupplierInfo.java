package com.data.display.model.supplier;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.util.Date;

public class SupplierInfo extends ParentComment implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * 供应商ID
	 */
	private Integer s_id;
	/**
	 * 用户id
	 */
	private Integer jd_user_id;
	/**
	 * 账户类型： 0：对公，1：对私
	 */
	private Integer public_or_private;
	/**
	 * 供应商名称
	 */
	private String s_name;
	/**
	 * 城市名称
	 */
	private String city;
	/**
	 * 国家
	 */
	private String country;
	/**
	 * 省份
	 */
	private String province;
	/**
	 * '邮政编码
	 */
	private String postal_code;
	/**
	 *注册地址 
	 */
	private String registered_address;
	/**
	 * 供应商账户组
	 */
	private String s_account_group;
	/**
	 * 纳税人类型
	 */
	private String taxpayer_type;
	/**
	 * 增值税号
	 */
	private String val_added_tax_no;
	/**
	 * 联系人名称
	 */
	private String s_manager;
	/**
	 * 经营地址
	 */
	private String business_address;
	/**
	 * 电子邮箱
	 */
	private String email;
	/**
	 * 联系人办公电话
	 */
	private String office_phone;
	/**
	 * 联系人手机
	 */
	private String s_phone;
	/**
	 * 传真
	 */
	private String fax;
	/**
	 * 币种
	 */
	private String currency;
	/**
	 * 银行代码
	 */
	private String bank_code;
	/**
	 *银行名称 
	 */
	private String bank_name;
	/**
	 * 银行账户号码
	 */
	private String  bank_account_no;
	/**
	 * 账户持有人
	 */
	private String account_holder;
	/**
	 * 公司
	 */
	private String company;
	/**
	 * 创建时间
	 */
	private Date create_time;
	/**
	 * 账户提现状态（0正常，1锁定）
	 */
	private Integer status;
	public Integer getS_id() {
		return s_id;
	}
	public void setS_id(Integer s_id) {
		this.s_id = s_id;
	}
	public Integer getJd_user_id() {
		return jd_user_id;
	}
	public void setJd_user_id(Integer jd_user_id) {
		this.jd_user_id = jd_user_id;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getPostal_code() {
		return postal_code;
	}
	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}
	public String getRegistered_address() {
		return registered_address;
	}
	public void setRegistered_address(String registered_address) {
		this.registered_address = registered_address;
	}
	public String getS_account_group() {
		return s_account_group;
	}
	public void setS_account_group(String s_account_group) {
		this.s_account_group = s_account_group;
	}
	public String getTaxpayer_type() {
		return taxpayer_type;
	}
	public void setTaxpayer_type(String taxpayer_type) {
		this.taxpayer_type = taxpayer_type;
	}
	public String getVal_added_tax_no() {
		return val_added_tax_no;
	}
	public void setVal_added_tax_no(String val_added_tax_no) {
		this.val_added_tax_no = val_added_tax_no;
	}
	public String getS_manager() {
		return s_manager;
	}
	public void setS_manager(String s_manager) {
		this.s_manager = s_manager;
	}
	public String getBusiness_address() {
		return business_address;
	}
	public void setBusiness_address(String business_address) {
		this.business_address = business_address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOffice_phone() {
		return office_phone;
	}
	public void setOffice_phone(String office_phone) {
		this.office_phone = office_phone;
	}
	public String getS_phone() {
		return s_phone;
	}
	public void setS_phone(String s_phone) {
		this.s_phone = s_phone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
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
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
