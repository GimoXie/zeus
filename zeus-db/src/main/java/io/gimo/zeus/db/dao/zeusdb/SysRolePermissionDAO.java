package io.gimo.zeus.db.dao.zeusdb;

import io.gimo.zeus.db.dao.BaseDAO;
import io.gimo.zeus.entity._do.zeusdb.SysRolePermissionDO;
import io.gimo.zeus.entity._do.zeusdb.SysRolePermissionExample;
import org.springframework.stereotype.Repository;

/**
 * SysRolePermissionMapper继承基类
 */
@Repository
public interface SysRolePermissionDAO extends BaseDAO<SysRolePermissionDO, Long, SysRolePermissionExample> {
}