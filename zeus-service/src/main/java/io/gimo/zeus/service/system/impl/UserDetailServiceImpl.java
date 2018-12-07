package io.gimo.zeus.service.system.impl;

import io.gimo.zeus.db.model.zeusdb.system.SysUserDO;
import io.gimo.zeus.service.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by zmxie on 2018/12/7.
 */
public class UserDetailServiceImpl implements UserDetailsService {


    private UserService userService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserDO user = userService.getUserByUsername(username);
        return null;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
