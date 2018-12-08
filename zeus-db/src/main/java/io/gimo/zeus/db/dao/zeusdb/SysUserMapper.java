package io.gimo.zeus.db.dao.zeusdb;

import io.gimo.zeus.db.dao.BaseMapper;
import io.gimo.zeus.db.model.zeusdb.SysUserDO;
import io.gimo.zeus.db.model.zeusdb.SysUserExample;
import org.springframework.stereotype.Repository;

/**
 * SysUserMapper继承基类
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUserDO, Long, SysUserExample> {
}