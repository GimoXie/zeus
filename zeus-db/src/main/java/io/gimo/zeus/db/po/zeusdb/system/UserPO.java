package io.gimo.zeus.db.po.zeusdb.system;

import io.gimo.zeus.db.po.zeusdb.BasePO;
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
public class UserPO extends BasePO {

    /** 用户名 **/
    private String username;
    /** 密码 **/
    private String password;
    /** 最后登录时间 **/
    private LocalDateTime lastLoginTime;
}
