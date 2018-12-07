package io.gimo.zeus.db.model.zeusdb;

import io.gimo.zeus.db.model.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 权限资源表 数据库对象
 * [table]:sys_permission_resource
 *
 * @author gimoxie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysPermissionResourceDO extends BaseDO {

    /** 权限id */
    private Long permissionId;
    /** 资源id **/
    private Long resourceId;
}
