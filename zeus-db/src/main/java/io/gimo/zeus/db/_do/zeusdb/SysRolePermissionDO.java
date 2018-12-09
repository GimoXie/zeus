package io.gimo.zeus.db._do.zeusdb;

import io.gimo.zeus.db._do.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色-权限表 数据库对象
 * [table]:sys_role_permission
 * @author gimoxie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysRolePermissionDO extends BaseDO {

    /**
     * 角色id
     **/
    private Long roleId;
    /**
     * 权限id
     **/
    private Long permissionId;
}
