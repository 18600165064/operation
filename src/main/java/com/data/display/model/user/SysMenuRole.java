package com.data.display.model.user;

import com.data.display.model.dto.ParentComment;

/**
 *
 * 功能描述:
 * @author CYN
 * @date 2019/1/15 9:52
 */
public class SysMenuRole extends ParentComment {
	
    private Integer id;

    private Integer menu_level;

    private String menu_name;

    private String menu_url;

    private Integer menu_parent;

    private Integer role;

    private String icon;
    
    private Boolean isChecked = false;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMenu_level() {
		return menu_level;
	}

	public void setMenu_level(Integer menu_level) {
		this.menu_level = menu_level;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public String getMenu_url() {
		return menu_url;
	}

	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}

	public Integer getMenu_parent() {
		return menu_parent;
	}

	public void setMenu_parent(Integer menu_parent) {
		this.menu_parent = menu_parent;
	}

	public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

	public Boolean getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Boolean isChecked) {
		this.isChecked = isChecked;
	}
}