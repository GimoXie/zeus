package io.gimo.zeus.db.po.zeusdb.system;

import io.gimo.zeus.db.po.zeusdb.BasePO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色-菜单表 数据库对象
 * [table]:sys_role_permission
 * @author gimoxie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RolePermissionPO extends BasePO {

    /** 角色id **/
    private Long roleId;
    /** 菜单id **/
    private Long permissionId;
}
