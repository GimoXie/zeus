package io.gimo.zeus.service;


import io.gimo.zeus.entity.dto.RoleDTO;

import java.util.List;

public interface RoleService {

    /**
     * 根据用户id获取所有角色
     * @param userId    用户id
     * @return roleList 角色列表
     */
    List<RoleDTO> listRoleByUserId(Long userId);

}
