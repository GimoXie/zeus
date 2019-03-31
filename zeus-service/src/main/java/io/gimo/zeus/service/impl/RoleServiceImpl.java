package io.gimo.zeus.service.impl;

import io.gimo.zeus.db.dao.zeusdb.SysRoleDAO;
import io.gimo.zeus.db.dao.zeusdb.SysUserRoleDAO;
import io.gimo.zeus.db.plugin.interceptor.Page;
import io.gimo.zeus.entity._do.zeusdb.SysRoleDO;
import io.gimo.zeus.entity._do.zeusdb.SysRoleExample;
import io.gimo.zeus.entity._do.zeusdb.SysUserRoleDO;
import io.gimo.zeus.entity._do.zeusdb.SysUserRoleExample;
import io.gimo.zeus.entity.dto.RoleDTO;
import io.gimo.zeus.service.BaseService;
import io.gimo.zeus.service.RoleService;
import io.gimo.zeus.service.mapper.RoleConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl extends BaseService implements RoleService {

    private SysUserRoleDAO sysUserRoleDAO;
    private SysRoleDAO sysRoleDAO;
    private RoleConverter.RoleMapper roleMapper;

    @Override
    public List<RoleDTO> listRoleByUserId(Long userId) {
        // 根据用户查询所有的角色id
        SysUserRoleExample userRoleExample = new SysUserRoleExample();
        userRoleExample.createCriteria().andUserIdEqualTo(userId).andIsActiveEqualTo(true);
        List<SysUserRoleDO> userRoleList = sysUserRoleDAO.selectByExample(userRoleExample);
        List<Long> roleIdList = userRoleList.stream().map(SysUserRoleDO::getRoleId).collect(Collectors.toList());
        // 根据id列表查询所有角色信息
        SysRoleExample roleExample = new SysRoleExample();
        roleExample.createCriteria().andIdIn(roleIdList).andIsActiveEqualTo(true);
        return sysRoleDAO.selectByExample(roleExample).stream().map(roleMapper.reconvert).collect(Collectors.toList());
    }

    @Override
    public Page<RoleDTO> listRoleByPage(RoleDTO request) {
        Page<SysRoleDO> page = new Page<>(request.getOffset(), request.getLimit());
        SysRoleDO roleDO = roleMapper.convert.apply(request);
        sysRoleDAO.listRole(page, roleDO);
        return roleMapper.pageReconvert.apply(page);
    }

    @Override
    public void modifyRole(RoleDTO request) {
        SysRoleDO sysRoleDO = roleMapper.convert.apply(request);
        sysRoleDO.setCreateUserId(getCurrentUser().getId());
        sysRoleDO.setLastChangeUserId(getCurrentUser().getId());
        sysRoleDO.setIsActive(true);
        if (request.getId() == null) {
            sysRoleDAO.insertSelective(sysRoleDO);
        } else {
            sysRoleDAO.updateByPrimaryKeySelective(sysRoleDO);
        }
    }


    @Autowired
    public void setSysRoleDAO(SysRoleDAO sysRoleDAO) {
        this.sysRoleDAO = sysRoleDAO;
    }

    @Autowired
    public void setSysUserRoleDAO(SysUserRoleDAO sysUserRoleDAO) {
        this.sysUserRoleDAO = sysUserRoleDAO;
    }

    @Autowired
    public void setRoleMapper(RoleConverter.RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }
}
