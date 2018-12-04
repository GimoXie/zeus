package io.gimo.zeus.db.po.zeusdb.system;

import io.gimo.zeus.db.po.zeusdb.BasePO;
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
public class PermissionResourcePO extends BasePO {

    /** 权限id */
    private Long permissionId;
    /** 资源id **/
    private Long resourceId;
}
