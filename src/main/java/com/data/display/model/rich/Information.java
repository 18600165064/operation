package com.data.display.model.rich;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.util.Date;

public class Information extends ParentComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;
    /**
     * user_id
     */
    private Integer user_id;

    private String title;

    private String sub_title;

    private String message;

    private String extral_info;

    private Integer jump_page;

    private Date create_time;

    private Integer status;

    private Date update_time;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExtral_info() {
        return extral_info;
    }

    public void setExtral_info(String extral_info) {
        this.extral_info = extral_info;
    }

    public Integer getJump_page() {
        return jump_page;
    }

    public void setJump_page(Integer jump_page) {
        this.jump_page = jump_page;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
