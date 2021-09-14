package com.data.display.model.order;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 返现
 */
public class AssembleRefund extends ParentComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;
    /**
     * user_id
     */
    private Integer user_id;
    /**
     *团号
     */
    private String group_num;
    /**
     *订单编号
     */
    private String order_no;
    /**
     *退款编号
     */
    private String refund_order_no;
    /**
     *退款金额
     */
    private BigDecimal refund_price;
    /**
     *创建时间
     */
    private Date create_time;
    /**
     * 状态 1 退差价 2 退全款 3 退款失败 4超次数延时退款
     */
    private Integer status;
    /**
     * 更新时间
     */
    private Date update_time;

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

    public String getGroup_num() {
        return group_num;
    }

    public void setGroup_num(String group_num) {
        this.group_num = group_num;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getRefund_order_no() {
        return refund_order_no;
    }

    public void setRefund_order_no(String refund_order_no) {
        this.refund_order_no = refund_order_no;
    }

    public BigDecimal getRefund_price() {
        return refund_price;
    }

    public void setRefund_price(BigDecimal refund_price) {
        this.refund_price = refund_price;
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

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
