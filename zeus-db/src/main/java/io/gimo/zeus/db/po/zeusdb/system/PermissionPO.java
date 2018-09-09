package io.gimo.zeus.db.po.zeusdb.system;

import io.gimo.zeus.db.po.zeusdb.BasePO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单表 数据库对象
 * [table]:sys_permission
 * @author gimoxie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PermissionPO extends BasePO {

    /** 菜单名称 **/
    private String name;
    /** 资源路径 **/
    private String url;
}
