package io.gimo.zeus.service;


import io.gimo.zeus.db.plugin.interceptor.Page;
import io.gimo.zeus.entity.dto.ListRoleDTO;
import io.gimo.zeus.entity.dto.RoleDTO;
import io.gimo.zeus.entity.dto.SaveRoleDTO;
import io.gimo.zeus.entity.dto.UpdateRoleDTO;

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
     * @param param 查询参数
     * @return
     */
    Page<RoleDTO> listRoleByPage(ListRoleDTO param);

    /**
     * 新增角色
     * @param param 角色信息
     */
    void saveRole(SaveRoleDTO param);

    /**
     * 修改角色信息
     * @param id    角色id
     * @param param 修改参数
     */
    void updateRole(Long id, UpdateRoleDTO param);
}
