package io.gimo.zeus.db.dao.zeusdb;

import io.gimo.zeus.db.dao.BaseDAO;
import io.gimo.zeus.entity._do.zeusdb.SysUserRoleDO;
import io.gimo.zeus.entity._do.zeusdb.SysUserRoleExample;
import org.springframework.stereotype.Repository;

/**
 * SysUserRoleMapper继承基类
 */
@Repository
public interface SysUserRoleDAO extends BaseDAO<SysUserRoleDO, Long, SysUserRoleExample> {
}