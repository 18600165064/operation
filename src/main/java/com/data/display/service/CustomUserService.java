package com.data.display.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.data.display.mapper.userMapper.PermissionMapper;
import com.data.display.mapper.userMapper.UserMapper;
import com.data.display.model.user.Permission;
import com.data.display.model.user.SysUser;

@Service
public class CustomUserService implements UserDetailsService { //自定义UserDetailsService 接口

    @Resource
    UserMapper userMapper;
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) {
        SysUser user = userMapper.findByUserName(username);
        if (user != null) {
            List<Permission> permissions = permissionMapper.findByAdminUserId(user.getId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList <>();
            for (Permission permission : permissions) {
                if (permission != null && permission.getName()!=null) {

                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            return new SysUser(user.getId(),user.getUsername(), user.getPassword(),user.getNick_name(),user.getStaffRelation(), user.getHead_icon(),grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
        }
    }

}