package com.data.display.model.platform;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.math.BigDecimal;

public class BonusParents extends ParentComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * spuid
     */
    private String spuid;
    /**
     * 合伙人名称
     */
    private String user_name;
    /**
     * 合伙人ID
     */
    private Integer user_id;
    /**
     * 累计一级总人数
     */
    private Integer one_count;
    /**
     * 累计二级总人数
     */
    private Integer second_count;
    /**
     * 当日新增一级人数
     */
    private Integer one_user_count;
    /**
     * 当日新增二级人数
     */
    private Integer second_user_count;
    /**
     * 累计总订单数
     */
    private Integer order_count;
    /**
     * 当日新增订单数
     */
    private Integer today_order_count;
    /**
     * 当日退货单数
     */
    private Integer refund_order_count;
    /**
     * 团队累计退货率
     */
    private String refund_probability;
    /**
     * 昨日分红
     */
    private BigDecimal yesterday_bonus;
    /**
     * 全部合伙人数量
     */
    private Integer bonusAllCount;
    /**
     * 当日新增合伙人数量
     */
    private Integer bonusCount;
    /**
     * 超级合伙人ID
     */
    private Integer stockholderId;
    /**
     * 超级合伙人名称
     */
    private String stockholderName;
    /**
     * 3级之后累计人数
     */
    private Integer three_user_count;

    public String getSpuid() {
        return spuid;
    }

    public void setSpuid(String spuid) {
        this.spuid = spuid;
    }

    public String getStockholderName() {
        return stockholderName;
    }

    public void setStockholderName(String stockholderName) {
        this.stockholderName = stockholderName;
    }

    public Integer getThree_user_count() {
        return three_user_count;
    }

    public void setThree_user_count(Integer three_user_count) {
        this.three_user_count = three_user_count;
    }

    public Integer getStockholderId() {
        return stockholderId;
    }

    public void setStockholderId(Integer stockholderId) {
        this.stockholderId = stockholderId;
    }

    public Integer getBonusAllCount() {
        return bonusAllCount;
    }

    public void setBonusAllCount(Integer bonusAllCount) {
        this.bonusAllCount = bonusAllCount;
    }

    public Integer getBonusCount() {
        return bonusCount;
    }

    public void setBonusCount(Integer bonusCount) {
        this.bonusCount = bonusCount;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getOne_count() {
        return one_count;
    }

    public void setOne_count(Integer one_count) {
        this.one_count = one_count;
    }

    public Integer getSecond_count() {
        return second_count;
    }

    public void setSecond_count(Integer second_count) {
        this.second_count = second_count;
    }

    public Integer getOne_user_count() {
        return one_user_count;
    }

    public void setOne_user_count(Integer one_user_count) {
        this.one_user_count = one_user_count;
    }

    public Integer getSecond_user_count() {
        return second_user_count;
    }

    public void setSecond_user_count(Integer second_user_count) {
        this.second_user_count = second_user_count;
    }

    public Integer getOrder_count() {
        return order_count;
    }

    public void setOrder_count(Integer order_count) {
        this.order_count = order_count;
    }

    public Integer getToday_order_count() {
        return today_order_count;
    }

    public void setToday_order_count(Integer today_order_count) {
        this.today_order_count = today_order_count;
    }

    public Integer getRefund_order_count() {
        return refund_order_count;
    }

    public void setRefund_order_count(Integer refund_order_count) {
        this.refund_order_count = refund_order_count;
    }

    public String getRefund_probability() {
        return refund_probability;
    }

    public void setRefund_probability(String refund_probability) {
        this.refund_probability = refund_probability;
    }

    public BigDecimal getYesterday_bonus() {
        return yesterday_bonus;
    }

    public void setYesterday_bonus(BigDecimal yesterday_bonus) {
        this.yesterday_bonus = yesterday_bonus;
    }
}
