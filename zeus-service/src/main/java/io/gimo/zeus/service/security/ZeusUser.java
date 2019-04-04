package io.gimo.zeus.service.security;

import io.gimo.zeus.entity.dto.PermissionDTO;
import io.gimo.zeus.entity.dto.RoleDTO;
import io.gimo.zeus.entity.dto.UserDTO;
import io.gimo.zeus.entity.vo.MenuVO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.stream.Collectors;

public class ZeusUser extends User {

    // 用户基本信息
    private UserDTO user;
    // 用户角色信息
    private List<RoleDTO> roleList;
    // 用户权限信息
    private List<PermissionDTO> permissionList;
    // 用户菜单
    private List<MenuVO> menuList;

    public ZeusUser(UserDTO user, List<RoleDTO> roleList, List<PermissionDTO> permissionList) {
        super(user.getUsername(),
              user.getPassword(),
              permissionList.stream().map(permission -> (GrantedAuthority) permission::getCode).collect(Collectors.toSet()));
        this.user = user;
        this.roleList = roleList;
        this.permissionList = permissionList;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<RoleDTO> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RoleDTO> roleList) {
        this.roleList = roleList;
    }

    public List<PermissionDTO> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<PermissionDTO> permissionList) {
        this.permissionList = permissionList;
    }

    public List<MenuVO> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<MenuVO> menuList) {
        this.menuList = menuList;
    }
}
