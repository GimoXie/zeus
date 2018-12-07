package io.gimo.zeus.service.system.impl;

import io.gimo.zeus.db.model.zeusdb.SysUserDO;
import io.gimo.zeus.service.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.List;

/**
 * Created by zmxie on 2018/12/7.
 */
public class UserDetailServiceImpl implements UserDetailsService {


    private UserService userService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserDO user = userService.getUserByUsername(username);
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        return new User(user.getUsername(), user.getPassword(), authorities);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
