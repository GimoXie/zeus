package io.gimo.zeus.db.dao.zeusdb;

import io.gimo.zeus.db.dao.BaseDAO;
import io.gimo.zeus.entity._do.zeusdb.SysPermissionOperationDO;
import io.gimo.zeus.entity._do.zeusdb.SysPermissionOperationExample;
import org.springframework.stereotype.Repository;

/**
 * SysPermissionOperationMapper继承基类
 */
@Repository
public interface SysPermissionOperationDAO extends BaseDAO<SysPermissionOperationDO, Long, SysPermissionOperationExample> {
}