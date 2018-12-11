package io.gimo.zeus.service;

import io.gimo.zeus.service.dto.OperationDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by zmxie on 2018/12/11.
 */
public interface OperationService {

    /**
     * 根据权限id获取权限-操作映射关系
     * @param permissionIdList
     */
    Map<Long, List<OperationDTO>> getPermissionOperationMapByPermissionId(List<Long> permissionIdList);
}