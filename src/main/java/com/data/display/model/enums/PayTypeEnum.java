package com.data.display.model.enums;


/**
 * 支付方式
 */
public enum PayTypeEnum {


    /**
     * 微信APP支付
     */
    WX("wx"),

    /**
     * 微信公众平台支付
     */
    WXJS("wxjs"),

    /**
     * 钱包支付
     */
    WALLET("wallet"),

    WXSMALL("wxsmall");



    private String payType;


    PayTypeEnum(String payType) {
        this.payType = payType;
    }

    public String getPayType(){
        return this.payType;
    }


    public static PayTypeEnum payType(String payType){
        for (PayTypeEnum pay:PayTypeEnum.values()){
            if (payType.equals(pay.getPayType())){
                return pay;
            }
        }
        return null;
    }

}
