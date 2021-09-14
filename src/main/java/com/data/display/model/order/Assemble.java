package com.data.display.model.order;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Assemble extends ParentComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Integer id;
    /**
     * 发起人
     */
    private Integer originator_id;
    /**
     * 发起人名称
     */
    private String originator_name;
    /**
     *发起人头像
     */
    private String originator_head;
    /**
     *参与者
     */
    private Integer participant_id;
    /**
     * 参与者名称
     */
    private String participant_name;
    /**
     *订单号
     */
    private String order_no;
    /**
     *团号
     */
    private String group_num;
    /**
     * SPUID
     */
    private String spuid;
    /**
     *skuid
     */
    private String skuid;
    /**
     *规模 1 2 3 5 8
     */
    private Integer scale;
    /**
     *状态(0,拼团中;1,拼团成功;2,拼团失败)
     */
    private Integer status;
    /**
     *创建时间
     */
    private Date create_time;
    /**
     *更新时间
     */
    private Date update_time;
    /**
     *开始时间
     */
    private Date start_time;
    /**
     *截止时间
     */
    private Date end_time;
    /**
     *完成时间
     */
    private Date complete_time;
    /**
     *参与人数
     */
    private Integer participants_num;
    /**
     *返现金额
     */
    private BigDecimal disparity;


    public String getOriginator_name() {
        return originator_name;
    }

    public void setOriginator_name(String originator_name) {
        this.originator_name = originator_name;
    }

    public String getParticipant_name() {
        return participant_name;
    }

    public void setParticipant_name(String participant_name) {
        this.participant_name = participant_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOriginator_id() {
        return originator_id;
    }

    public void setOriginator_id(Integer originator_id) {
        this.originator_id = originator_id;
    }

    public String getOriginator_head() {
        return originator_head;
    }

    public void setOriginator_head(String originator_head) {
        this.originator_head = originator_head;
    }

    public Integer getParticipant_id() {
        return participant_id;
    }

    public void setParticipant_id(Integer participant_id) {
        this.participant_id = participant_id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getGroup_num() {
        return group_num;
    }

    public void setGroup_num(String group_num) {
        this.group_num = group_num;
    }

    public String getSpuid() {
        return spuid;
    }

    public void setSpuid(String spuid) {
        this.spuid = spuid;
    }

    public String getSkuid() {
        return skuid;
    }

    public void setSkuid(String skuid) {
        this.skuid = skuid;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
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

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Date getComplete_time() {
        return complete_time;
    }

    public void setComplete_time(Date complete_time) {
        this.complete_time = complete_time;
    }

    public Integer getParticipants_num() {
        return participants_num;
    }

    public void setParticipants_num(Integer participants_num) {
        this.participants_num = participants_num;
    }

    public BigDecimal getDisparity() {
        return disparity;
    }

    public void setDisparity(BigDecimal disparity) {
        this.disparity = disparity;
    }
}
