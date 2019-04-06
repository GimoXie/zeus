package io.gimo.zeus.service;


import io.gimo.zeus.entity.dto.ListPermissionDTO;
import io.gimo.zeus.entity.dto.PermissionDTO;

import java.util.List;

public interface PermissionService {

    /**
     * 根据角色id查询权限
     * @param roleIdList      角色id列表
     * @return permissionList 权限列表
     */
    List<PermissionDTO> listPermissionByRoleId(List<Long> roleIdList);

    /**
     * 查询权限列表
     * @param param
     * @return
     */
    List<PermissionDTO> listPermission(ListPermissionDTO param);
}
