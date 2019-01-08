package io.gimo.zeus.db.dao.zeusdb;


import io.gimo.zeus.db.dao.BaseDAO;
import io.gimo.zeus.entity._do.zeusdb.SysPermissionDO;
import io.gimo.zeus.entity._do.zeusdb.SysPermissionExample;
import org.springframework.stereotype.Repository;

/**
 * SysPermissionMapper继承基类
 */
@Repository
public interface SysPermissionDAO extends BaseDAO<SysPermissionDO, Long, SysPermissionExample> {
}