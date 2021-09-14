package com.data.display.model.user;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.data.display.model.dto.ParentComment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * 功能描述:
 * @author CYN
 * @date 2019/1/15 9:52
 */
public class SysUser extends ParentComment implements UserDetails {
    private Integer id;
    private String username;
    private String password;
    private String nick_name;
    private StaffRelation staffRelation;
    private String head_icon;
    private Date create_date;
    private String phone;
    private String manager;  //归属经理
    private String directInviter; //直接邀请人
    private String chiefInspector; //归属总监


    private Collection<? extends GrantedAuthority> authorities;
    private List<SysRole> roles;

    public SysUser() {
        super();
    }

    public SysUser(Integer id, String username, String password, String nick_name,StaffRelation staffRelation,String head_icon,Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nick_name = nick_name;
        this.staffRelation = staffRelation;
        this.head_icon = head_icon;
        this.authorities = authorities;
    }


    public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }
    /**
     *   账户是否过期,过期无法验证
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指定用户是否被锁定或者解锁,锁定的用户无法进行身份验证
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否被禁用,禁用的用户不能身份验证
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

	public StaffRelation getStaffRelation() {
		return staffRelation;
	}

	public void setStaffRelation(StaffRelation staffRelation) {
		this.staffRelation = staffRelation;
	}


    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getDirectInviter() {
        return directInviter;
    }

    public void setDirectInviter(String directInviter) {
        this.directInviter = directInviter;
    }

    public String getChiefInspector() {
        return chiefInspector;
    }

    public void setChiefInspector(String chiefInspector) {
        this.chiefInspector = chiefInspector;
    }

}
