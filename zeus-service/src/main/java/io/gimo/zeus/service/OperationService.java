package io.gimo.zeus.service;


import io.gimo.zeus.entity.dto.OperationDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by zmxie on 2018/12/11.
 */
public interface OperationService {

    /**
     * 根据权限id获取权限-操作映射关系
     * @param permissionIdList 权限id列表
     * @return permissionOperationMap 权限-操作樱映射
     */
    Map<Long, List<OperationDTO>> mappingPermissionOperationMapByPermissionId(List<Long> permissionIdList);
}
