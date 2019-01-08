package io.gimo.zeus.service.impl;

import io.gimo.zeus.db.dao.zeusdb.SysUserDAO;
import io.gimo.zeus.db.plugin.interceptor.Page;
import io.gimo.zeus.entity._do.zeusdb.SysUserDO;
import io.gimo.zeus.entity._do.zeusdb.SysUserExample;
import io.gimo.zeus.entity.dto.UserDTO;
import io.gimo.zeus.service.BaseService;
import io.gimo.zeus.service.UserService;
import io.gimo.zeus.service.mapper.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;


/**
 * Created by zmxie on 2018/12/7.
 */
@Service
public class UserServiceImpl extends BaseService implements UserService {

    private SysUserDAO sysUserDAO;
    private UserConverter.UserMapper userMapper;

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
            return userMapper.reconvert.apply(userList.get(0));
        }
    }

    @Override
    public Page<UserDTO> listUserByPage(UserDTO request) {
        Page<SysUserDO> page = new Page<>(request.getOffset(), request.getLimit());
        SysUserDO userDO = userMapper.convert.apply(request);
        sysUserDAO.listUser(page, userDO);
        return userMapper.pageReconvert.apply(page);
    }

    @Autowired
    public void setSysUserDAO(SysUserDAO sysUserDAO) {
        this.sysUserDAO = sysUserDAO;
    }

    @Autowired
    public void setUserMapper(UserConverter.UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
