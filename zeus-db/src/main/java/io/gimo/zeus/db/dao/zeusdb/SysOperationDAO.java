package io.gimo.zeus.db.dao.zeusdb;


import io.gimo.zeus.db.dao.BaseDAO;
import io.gimo.zeus.entity._do.zeusdb.SysOperationDO;
import io.gimo.zeus.entity._do.zeusdb.SysOperationExample;
import org.springframework.stereotype.Repository;

/**
 * SysOperationMapper继承基类
 */
@Repository
public interface SysOperationDAO extends BaseDAO<SysOperationDO, Long, SysOperationExample> {
}