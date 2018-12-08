package io.gimo.zeus.db.dao.zeusdb;

import io.gimo.zeus.db.dao.BaseMapper;
import io.gimo.zeus.db.model.zeusdb.SysOperationDO;
import io.gimo.zeus.db.model.zeusdb.SysOperationExample;
import org.springframework.stereotype.Repository;

/**
 * SysOperationMapper继承基类
 */
@Repository
public interface SysOperationMapper extends BaseMapper<SysOperationDO, Long, SysOperationExample> {
}