package com.data.display.model.order;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Order extends ParentComment implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private Long id;

    private String order_no;

    private int if_primary;

    private String sub_order_no;

    private Integer user_id;

    private Integer share_user_id;

    private Integer originator_id;

    private Integer supplier_id;

    private BigDecimal dis_fee;

    private String pay_type;

    private Integer group_type;

    private String group_num;

    private Integer group_source;

    private BigDecimal c_amount;

    private BigDecimal commission;

    private Integer address_id;

    private Integer addr_region;

    private String addr_detail;

    private String addr_name;

    private String addr_mobile;

    private String trans_com;

    private String trans_id;

    private Integer trans_fin;

    private String remark;

    private String if_refund;

    private String after_sale_status;

    private Integer auditor;

    private String to_examine_status;

    private String order_status;

    private Byte pay_status;

    private Byte settle_status;

    private Date create_time;

    private Date pay_time;

    private Date send_time;

    private Long trans_time;

    private Date ywc_time;

    private Date update_time;
    /**
     * 0 推送 1  未推送
     */
    private Integer if_self;
    
    /**
     * 时间段---开始时间
     * @return
     */
    private Date startTime;
    /**
     * 时间段---结束时间
     * @return
     */
    private Date endTime;
    /**
     * 订单关闭原因
     * @return
     */
    private String order_close_status;
    /**
     * 商品数量
     */
    private Integer num;

    private Integer smallNum;

    private Integer bigNum;

    private Integer partener_id;

    private Integer stockerholder_id;

    private Date apply_time;

    public Date getApply_time() {
        return apply_time;
    }

    public void setApply_time(Date apply_time) {
        this.apply_time = apply_time;
    }

    public Integer getPartener_id() {
        return partener_id;
    }

    public void setPartener_id(Integer partener_id) {
        this.partener_id = partener_id;
    }

    public Integer getStockerholder_id() {
        return stockerholder_id;
    }

    public void setStockerholder_id(Integer stockerholder_id) {
        this.stockerholder_id = stockerholder_id;
    }

    public Integer getSmallNum() {
        return smallNum;
    }

    public void setSmallNum(Integer smallNum) {
        this.smallNum = smallNum;
    }

    public Integer getBigNum() {
        return bigNum;
    }

    public void setBigNum(Integer bigNum) {
        this.bigNum = bigNum;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getOrder_close_status() {
        return order_close_status;
    }

    public void setOrder_close_status(String order_close_status) {
        this.order_close_status = order_close_status;
    }

    public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getIf_self() {
		return if_self;
	}

	public void setIf_self(Integer if_self) {
		this.if_self = if_self;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no == null ? null : order_no.trim();
    }

    public int getIf_primary() {
        return if_primary;
    }

    public void setIf_primary(int if_primary) {
        this.if_primary = if_primary;
    }

    public String getSub_order_no() {
        return sub_order_no;
    }

    public void setSub_order_no(String sub_order_no) {
        this.sub_order_no = sub_order_no == null ? null : sub_order_no.trim();
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getShare_user_id() {
        return share_user_id;
    }

    public void setShare_user_id(Integer share_user_id) {
        this.share_user_id = share_user_id;
    }

    public Integer getOriginator_id() {
        return originator_id;
    }

    public void setOriginator_id(Integer originator_id) {
        this.originator_id = originator_id;
    }

    public Integer getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(Integer supplier_id) {
        this.supplier_id = supplier_id;
    }

    public BigDecimal getDis_fee() {
        return dis_fee;
    }

    public void setDis_fee(BigDecimal dis_fee) {
        this.dis_fee = dis_fee;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type == null ? null : pay_type.trim();
    }

    public Integer getGroup_type() {
        return group_type;
    }

    public void setGroup_type(Integer group_type) {
        this.group_type = group_type;
    }

    public String getGroup_num() {
		return group_num;
	}

	public void setGroup_num(String group_num) {
		this.group_num = group_num;
	}

	public Integer getGroup_source() {
        return group_source;
    }

    public void setGroup_source(Integer group_source) {
        this.group_source = group_source;
    }

    public BigDecimal getC_amount() {
        return c_amount;
    }

    public void setC_amount(BigDecimal c_amount) {
        this.c_amount = c_amount;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public Integer getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Integer address_id) {
        this.address_id = address_id;
    }

    public Integer getAddr_region() {
        return addr_region;
    }

    public void setAddr_region(Integer addr_region) {
        this.addr_region = addr_region;
    }

    public String getAddr_detail() {
        return addr_detail;
    }

    public void setAddr_detail(String addr_detail) {
        this.addr_detail = addr_detail == null ? null : addr_detail.trim();
    }

    public String getAddr_name() {
        return addr_name;
    }

    public void setAddr_name(String addr_name) {
        this.addr_name = addr_name == null ? null : addr_name.trim();
    }

    public String getAddr_mobile() {
        return addr_mobile;
    }

    public void setAddr_mobile(String addr_mobile) {
        this.addr_mobile = addr_mobile == null ? null : addr_mobile.trim();
    }

    public String getTrans_com() {
        return trans_com;
    }

    public void setTrans_com(String trans_com) {
        this.trans_com = trans_com == null ? null : trans_com.trim();
    }

    public String getTrans_id() {
        return trans_id;
    }

    public void setTrans_id(String trans_id) {
        this.trans_id = trans_id == null ? null : trans_id.trim();
    }

    public Integer getTrans_fin() {
        return trans_fin;
    }

    public void setTrans_fin(Integer trans_fin) {
        this.trans_fin = trans_fin;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getIf_refund() {
        return if_refund;
    }

    public void setIf_refund(String if_refund) {
        this.if_refund = if_refund == null ? null : if_refund.trim();
    }

    public String getAfter_sale_status() {
        return after_sale_status;
    }

    public void setAfter_sale_status(String after_sale_status) {
        this.after_sale_status = after_sale_status == null ? null : after_sale_status.trim();
    }

    public Integer getAuditor() {
        return auditor;
    }

    public void setAuditor(Integer auditor) {
        this.auditor = auditor;
    }

    public String getTo_examine_status() {
        return to_examine_status;
    }

    public void setTo_examine_status(String to_examine_status) {
        this.to_examine_status = to_examine_status == null ? null : to_examine_status.trim();
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status == null ? null : order_status.trim();
    }

    public Byte getPay_status() {
        return pay_status;
    }

    public void setPay_status(Byte pay_status) {
        this.pay_status = pay_status;
    }

    public Byte getSettle_status() {
        return settle_status;
    }

    public void setSettle_status(Byte settle_status) {
        this.settle_status = settle_status;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getPay_time() {
        return pay_time;
    }

    public void setPay_time(Date pay_time) {
        this.pay_time = pay_time;
    }

    public Date getSend_time() {
        return send_time;
    }

    public void setSend_time(Date send_time) {
        this.send_time = send_time;
    }

    public Long getTrans_time() {
        return trans_time;
    }

    public void setTrans_time(Long trans_time) {
        this.trans_time = trans_time;
    }

    public Date getYwc_time() {
        return ywc_time;
    }

    public void setYwc_time(Date ywc_time) {
        this.ywc_time = ywc_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}