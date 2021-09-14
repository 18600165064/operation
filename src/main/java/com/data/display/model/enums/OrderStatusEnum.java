package com.data.display.model.enums;

/**
 * 订单状态说明
 */
public enum OrderStatusEnum {

    // dfk,dfh,dsh,ywc,ytk;(待付款,待发货,待收货,已完成)close 关闭
    /**
     * 待付款
     */
    DFK("dfk","待付款"),

    /**
     * 待发货
     */
    DFH("dfh","待发货"),

    /**
     * 待收货
     */
    DSH("dsh","待收货"),

    /**
     * 已完成
     */
    YWC("ywc","已完成"),

    /**
     * 交易关闭
     */
    CLOSE("close","已关闭"),

    /**
     * 已退款
     */
    YTK("ytk","已退款");

    private String status;
    private String desc;


    OrderStatusEnum(String status,String desc) {
        this.status = status;
        this.desc = desc;
    }

    public String getOrderStatus(){
        return this.status;
    }

    public String getOrderDesc(){
        return this.desc;
    }


}

