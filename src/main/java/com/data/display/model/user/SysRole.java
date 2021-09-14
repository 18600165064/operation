package com.data.display.model.user;

import com.data.display.model.dto.ParentComment;

/**
 *
 * 功能描述:
 * @author CYN
 * @date 2019/1/15 9:52
 */
public class SysRole extends ParentComment {

    private Integer id;
    private Integer id2;
    private String name;
    private String description;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId2() {
		return id2;
	}
	public void setId2(Integer id2) {
		this.id2 = id2;
	}
	public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    
}
