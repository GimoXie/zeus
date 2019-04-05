package io.gimo.zeus.entity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateUserDTO implements Serializable {
    private static final long serialVersionUID = -840617513525279914L;
    private String password;
    private String nickName;
    private String email;
    private String telephone;
}
