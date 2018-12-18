package io.gimo.zeus.web.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * Created by zmxie on 2018/12/18.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserVO extends BaseVO {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    private String email;
    private String telephone;
    private LocalDateTime lastLoginTime;
}
