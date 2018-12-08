package io.gimo.zeus.service.system.impl;

import com.google.common.base.Preconditions;
import io.gimo.zeus.db.dao.zeusdb.SysRoleMapper;
import io.gimo.zeus.db.dao.zeusdb.SysUserMapper;
import io.gimo.zeus.db.dao.zeusdb.SysUserRoleMapper;
import io.gimo.zeus.db.model.zeusdb.*;
import io.gimo.zeus.service.BaseService;
import io.gimo.zeus.service.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zmxie on 2018/12/7.
 */
@Service
public class UserServiceImpl extends BaseService implements UserService {

    private SysUserMapper sysUserMapper;
    private SysUserRoleMapper sysUserRoleMapper;
    private SysRoleMapper sysRoleMapper;

    @Override
    public SysUserDO getUserByUsername(String username) throws UsernameNotFoundException{
        SysUserExample userExample = new SysUserExample();
        userExample.createCriteria()
                .andUsernameEqualTo(username)
                .andIsActiveEqualTo(true);
        List<SysUserDO> userList = sysUserMapper.selectByExample(userExample);
        if (CollectionUtils.isEmpty(userList)) {
            throw new UsernameNotFoundException(String.format("未查询到用户名为%s的用户！", username));
        } else if (userList.size() > 1) {
            throw new UsernameNotFoundException(String.format("名为%s的用户不止一个！", username));
        } else {
            return userList.get(0);
        }
    }

    @Override
    public List<SysRoleDO> listRoleByUserName(Long userId) {
        // 根据用户查询所有的角色id
        SysUserRoleExample userRoleExample = new SysUserRoleExample();
        userRoleExample.createCriteria().andUserIdEqualTo(userId).andIsActiveEqualTo(true);
        List<SysUserRoleDO> userRoleList = sysUserRoleMapper.selectByExample(userRoleExample);
        List<Long> roleIdList = userRoleList.stream().map(SysUserRoleDO::getRoleId).collect(Collectors.toList());
        // 根据id列表查询所有角色信息
        SysRoleExample roleExample = new SysRoleExample();
        roleExample.createCriteria().andIdIn(roleIdList).andIsActiveEqualTo(true);
        return sysRoleMapper.selectByExample(roleExample);
    }

    @Autowired
    public void setSysUserMapper(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    @Autowired
    public void setSysRoleMapper(SysRoleMapper sysRoleMapper) {
        this.sysRoleMapper = sysRoleMapper;
    }

    @Autowired
    public void setSysUserRoleMapper(SysUserRoleMapper sysUserRoleMapper) {
        this.sysUserRoleMapper = sysUserRoleMapper;
    }
}
