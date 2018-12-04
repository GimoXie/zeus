package io.gimo.zeus.db.po.zeusdb.system;

import io.gimo.zeus.db.po.zeusdb.BasePO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 资源表 数据库对象
 * [table]:sys_resource
 * @author gimoxie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ResourcePO extends BasePO {

    /** 资源名称 **/
    private String name;
    /** 资源类型 **/
    private String type;
    /** 资源url **/
    private String url;
    /** 资源描述 **/
    private String description;
    /** 资源优先级 **/
    private int priority;
    /** 父级资源id **/
    private Long parentId;
}
