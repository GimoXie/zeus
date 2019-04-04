package io.gimo.zeus.service.impl;

import io.gimo.zeus.db.dao.zeusdb.SysPermissionDAO;
import io.gimo.zeus.db.dao.zeusdb.SysRolePermissionDAO;
import io.gimo.zeus.entity._do.zeusdb.SysPermissionDO;
import io.gimo.zeus.entity._do.zeusdb.SysPermissionExample;
import io.gimo.zeus.entity._do.zeusdb.SysRolePermissionDO;
import io.gimo.zeus.entity._do.zeusdb.SysRolePermissionExample;
import io.gimo.zeus.entity.dto.PermissionDTO;
import io.gimo.zeus.service.PermissionService;
import io.gimo.zeus.service.mapper.PermissionConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {

    private SysRolePermissionDAO sysRolePermissionDAO;
    private SysPermissionDAO sysPermissionDAO;
    private PermissionConverter.PermissionMapper permissionMapper;

    @Override
    public List<PermissionDTO> listPermissionByRoleId(List<Long> roleIdList) {
        SysRolePermissionExample rolePermissionExample = new SysRolePermissionExample();
        rolePermissionExample.createCriteria().andRoleIdIn(roleIdList).andActiveEqualTo(true);
        List<SysRolePermissionDO> rolePermissionDOList = sysRolePermissionDAO.selectByExample(rolePermissionExample);
        List<Long> permissionIdList = rolePermissionDOList.stream().map(SysRolePermissionDO::getPermissionId).collect(Collectors.toList());
        SysPermissionExample permissionExample = new SysPermissionExample();
        permissionExample.createCriteria().andIdIn(permissionIdList).andActiveEqualTo(true);
        return sysPermissionDAO.selectByExample(permissionExample).stream().map(permissionMapper.reconvert).collect(Collectors.toList());
    }

    @Override
    public List<PermissionDTO> listPermission(PermissionDTO request) {
        SysPermissionDO permissionDO = permissionMapper.convert.apply(request);
        SysPermissionExample permissionExample = new SysPermissionExample();
        permissionExample.createCriteria().andActiveEqualTo(true);
        return sysPermissionDAO.selectByExample(permissionExample).stream().map(permissionMapper.reconvert).collect(Collectors.toList());
    }

    @Autowired
    public void setSysRolePermissionDAO(SysRolePermissionDAO sysRolePermissionDAO) {
        this.sysRolePermissionDAO = sysRolePermissionDAO;
    }

    @Autowired
    public void setSysPermissionDAO(SysPermissionDAO sysPermissionDAO) {
        this.sysPermissionDAO = sysPermissionDAO;
    }

    @Autowired
    public void setPermissionMapper(PermissionConverter.PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }
}
