package io.gimo.zeus.web.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * spring security获取数据库用户信息
 * Created by zmxie on 2018/12/6.
 */
public class CustomUserDetailService implements UserDetailsService {

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return null;
    }

}
