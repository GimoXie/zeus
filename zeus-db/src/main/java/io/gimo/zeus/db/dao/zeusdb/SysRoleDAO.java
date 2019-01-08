package io.gimo.zeus.db.dao.zeusdb;

import io.gimo.zeus.db.dao.BaseDAO;
import io.gimo.zeus.entity._do.zeusdb.SysRoleDO;
import io.gimo.zeus.entity._do.zeusdb.SysRoleExample;
import org.springframework.stereotype.Repository;

/**
 * SysRoleMapper继承基类
 */
@Repository
public interface SysRoleDAO extends BaseDAO<SysRoleDO, Long, SysRoleExample> {
}