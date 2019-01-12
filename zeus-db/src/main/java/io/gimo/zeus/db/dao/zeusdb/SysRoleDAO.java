package io.gimo.zeus.db.dao.zeusdb;

import io.gimo.zeus.db.dao.BaseDAO;
import io.gimo.zeus.db.plugin.interceptor.Page;
import io.gimo.zeus.entity._do.zeusdb.SysRoleDO;
import io.gimo.zeus.entity._do.zeusdb.SysRoleExample;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SysRoleMapper继承基类
 */
@Repository
public interface SysRoleDAO extends BaseDAO<SysRoleDO, Long, SysRoleExample> {
    /**
     * 分页查询角色信息
     * @param page
     * @param roleDO
     */
    List<SysRoleDO> listRole(Page<SysRoleDO> page, SysRoleDO roleDO);
}