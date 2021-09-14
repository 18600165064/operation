package com.data.display.model.platform;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.util.Date;

/**
 * 归属关系
 */
public class YMAscription extends ParentComment implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String spuid;
    private String skuid;
    private Integer user_id;
    private Integer uperid;
    private Date create_time;
    private Integer status;
    private Date update_time;

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

    public String getSkuid() {
        return skuid;
    }

    public void setSkuid(String skuid) {
        this.skuid = skuid;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getUperid() {
        return uperid;
    }

    public void setUperid(Integer uperid) {
        this.uperid = uperid;
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
