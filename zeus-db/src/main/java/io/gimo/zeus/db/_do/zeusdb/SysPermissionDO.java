package io.gimo.zeus.db._do.zeusdb;

import io.gimo.zeus.db._do.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 权限表 数据库对象
 * [table]:sys_permission
 * @author: gimoxie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysPermissionDO extends BaseDO {

    /**
     * 父级id
     **/
    private Long parentId;
    /**
     * 权限名称
     **/
    private String name;
    /**
     * 图标
     **/
    private String icon;
    /**
     * url地址
     **/
    private String url;
    /**
     * 描述
     **/
    private String description;
    /**
     * 优先级
     **/
    private String priority;
}
