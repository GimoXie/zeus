package io.gimo.zeus.service.mapper;

import io.gimo.zeus.entity._do.zeusdb.SysRolePermissionDO;
import io.gimo.zeus.entity.dto.RolePermissionDTO;
import io.gimo.zeus.entity.vo.RolePermissionVO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class RolePermissionConverter {

    @Service
    public class RolePermissionMapper extends AbstractMapper<RolePermissionDTO, SysRolePermissionDO> {

    }

    @Service
    public class RolePermissionViewMapper extends AbstractMapper<RolePermissionDTO, RolePermissionVO> {

    }
}
