package io.gimo.zeus.db.model.zeusdb.system;

import io.gimo.zeus.db.model.zeusdb.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户-角色表 数据库对象
 * [table]:sys_user_role
 * @author gimoxie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserRoleDO extends BaseDO {

    /** 用户id **/
    private Long userId;
    /** 角色id **/
    private Long roleId;
}
