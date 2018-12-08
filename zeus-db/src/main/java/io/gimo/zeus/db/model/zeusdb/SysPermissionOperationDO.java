package io.gimo.zeus.db.model.zeusdb;

import io.gimo.zeus.db.model.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 权限-操作表 数据库对象
 * [table]:sys_operation
 * @author gimoxie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysPermissionOperationDO extends BaseDO {

    /** 权限id **/
    private Long permissionId;
    /** 操作id **/
    private Long operationId;
}
