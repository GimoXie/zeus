package io.gimo.zeus.service.impl;

import io.gimo.zeus.db.dao.zeusdb.SysRoleDAO;
import io.gimo.zeus.db.dao.zeusdb.SysRoleExtDAO;
import io.gimo.zeus.db.dao.zeusdb.SysUserRoleDAO;
import io.gimo.zeus.db.plugin.interceptor.Page;
import io.gimo.zeus.entity.dto.ListRoleDTO;
import io.gimo.zeus.entity.dto.SaveRoleDTO;
import io.gimo.zeus.entity.dto.UpdateRoleDTO;
import io.gimo.zeus.entity.model.zeusdb.SysRoleDO;
import io.gimo.zeus.entity.model.zeusdb.SysRoleExample;
import io.gimo.zeus.entity.model.zeusdb.SysUserRoleDO;
import io.gimo.zeus.entity.model.zeusdb.SysUserRoleExample;
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
    private SysRoleExtDAO sysRoleExtDAO;
    private RoleConverter.RoleMapper roleMapper;
    private RoleConverter.ListRoleMapper listRoleMapper;
    private RoleConverter.SaveRoleMapper saveRoleMapper;
    private RoleConverter.UpdateRoleMapper updateRoleMapper;

    @Override
    public List<RoleDTO> listRoleByUserId(Long userId) {
        // 根据用户查询所有的角色id
        SysUserRoleExample userRoleExample = new SysUserRoleExample();
        userRoleExample.createCriteria().andUserIdEqualTo(userId).andActiveEqualTo(true);
        List<SysUserRoleDO> userRoleList = sysUserRoleDAO.selectByExample(userRoleExample);
        List<Long> roleIdList = userRoleList.stream().map(SysUserRoleDO::getRoleId).collect(Collectors.toList());
        // 根据id列表查询所有角色信息
        SysRoleExample roleExample = new SysRoleExample();
        roleExample.createCriteria().andIdIn(roleIdList).andActiveEqualTo(true);
        return sysRoleDAO.selectByExample(roleExample).stream().map(roleMapper.reconvert).collect(Collectors.toList());
    }

    @Override
    public Page<RoleDTO> listRoleByPage(ListRoleDTO param) {
        Page<SysRoleDO> page = new Page<>(param.getOffset(), param.getLimit());
        SysRoleDO roleDO = listRoleMapper.convert.apply(param);
        sysRoleExtDAO.listRole(page, roleDO);
        return roleMapper.pageReconvert.apply(page);
    }

    @Override
    public void saveRole(SaveRoleDTO request) {
        SysRoleDO sysRoleDO = saveRoleMapper.convert.apply(request);
        sysRoleDO.setCreateUserId(getCurrentUser().getId());
        sysRoleDO.setChangeUserId(getCurrentUser().getId());
        sysRoleDO.setActive(true);
        sysRoleDAO.insertSelective(sysRoleDO);
    }

    @Override
    public void updateRole(Long id, UpdateRoleDTO request) {
        SysRoleDO sysRoleDO = updateRoleMapper.convert.apply(request);
        sysRoleDO.setId(id);
        sysRoleDO.setCreateUserId(getCurrentUser().getId());
        sysRoleDO.setChangeUserId(getCurrentUser().getId());
        sysRoleDO.setActive(true);
        sysRoleDAO.updateByPrimaryKeySelective(sysRoleDO);
    }

    @Autowired
    public void setSysUserRoleDAO(SysUserRoleDAO sysUserRoleDAO) {
        this.sysUserRoleDAO = sysUserRoleDAO;
    }

    @Autowired
    public void setSysRoleExtDAO(SysRoleExtDAO sysRoleExtDAO) {
        this.sysRoleExtDAO = sysRoleExtDAO;
    }

    @Autowired
    public void setSysRoleDAO(SysRoleDAO sysRoleDAO) {
        this.sysRoleDAO = sysRoleDAO;
    }

    @Autowired
    public void setRoleMapper(RoleConverter.RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Autowired
    public void setListRoleMapper(RoleConverter.ListRoleMapper listRoleMapper) {
        this.listRoleMapper = listRoleMapper;
    }

    @Autowired
    public void setSaveRoleMapper(RoleConverter.SaveRoleMapper saveRoleMapper) {
        this.saveRoleMapper = saveRoleMapper;
    }

    @Autowired
    public void setUpdateRoleMapper(RoleConverter.UpdateRoleMapper updateRoleMapper) {
        this.updateRoleMapper = updateRoleMapper;
    }
}
