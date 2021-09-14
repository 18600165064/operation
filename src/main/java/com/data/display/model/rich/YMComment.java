package com.data.display.model.rich;

import java.io.Serializable;
import java.util.Date;

public class YMComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Integer id;
    /**
     * 评论人用户ID
     */
    private Integer user_id;
    /**
     * 评论人昵称
     */
    private String nick_name;
    /**
     * 评论人头像
     */
    private String head_icon;
    /**
     * 评论内容
     */
    private String comment;
    /**
     * 评论图片组，图片链接逗号隔开
     */
    private String images;
    /**
     * 创建时间
     */
    private Date create_time;
    /**
     * 操作更新时间
     */
    private Date update_time;
    /**
     * 编辑人用户ID
     */
    private Integer editor_id;

    /**
     * spu编号
     * @return
     */
    private String spuid;

    private String list;


    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public String getSpuid() {
        return spuid;
    }

    public void setSpuid(String spuid) {
        this.spuid = spuid;
    }

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

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getHead_icon() {
        return head_icon;
    }

    public void setHead_icon(String head_icon) {
        this.head_icon = head_icon;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
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

    public Integer getEditor_id() {
        return editor_id;
    }

    public void setEditor_id(Integer editor_id) {
        this.editor_id = editor_id;
    }
}
