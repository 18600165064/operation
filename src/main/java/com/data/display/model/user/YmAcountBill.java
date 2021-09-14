package com.data.display.model.user;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.util.Date;

public class YmAcountBill extends ParentComment implements Serializable {

    private static final long serialVersionUID = 1L;

    private String order_no;

    private Date update_time;

    private Date settle_time;

    private Integer status;


    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Date getSettle_time() {
        return settle_time;
    }

    public void setSettle_time(Date settle_time) {
        this.settle_time = settle_time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
