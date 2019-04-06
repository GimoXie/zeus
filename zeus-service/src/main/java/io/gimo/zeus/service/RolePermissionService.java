package io.gimo.zeus.service;

import io.gimo.zeus.entity.dto.RolePermissionDTO;
import io.gimo.zeus.entity.dto.SaveRolePermissionDTO;

import java.util.List;

public interface RolePermissionService {


    /**
     * 根据角色id查询角色-权限关联关系
     * @param roleId
     * @return
     */
    List<RolePermissionDTO> listRolePermission(Long roleId);

    /**
     * 变更角色权限信息
     * @param param
     */
    void saveRolePermission(SaveRolePermissionDTO param);
}
