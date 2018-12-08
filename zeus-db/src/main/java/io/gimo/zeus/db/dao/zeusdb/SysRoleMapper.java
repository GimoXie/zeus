package io.gimo.zeus.db.dao.zeusdb;

import io.gimo.zeus.db.dao.BaseMapper;
import io.gimo.zeus.db.model.zeusdb.SysRoleDO;
import io.gimo.zeus.db.model.zeusdb.SysRoleExample;
import org.springframework.stereotype.Repository;

/**
 * SysRoleMapper继承基类
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRoleDO, Long, SysRoleExample> {
}