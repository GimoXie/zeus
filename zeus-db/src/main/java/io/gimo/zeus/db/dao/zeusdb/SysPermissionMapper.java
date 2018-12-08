package io.gimo.zeus.db.dao.zeusdb;

import io.gimo.zeus.db.dao.BaseMapper;
import io.gimo.zeus.db.model.zeusdb.SysPermissionDO;
import io.gimo.zeus.db.model.zeusdb.SysPermissionExample;
import org.springframework.stereotype.Repository;

/**
 * SysPermissionMapper继承基类
 */
@Repository
public interface SysPermissionMapper extends BaseMapper<SysPermissionDO, Long, SysPermissionExample> {
}