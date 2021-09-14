package com.data.display.model.order;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.util.Date;

public class YMGrouper extends ParentComment implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String spuid;
    private Integer user_id;
    private Integer chances_num;
    private Integer forever_valid;
    private Date valid_time;
    private Date create_time;
    private Integer status;
    private Integer assemble_number;

    private String beginDate;
    private String addr_mobile;
    private String nick_name;
    private String addr_name;
    private Integer num;
    private String nickname;
    private String addrname;


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAddrname() {
        return addrname;
    }

    public void setAddrname(String addrname) {
        this.addrname = addrname;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getAddr_mobile() {
        return addr_mobile;
    }

    public void setAddr_mobile(String addr_mobile) {
        this.addr_mobile = addr_mobile;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getAddr_name() {
        return addr_name;
    }

    public void setAddr_name(String addr_name) {
        this.addr_name = addr_name;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
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

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getChances_num() {
        return chances_num;
    }

    public void setChances_num(Integer chances_num) {
        this.chances_num = chances_num;
    }

    public Integer getForever_valid() {
        return forever_valid;
    }

    public void setForever_valid(Integer forever_valid) {
        this.forever_valid = forever_valid;
    }

    public Date getValid_time() {
        return valid_time;
    }

    public void setValid_time(Date valid_time) {
        this.valid_time = valid_time;
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

    public Integer getAssemble_number() {
        return assemble_number;
    }

    public void setAssemble_number(Integer assemble_number) {
        this.assemble_number = assemble_number;
    }
}
