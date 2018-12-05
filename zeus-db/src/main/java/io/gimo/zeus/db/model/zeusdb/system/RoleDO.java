package io.gimo.zeus.db.model.zeusdb.system;

import io.gimo.zeus.db.model.zeusdb.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色表 数据库对象
 * [table]:sys_role
 * @author gimoxie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleDO extends BaseDO {

    /** 角色名称 **/
    private String name;
    /** 角色类型 **/
    private String type;
    /** 角色描述 **/
    private String description;
}
