package io.gimo.zeus.service;


import io.gimo.zeus.db.plugin.interceptor.Page;
import io.gimo.zeus.entity.dto.RoleDTO;

import java.util.List;

public interface RoleService {

    /**
     * 根据用户id获取所有角色
     * @param  userId   用户id
     * @return roleList 角色列表
     */
    List<RoleDTO> listRoleByUserId(Long userId);

    /**
     * 分页查询角色信息
     * @param request
     * @return
     */
    Page<RoleDTO> listRoleByPage(RoleDTO request);

    /**
     * 修改角色信息
     * @param request 变更参数
     */
    void modifyRole(RoleDTO request);
}
