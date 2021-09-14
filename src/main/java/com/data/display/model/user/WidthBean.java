package com.data.display.model.user;

import com.data.display.model.dto.ParentComment;

public class WidthBean extends ParentComment {

 	private String order_no; 
    private String open_id; 
    private int total_fee; 
    private String remark;
    public String getOrder_no() {
        return order_no;
    }
    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }
    public String getOpen_id() {
        return open_id;
    }
    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }
    public int getTotal_fee() {
        return total_fee;
    }
    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
	
}
