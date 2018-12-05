package io.gimo.zeus.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by zmxie on 2018/12/5.
 */
public class UserDetailsServiceImpl implements UserDetailsService {


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return null;
    }

}
