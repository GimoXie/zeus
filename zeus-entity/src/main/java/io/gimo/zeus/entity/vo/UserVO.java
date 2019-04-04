package io.gimo.zeus.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * Created by gimo on 2018/12/18.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserVO extends BaseVO {
    private static final long serialVersionUID = 2271780463657450919L;
    private Long id;
    private String username;
    private String nickName;
    private String email;
    private String telephone;
    private LocalDateTime lastLoginTime;
    private Boolean active;
}
