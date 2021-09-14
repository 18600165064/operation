package com.data.display.model.school;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.util.Date;


/**
 * 商学院内容
 */
public class SchoolContent extends ParentComment implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer colume_id;

    private String title;

    private String image;

    private String spuid;

    private String video_url;

    private String content;

    private Integer browse_num;

    private Integer is_top;

    private Integer content_type;

    private Date create_time;

    private Date update_time;

    private Integer is_show;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getColume_id() {
        return colume_id;
    }

    public void setColume_id(Integer colume_id) {
        this.colume_id = colume_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSpuid() {
        return spuid;
    }

    public void setSpuid(String spuid) {
        this.spuid = spuid;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getBrowse_num() {
        return browse_num;
    }

    public void setBrowse_num(Integer browse_num) {
        this.browse_num = browse_num;
    }

    public Integer getIs_top() {
        return is_top;
    }

    public void setIs_top(Integer is_top) {
        this.is_top = is_top;
    }

    public Integer getContent_type() {
        return content_type;
    }

    public void setContent_type(Integer content_type) {
        this.content_type = content_type;
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

    public Integer getIs_show() {
        return is_show;
    }

    public void setIs_show(Integer is_show) {
        this.is_show = is_show;
    }
}
