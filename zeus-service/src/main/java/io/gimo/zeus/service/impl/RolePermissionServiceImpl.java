package io.gimo.zeus.service.impl;

import io.gimo.zeus.db.dao.zeusdb.SysRolePermissionDAO;
import io.gimo.zeus.entity.dto.SaveRolePermissionDTO;
import io.gimo.zeus.entity.model.zeusdb.SysRolePermissionDO;
import io.gimo.zeus.entity.model.zeusdb.SysRolePermissionExample;
import io.gimo.zeus.entity.dto.RolePermissionDTO;
import io.gimo.zeus.service.RolePermissionService;
import io.gimo.zeus.service.mapper.RolePermissionConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    private SysRolePermissionDAO sysRolePermissionDAO;
    private RolePermissionConverter.RolePermissionMapper rolePermissionMapper;

    @Override
    public List<RolePermissionDTO> listRolePermission(Long roleId) {
        SysRolePermissionExample example = new SysRolePermissionExample();
        example.createCriteria().andActiveEqualTo(true).andRoleIdEqualTo(roleId);
        return sysRolePermissionDAO.selectByExample(example).stream().map(rolePermissionMapper.reconvert).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRolePermission(SaveRolePermissionDTO param) {
        List<Long> preservedIdList = param.getPermissionIdList();
        // step1:根据roleId查询数据库中的permission信息
        SysRolePermissionExample example = new SysRolePermissionExample();
        example.createCriteria().andRoleIdEqualTo(param.getRoleId());
        List<SysRolePermissionDO> sysRolePermissionList = sysRolePermissionDAO.selectByExample(example);
        List<SysRolePermissionDO> updateData = sysRolePermissionList.stream()
                .filter(rolePermission -> !rolePermission.getActive())
                .filter(rolePermission -> preservedIdList.contains(rolePermission.getPermissionId()))
                .collect(Collectors.toList());
        List<SysRolePermissionDO> deleteData =  sysRolePermissionList.stream()
                .filter(SysRolePermissionDO::getActive)
                .filter(rolePermission -> !preservedIdList.contains(rolePermission.getPermissionId()))
                .collect(Collectors.toList());
        List<Long> insertData = preservedIdList.stream()
                .filter(preservedId -> sysRolePermissionList.stream().noneMatch(rolePermission -> rolePermission.getPermissionId().equals(preservedId)))
                .collect(Collectors.toList());
        // TODO: 保存三类数据。
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
