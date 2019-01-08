package io.gimo.zeus.db.dao.zeusdb;

import io.gimo.zeus.db.dao.BaseDAO;
import io.gimo.zeus.db.plugin.interceptor.Page;
import io.gimo.zeus.entity._do.zeusdb.SysUserDO;
import io.gimo.zeus.entity._do.zeusdb.SysUserExample;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SysUserMapper继承基类
 */
@Repository
public interface SysUserDAO extends BaseDAO<SysUserDO, Long, SysUserExample> {
    /**
     * 分页查询用户
     * @param page   分页参数
     * @param userDO 查询条件
     * @return       查询结果
     */
    List<SysUserDO> listUser(Page<SysUserDO> page, SysUserDO userDO);
}