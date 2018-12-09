package io.gimo.zeus.service.impl;

import com.google.common.collect.Lists;
import io.gimo.zeus.db._do.zeusdb.*;
import io.gimo.zeus.db.dao.zeusdb.SysRoleDAO;
import io.gimo.zeus.db.dao.zeusdb.SysUserDAO;
import io.gimo.zeus.db.dao.zeusdb.SysUserRoleDAO;
import io.gimo.zeus.service.BaseService;
import io.gimo.zeus.service.UserService;
import io.gimo.zeus.service.dto.RoleDTO;
import io.gimo.zeus.service.dto.UserDTO;
import io.gimo.zeus.service.mapper.RoleMapper;
import io.gimo.zeus.service.mapper.UserMapper;
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

    private SysUserDAO sysUserDAO;
    private UserMapper userMapper;

    @Override
    public UserDTO getUserByUsername(String username) throws UsernameNotFoundException{
        SysUserExample userExample = new SysUserExample();
        userExample.createCriteria()
                .andUsernameEqualTo(username)
                .andIsActiveEqualTo(true);
        List<SysUserDO> userList = sysUserDAO.selectByExample(userExample);
        if (CollectionUtils.isEmpty(userList)) {
            throw new UsernameNotFoundException(String.format("未查询到用户名为%s的用户！", username));
        } else if (userList.size() > 1) {
            throw new UsernameNotFoundException(String.format("名为%s的用户不止一个！", username));
        } else {
            return userMapper.convertDoToDto.apply(userList.get(0));
        }
    }

    @Autowired
    public void setSysUserDAO(SysUserDAO sysUserDAO) {
        this.sysUserDAO = sysUserDAO;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

}
