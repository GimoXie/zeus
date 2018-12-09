package io.gimo.zeus.db.dao.zeusdb;

import io.gimo.zeus.db.dao.BaseDAO;
import io.gimo.zeus.db._do.zeusdb.SysUserDO;
import io.gimo.zeus.db._do.zeusdb.SysUserExample;
import org.springframework.stereotype.Repository;

/**
 * SysUserMapper继承基类
 */
@Repository
public interface SysUserDAO extends BaseDAO<SysUserDO, Long, SysUserExample> {
}