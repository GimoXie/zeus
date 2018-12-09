package io.gimo.zeus.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserDTO extends BaseDTO {
    private String username;
    private String password;
    private String email;
    private String telephone;
    private LocalDateTime lastLoginTime;
}
