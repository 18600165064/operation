package com.data.display.model.school;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.util.Date;


/**
 * 商学院栏目
 */
public class SchoolColume extends ParentComment implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String colume_name;

    private String cate_icon;

    private Date create_time;

    private Integer sort_num;

    private Integer red_hot;

    private Integer is_show;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColume_name() {
        return colume_name;
    }

    public void setColume_name(String colume_name) {
        this.colume_name = colume_name;
    }

    public String getCate_icon() {
        return cate_icon;
    }

    public void setCate_icon(String cate_icon) {
        this.cate_icon = cate_icon;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Integer getSort_num() {
        return sort_num;
    }

    public void setSort_num(Integer sort_num) {
        this.sort_num = sort_num;
    }

    public Integer getRed_hot() {
        return red_hot;
    }

    public void setRed_hot(Integer red_hot) {
        this.red_hot = red_hot;
    }

    public Integer getIs_show() {
        return is_show;
    }

    public void setIs_show(Integer is_show) {
        this.is_show = is_show;
    }
}
