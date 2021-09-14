package com.data.display.model.commodity;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品服务商
 */
public class ProServiceBill extends ParentComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Integer id;
    /**
     * 创建时间
     */
    private Date create_time;
    /**
     * 0 收入 1 支出
     */
    private Integer type;
    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 1 商品差额 2 即时返现 3 核销返现 4 服务费 5 运费 6 供货价 7 退款
     */
    private Integer status;
    /**
     * 主订单号
     */
    private String order_no;
    /**
     * 用户id
     */
    private String user_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
