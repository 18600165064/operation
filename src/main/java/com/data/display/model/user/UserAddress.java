package com.data.display.model.user;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.util.Date;

public class UserAddress extends ParentComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;
    /**
     * 用户ID
     */
    private Integer user_id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 电话
     */
    private String phone;
    /**
     * 省份
     */
    private String provice;
    /**
     * 市
     */
    private String city;
    /**
     * 区
     */
    private String district;
    /**
     * 乡镇名称
     */
    private String town;
    /**
     * 详细地址
     */
    private String detai_addr;
    /**
     * 是否默认（0，否；1，是）
     */
    private Integer defalt;
    /**
     * 创建时间
     */
    private Date create_time;
    /**
     *省份ID
     */
    private String provice_id;
    /**
     * 市id
     */
    private String city_id;
    /**
     * 区id
     */
    private String district_id;
    /**
     * 乡镇id
     */
    private String town_id;
    /**
     * 标签id
     */
    private String tag_id;
    /**
     * 标签name
     */
    private String tag_name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvice() {
        return provice;
    }

    public void setProvice(String provice) {
        this.provice = provice;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getDetai_addr() {
        return detai_addr;
    }

    public void setDetai_addr(String detai_addr) {
        this.detai_addr = detai_addr;
    }

    public Integer getDefalt() {
        return defalt;
    }

    public void setDefalt(Integer defalt) {
        this.defalt = defalt;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getProvice_id() {
        return provice_id;
    }

    public void setProvice_id(String provice_id) {
        this.provice_id = provice_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getTown_id() {
        return town_id;
    }

    public void setTown_id(String town_id) {
        this.town_id = town_id;
    }

    public String getTag_id() {
        return tag_id;
    }

    public void setTag_id(String tag_id) {
        this.tag_id = tag_id;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }
}
