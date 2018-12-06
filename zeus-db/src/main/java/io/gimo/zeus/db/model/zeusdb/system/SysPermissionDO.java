package io.gimo.zeus.db.model.zeusdb.system;

import io.gimo.zeus.db.model.zeusdb.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单表 数据库对象
 * [table]:sys_permission
 * @author gimoxie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysPermissionDO extends BaseDO {

    /** 菜单名称 **/
    private String name;
    /** 资源路径 **/
    private String url;
}
