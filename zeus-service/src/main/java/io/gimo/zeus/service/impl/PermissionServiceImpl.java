package io.gimo.zeus.service.impl;

import com.google.common.collect.Lists;
import io.gimo.zeus.db._do.zeusdb.SysPermissionExample;
import io.gimo.zeus.db._do.zeusdb.SysRolePermissionDO;
import io.gimo.zeus.db._do.zeusdb.SysRolePermissionExample;
import io.gimo.zeus.db.dao.zeusdb.SysPermissionDAO;
import io.gimo.zeus.db.dao.zeusdb.SysRolePermissionDAO;
import io.gimo.zeus.service.PermissionService;
import io.gimo.zeus.service.dto.PermissionDTO;
import io.gimo.zeus.service.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {

    private SysRolePermissionDAO sysRolePermissionDAO;
    private SysPermissionDAO sysPermissionDAO;
    private PermissionMapper permissionMapper;

    @Override
    public List<PermissionDTO> listPermissionByRoleId(List<Long> roleIdList) {
        SysRolePermissionExample rolePermissionExample = new SysRolePermissionExample();
        rolePermissionExample.createCriteria().andRoleIdIn(roleIdList).andIsActiveEqualTo(true);
        List<SysRolePermissionDO> rolePermissionDOList = sysRolePermissionDAO.selectByExample(rolePermissionExample);
        List<Long> permissionIdList = rolePermissionDOList.stream().map(SysRolePermissionDO::getPermissionId).collect(Collectors.toList());
        SysPermissionExample permissionExample = new SysPermissionExample();
        permissionExample.createCriteria().andIdIn(permissionIdList).andIsActiveEqualTo(true);
        List<PermissionDTO> permissionDTOList = Lists.newArrayList();
        sysPermissionDAO.selectByExample(permissionExample).forEach(permission -> permissionDTOList.add(permissionMapper.reconvert.apply(permission)));
        return permissionDTOList;
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
    public void setPermissionMapper(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }
}
