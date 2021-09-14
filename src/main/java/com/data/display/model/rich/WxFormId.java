package com.data.display.model.rich;

/**
 * @Classname WxFormId
 * @Description TODO
 * @Date 2019/6/22 16:56
 * @Created by Administrator
 */
public class WxFormId {

    public WxFormId(String formId, Long expierTime) {
        this.formId = formId;
        this.expierTime = expierTime;
    }

    String formId ;

    Long expierTime;

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public Long getExpierTime() {
        return expierTime;
    }

    public void setExpierTime(Long expierTime) {
        this.expierTime = expierTime;
    }

}
