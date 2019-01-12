package io.gimo.zeus.service.mapper;

import io.gimo.zeus.entity._do.zeusdb.SysRoleDO;
import io.gimo.zeus.entity.dto.RoleDTO;
import io.gimo.zeus.entity.vo.RoleVO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by gimo on 2019/1/8.
 */
@Component
public class RoleConverter {

    @Service
    public class RoleMapper extends AbstractMapper<RoleDTO, SysRoleDO> {

    }

    @Service
    public class RoleViewMapper extends AbstractMapper<RoleDTO, RoleVO> {

    }

}
