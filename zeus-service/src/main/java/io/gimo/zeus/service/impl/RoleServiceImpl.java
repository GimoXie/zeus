package io.gimo.zeus.service.impl;

import com.google.common.collect.Lists;
import io.gimo.zeus.db._do.zeusdb.SysRoleDO;
import io.gimo.zeus.db._do.zeusdb.SysRoleExample;
import io.gimo.zeus.db._do.zeusdb.SysUserRoleDO;
import io.gimo.zeus.db._do.zeusdb.SysUserRoleExample;
import io.gimo.zeus.db.dao.zeusdb.SysRoleDAO;
import io.gimo.zeus.db.dao.zeusdb.SysUserRoleDAO;
import io.gimo.zeus.service.RoleService;
import io.gimo.zeus.service.dto.RoleDTO;
import io.gimo.zeus.service.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private SysUserRoleDAO sysUserRoleDAO;
    private SysRoleDAO sysRoleDAO;
    private RoleMapper roleMapper;

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
        List<SysRoleDO> sysRoleDOList = sysRoleDAO.selectByExample(roleExample);
        List<RoleDTO> roleDTOList = Lists.newArrayList();
        sysRoleDOList.forEach(roleDO -> roleDTOList.add(roleMapper.reconvert.apply(roleDO)));
        return roleDTOList;
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
    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }
}
