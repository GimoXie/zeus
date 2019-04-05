package io.gimo.zeus.service.mapper;

import io.gimo.zeus.entity.model.zeusdb.SysPermissionDO;
import io.gimo.zeus.entity.dto.PermissionDTO;
import io.gimo.zeus.entity.vo.MenuVO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by gimo on 2019/1/8.
 */
@Component
public class PermissionConverter {

    @Service
    public class PermissionMapper extends AbstractMapper<PermissionDTO, SysPermissionDO> {

    }

    @Service
    public class PermissionViewMapper extends AbstractMapper<PermissionDTO, SysPermissionDO> {

    }

    @Service
    public class MenuMapper extends AbstractMapper<PermissionDTO, MenuVO> {

    }

}
