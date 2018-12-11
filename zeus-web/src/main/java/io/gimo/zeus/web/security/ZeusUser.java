package io.gimo.zeus.web.security;

import io.gimo.zeus.web.vo.MenuVO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

public class ZeusUser extends User {

    private Long id;
    private String email;
    private String telephone;
    private List<MenuVO> menuList;

    public ZeusUser(String username, String password, Collection<? extends GrantedAuthority> authorities, Long id, String email, String telephone) {
        super(username, password, authorities);
        this.id = id;
        this.email = email;
        this.telephone = telephone;
    }

    public ZeusUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, Long id, String email, String telephone) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.email = email;
        this.telephone = telephone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<MenuVO> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<MenuVO> menuList) {
        this.menuList = menuList;
    }
}
