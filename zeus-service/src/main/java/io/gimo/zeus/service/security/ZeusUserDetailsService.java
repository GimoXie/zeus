package io.gimo.zeus.service.security;

import io.gimo.zeus.entity.dto.PermissionDTO;
import io.gimo.zeus.entity.dto.RoleDTO;
import io.gimo.zeus.entity.dto.UserDTO;
import io.gimo.zeus.service.PermissionService;
import io.gimo.zeus.service.RoleService;
import io.gimo.zeus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ZeusUserDetailsService implements UserDetailsService {

    private UserService userService;
    private RoleService roleService;
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = userService.getUserByUsername(username);
        List<RoleDTO> roleList = roleService.listRoleByUserId(user.getId());
        List<Long> roleIdList = roleList.stream().map(RoleDTO::getId).collect(Collectors.toList());
        List<PermissionDTO> permissionList = permissionService.listPermissionByRoleId(roleIdList);
        return new ZeusUser(user, roleList, permissionList);
    }

    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = userService.getUserByUsername(username);
        List<ZeusGrantedAuthority> authorities = roleService.listRoleByUserId(user.getId()).stream()
                .map(role -> new ZeusGrantedAuthority(role.getId(), role.getName(), role.getType()))
                .collect(Collectors.toList());
        return new ZeusUser(
                user.getUsername(),
                user.getPassword(),
                authorities,
                user.getId(),
                user.getEmail(),
                user.getTelephone()
        );
    }*/

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }
}
