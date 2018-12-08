package io.gimo.zeus.db.dao.zeusdb;

import io.gimo.zeus.db.dao.BaseMapper;
import io.gimo.zeus.db.model.zeusdb.SysPermissionOperationDO;
import io.gimo.zeus.db.model.zeusdb.SysPermissionOperationExample;
import org.springframework.stereotype.Repository;

/**
 * SysPermissionOperationMapper继承基类
 */
@Repository
public interface SysPermissionOperationMapper extends BaseMapper<SysPermissionOperationDO, Long, SysPermissionOperationExample> {
}