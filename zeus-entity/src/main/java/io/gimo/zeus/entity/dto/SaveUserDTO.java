package io.gimo.zeus.entity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SaveUserDTO implements Serializable {
    private static final long serialVersionUID = -1816264840436665453L;
    private String username;
    private String password;
    private String nickName;
    private String email;
    private String telephone;
}
