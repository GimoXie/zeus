package io.gimo.zeus.db.dao.zeusdb;

import io.gimo.zeus.db.dao.BaseMapper;
import io.gimo.zeus.db.model.zeusdb.SysUserRoleDO;
import io.gimo.zeus.db.model.zeusdb.SysUserRoleExample;
import org.springframework.stereotype.Repository;

/**
 * SysUserRoleMapper继承基类
 */
@Repository
public interface SysUserRoleMapper extends BaseMapper<SysUserRoleDO, Long, SysUserRoleExample> {
}