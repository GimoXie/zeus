package io.gimo.zeus.db._do.zeusdb;

import io.gimo.zeus.db._do.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 用户表 数据库对象
 * [table]:sys_user
 * @author gimoxie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserDO extends BaseDO {

    /**
     * 用户名
     **/
    private String username;
    /**
     * 密码
     **/
    private String password;
    /**
     * 邮箱
     **/
    private String email;
    /**
     * 电话号码
     **/
    private String telephone;
    /**
     * 最后登录时间
     **/
    private LocalDateTime lastLoginTime;
}
