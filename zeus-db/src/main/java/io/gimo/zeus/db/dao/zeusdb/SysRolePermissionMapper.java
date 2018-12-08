package io.gimo.zeus.db.dao.zeusdb;

import io.gimo.zeus.db.dao.BaseMapper;
import io.gimo.zeus.db.model.zeusdb.SysRolePermissionDO;
import io.gimo.zeus.db.model.zeusdb.SysRolePermissionExample;
import org.springframework.stereotype.Repository;

/**
 * SysRolePermissionMapper继承基类
 */
@Repository
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermissionDO, Long, SysRolePermissionExample> {
}