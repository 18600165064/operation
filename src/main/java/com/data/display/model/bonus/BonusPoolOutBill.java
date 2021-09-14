package com.data.display.model.bonus;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 分红池流出明细
 */
public class BonusPoolOutBill extends ParentComment implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String spuid;
    private Integer pool_type;
    private Integer user_id;
    private BigDecimal amount;
    private BigDecimal deduct_amount;
    private BigDecimal pros_num;
    private BigDecimal even_bonus;
    private BigDecimal bonus_ratio;
    private String batch_no;
    private Integer status;
    private Date create_time;
    private Date update_time;
    private Date settle_time;
    private String remark;
    private BigDecimal more_amount;

    private String month;

    public BigDecimal getMore_amount() {
        return more_amount;
    }

    public void setMore_amount(BigDecimal more_amount) {
        this.more_amount = more_amount;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpuid() {
        return spuid;
    }

    public void setSpuid(String spuid) {
        this.spuid = spuid;
    }

    public Integer getPool_type() {
        return pool_type;
    }

    public void setPool_type(Integer pool_type) {
        this.pool_type = pool_type;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getDeduct_amount() {
        return deduct_amount;
    }

    public void setDeduct_amount(BigDecimal deduct_amount) {
        this.deduct_amount = deduct_amount;
    }

    public BigDecimal getPros_num() {
        return pros_num;
    }

    public void setPros_num(BigDecimal pros_num) {
        this.pros_num = pros_num;
    }

    public BigDecimal getEven_bonus() {
        return even_bonus;
    }

    public void setEven_bonus(BigDecimal even_bonus) {
        this.even_bonus = even_bonus;
    }

    public BigDecimal getBonus_ratio() {
        return bonus_ratio;
    }

    public void setBonus_ratio(BigDecimal bonus_ratio) {
        this.bonus_ratio = bonus_ratio;
    }

    public String getBatch_no() {
        return batch_no;
    }

    public void setBatch_no(String batch_no) {
        this.batch_no = batch_no;
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

    public Date getSettle_time() {
        return settle_time;
    }

    public void setSettle_time(Date settle_time) {
        this.settle_time = settle_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
