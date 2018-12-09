package io.gimo.zeus.web.security;

import com.google.common.collect.Lists;
import io.gimo.zeus.service.UserService;
import io.gimo.zeus.service.dto.RoleDTO;
import io.gimo.zeus.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class ZeusUserDetailsService implements UserDetailsService {

    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = userService.getUserByUsername(username);
        List<RoleDTO> roleList = userService.listRoleByUserId(user.getId());
        List<SimpleGrantedAuthority> authorities = Lists.newArrayList();
        roleList.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getType())));
        return new ZeusUser(
                user.getUsername(),
                user.getPassword(),
                authorities,
                user.getId(),
                user.getEmail(),
                user.getTelephone()
        );
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
