package com.data.display.model.commodity;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 结算运费模板
 */
public class SupplierSettle extends ParentComment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
 	 * 模板表主键id
 	 */
    private Integer sas_id;
	/**
 	 * 类型1：按件数2：按重量3：按体积 4：固定运费
 	 */
    private Integer type;
	/**
 	 * 默认每、件数，kg，m³
 	 */
    private Integer default_number;
	/**
 	 * 默认金额
 	 */
    private BigDecimal default_price;
	/**
 	 * 默认每增加 件数，kg，m³
 	 */
    private Integer increa_number;
	/**
 	 * 默认增加金额
 	 */
    private BigDecimal increa_price;
	/**
 	 * 是否包邮0:不包邮1:包邮
 	 */
    private Integer status;
	/**
 	 * 创建时间
 	 */
    private Date create_time;
	/**
 	 * 更改时间
 	 */
    private Date update_time;
	/**
 	 * 模板名称
 	 */
    private String templet_name;
	/**
	 * 固定运费
	 */
	private BigDecimal fixed_shipping;
	/**
	 * 供应商id
	 */
	private Integer s_id;
	/**
	 *特殊区域
	 */
	private List<SupplierSettleSettings> settleSettingsList;
	/**
	 *不发货地区 
	 */
	private String nodel;
	
	private String nodelName;


	public List<SupplierSettleSettings> getSettleSettingsList() {
		return settleSettingsList;
	}

	public void setSettleSettingsList(List<SupplierSettleSettings> settleSettingsList) {
		this.settleSettingsList = settleSettingsList;
	}

	public String getNodelName() {
		return nodelName;
	}
	public void setNodelName(String nodelName) {
		this.nodelName = nodelName;
	}
	public String getNodel() {
		return nodel;
	}
	public void setNodel(String nodel) {
		this.nodel = nodel;
	}
	public Integer getSas_id() {
		return sas_id;
	}
	public void setSas_id(Integer sas_id) {
		this.sas_id = sas_id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getDefault_number() {
		return default_number;
	}
	public void setDefault_number(Integer default_number) {
		this.default_number = default_number;
	}
	public BigDecimal getDefault_price() {
		return default_price;
	}
	public void setDefault_price(BigDecimal default_price) {
		this.default_price = default_price;
	}
	public Integer getIncrea_number() {
		return increa_number;
	}
	public void setIncrea_number(Integer increa_number) {
		this.increa_number = increa_number;
	}
	public BigDecimal getIncrea_price() {
		return increa_price;
	}
	public void setIncrea_price(BigDecimal increa_price) {
		this.increa_price = increa_price;
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
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public String getTemplet_name() {
		return templet_name;
	}
	public void setTemplet_name(String templet_name) {
		this.templet_name = templet_name;
	}
	public BigDecimal getFixed_shipping() {
		return fixed_shipping;
	}
	public void setFixed_shipping(BigDecimal fixed_shipping) {
		this.fixed_shipping = fixed_shipping;
	}
	public Integer getS_id() {
		return s_id;
	}
	public void setS_id(Integer s_id) {
		this.s_id = s_id;
	}

}
