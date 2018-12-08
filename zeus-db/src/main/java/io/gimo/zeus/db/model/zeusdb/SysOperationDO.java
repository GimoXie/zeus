package io.gimo.zeus.db.model.zeusdb;

import io.gimo.zeus.db.model.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 操作表 数据库对象
 * [table]:sys_operation
 * @author gimoxie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysOperationDO extends BaseDO {

    /** 操作名称 **/
    private String name;
    /** 操作编码 **/
    private String code;
}
