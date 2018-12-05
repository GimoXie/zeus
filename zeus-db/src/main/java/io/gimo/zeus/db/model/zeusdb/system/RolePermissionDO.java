package io.gimo.zeus.db.model.zeusdb.system;

import io.gimo.zeus.db.model.zeusdb.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色-权限表 数据库对象
 * [table]:sys_role_permission
 * @author gimoxie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RolePermissionDO extends BaseDO {

    /** 角色id **/
    private Long roleId;
    /** 权限id **/
    private Long permissionId;
}
