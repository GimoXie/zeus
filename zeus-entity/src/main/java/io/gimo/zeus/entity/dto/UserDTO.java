package io.gimo.zeus.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 7476428088005635883L;
    private String username;
    private String password;
    private String nickName;
    private String email;
    private String telephone;
    private LocalDateTime lastLoginTime;
}
