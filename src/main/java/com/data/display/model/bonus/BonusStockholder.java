package com.data.display.model.bonus;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 股东
 */
public class BonusStockholder extends ParentComment implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer user_id;
    private String spuid;
    private Integer members_num_on_upgrade;
    private Integer pro_sales_on_upgrade;
    private Integer total_pro_sales;
    private Date upgrade_time;
    private Date hit_target_time;
    private Date create_time;
    private Date update_time;
    private String remark;
    private Integer real_pro_sales;
    private Integer more_sales;
    private BigDecimal stock_ratio;


    public BigDecimal getStock_ratio() {
        return stock_ratio;
    }

    public void setStock_ratio(BigDecimal stock_ratio) {
        this.stock_ratio = stock_ratio;
    }

    public Integer getReal_pro_sales() {
        return real_pro_sales;
    }

    public void setReal_pro_sales(Integer real_pro_sales) {
        this.real_pro_sales = real_pro_sales;
    }

    public Integer getMore_sales() {
        return more_sales;
    }

    public void setMore_sales(Integer more_sales) {
        this.more_sales = more_sales;
    }
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

    public String getSpuid() {
        return spuid;
    }

    public void setSpuid(String spuid) {
        this.spuid = spuid;
    }

    public Integer getMembers_num_on_upgrade() {
        return members_num_on_upgrade;
    }

    public void setMembers_num_on_upgrade(Integer members_num_on_upgrade) {
        this.members_num_on_upgrade = members_num_on_upgrade;
    }

    public Integer getPro_sales_on_upgrade() {
        return pro_sales_on_upgrade;
    }

    public void setPro_sales_on_upgrade(Integer pro_sales_on_upgrade) {
        this.pro_sales_on_upgrade = pro_sales_on_upgrade;
    }

    public Integer getTotal_pro_sales() {
        return total_pro_sales;
    }

    public void setTotal_pro_sales(Integer total_pro_sales) {
        this.total_pro_sales = total_pro_sales;
    }

    public Date getUpgrade_time() {
        return upgrade_time;
    }

    public void setUpgrade_time(Date upgrade_time) {
        this.upgrade_time = upgrade_time;
    }

    public Date getHit_target_time() {
        return hit_target_time;
    }

    public void setHit_target_time(Date hit_target_time) {
        this.hit_target_time = hit_target_time;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
