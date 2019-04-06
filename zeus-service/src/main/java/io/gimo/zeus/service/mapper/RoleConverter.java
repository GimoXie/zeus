package io.gimo.zeus.service.mapper;

import io.gimo.zeus.entity.dto.ListRoleDTO;
import io.gimo.zeus.entity.dto.SaveRoleDTO;
import io.gimo.zeus.entity.dto.UpdateRoleDTO;
import io.gimo.zeus.entity.model.zeusdb.SysRoleDO;
import io.gimo.zeus.entity.dto.RoleDTO;
import io.gimo.zeus.entity.vo.RoleVO;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by gimo on 2019/1/8.
 */
@Component
public class RoleConverter {

    @Service
    public class ListRoleMapper extends AbstractMapper<ListRoleDTO, SysRoleDO> {

    }

    @Service
    public class SaveRoleMapper extends AbstractMapper<SaveRoleDTO, SysRoleDO> {

    }

    @Service
    public class UpdateRoleMapper extends AbstractMapper<UpdateRoleDTO, SysRoleDO> {

    }

    @Service
    public class RoleMapper extends AbstractMapper<RoleDTO, SysRoleDO> {

    }

    @Service
    public class RoleViewMapper extends AbstractMapper<RoleDTO, RoleVO> {

    }

}
