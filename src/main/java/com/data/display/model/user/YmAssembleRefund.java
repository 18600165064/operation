package com.data.display.model.user;

import com.data.display.model.dto.ParentComment;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: CYN
 * @Date: 2019/5/20 20:35
 * @Description: 拼团返现
 */
public class YmAssembleRefund extends ParentComment {
    private Integer id;

    private Integer user_id;

    private String group_num;

    private String order_no;

    private String refund_order_no;

    private BigDecimal refund_price;

    private Date create_time;

    private Integer status;

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
        this.group_num = group_num == null ? null : group_num.trim();
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no == null ? null : order_no.trim();
    }

    public String getRefund_order_no() {
        return refund_order_no;
    }

    public void setRefund_order_no(String refund_order_no) {
        this.refund_order_no = refund_order_no == null ? null : refund_order_no.trim();
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
}
