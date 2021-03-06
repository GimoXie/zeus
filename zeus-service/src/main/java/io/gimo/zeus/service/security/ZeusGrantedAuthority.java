package io.gimo.zeus.service.security;

import org.springframework.security.core.GrantedAuthority;

public class ZeusGrantedAuthority implements GrantedAuthority {

    private Long id;
    private String name;
    private String type;

    public ZeusGrantedAuthority(Long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    @Override
    public String getAuthority() {
        return this.type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
