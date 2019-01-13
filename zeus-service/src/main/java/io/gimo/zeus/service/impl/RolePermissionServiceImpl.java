package io.gimo.zeus.service.impl;

import io.gimo.zeus.db.dao.zeusdb.SysRolePermissionDAO;
import io.gimo.zeus.entity._do.zeusdb.SysRolePermissionExample;
import io.gimo.zeus.entity.dto.RolePermissionDTO;
import io.gimo.zeus.service.RolePermissionService;
import io.gimo.zeus.service.mapper.RolePermissionConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    private SysRolePermissionDAO sysRolePermissionDAO;
    private RolePermissionConverter.RolePermissionMapper rolePermissionMapper;

    @Override
    public List<RolePermissionDTO> listRolePermission(Long roleId) {
        SysRolePermissionExample example = new SysRolePermissionExample();
        example.createCriteria().andIsActiveEqualTo(true).andRoleIdEqualTo(roleId);
        return sysRolePermissionDAO.selectByExample(example).stream().map(rolePermissionMapper.reconvert).collect(Collectors.toList());
    }

    @Autowired
    public void setSysRolePermissionDAO(SysRolePermissionDAO sysRolePermissionDAO) {
        this.sysRolePermissionDAO = sysRolePermissionDAO;
    }

    @Autowired
    public void setRolePermissionMapper(RolePermissionConverter.RolePermissionMapper rolePermissionMapper) {
        this.rolePermissionMapper = rolePermissionMapper;
    }
}
