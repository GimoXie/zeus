package io.gimo.zeus.db.dao.zeusdb;

import io.gimo.zeus.db.plugin.interceptor.Page;
import io.gimo.zeus.entity._do.zeusdb.SysRoleDO;

import java.util.List;

/**
 * SysRoleDAO扩展
 */
public interface SysRoleExtDAO extends SysRoleDAO {

    /**
     * 分页查询角色信息
     * @param page
     * @param roleDO
     */
    List<SysRoleDO> listRole(Page<SysRoleDO> page, SysRoleDO roleDO);
}