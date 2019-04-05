package io.gimo.zeus.service.impl;

import io.gimo.zeus.db.dao.zeusdb.SysUserDAO;
import io.gimo.zeus.db.dao.zeusdb.SysUserExtDAO;
import io.gimo.zeus.db.plugin.interceptor.Page;
import io.gimo.zeus.entity.model.zeusdb.SysUserDO;
import io.gimo.zeus.entity.model.zeusdb.SysUserExample;
import io.gimo.zeus.entity.dto.ListUserDTO;
import io.gimo.zeus.entity.dto.SaveUserDTO;
import io.gimo.zeus.entity.dto.UpdateUserDTO;
import io.gimo.zeus.entity.dto.UserDTO;
import io.gimo.zeus.service.BaseService;
import io.gimo.zeus.service.UserService;
import io.gimo.zeus.service.mapper.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;


/**
 * Created by gimo on 2018/12/7.
 */
@Service
public class UserServiceImpl extends BaseService implements UserService {

    private SysUserDAO sysUserDAO;
    private SysUserExtDAO sysUserExtDAO;
    private UserConverter.UserMapper userMapper;
    private UserConverter.QueryUserMapper queryUserMapper;
    private UserConverter.SaveUserMapper saveUserMapper;
    private UserConverter.UpdateUserMapper updateUserMapper;

    @Override
    public UserDTO getUserByUsername(String username) throws UsernameNotFoundException{
        SysUserExample userExample = new SysUserExample();
        userExample.createCriteria()
                .andUsernameEqualTo(username)
                .andActiveEqualTo(true);
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
    public Page<UserDTO> listUserByPage(ListUserDTO request) {
        Page<SysUserDO> page = new Page<>(request.getOffset(), request.getLimit());
        SysUserDO userDO = queryUserMapper.convert.apply(request);
        sysUserExtDAO.listUser(page, userDO);
        return userMapper.pageReconvert.apply(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUser(SaveUserDTO param) {
        SysUserDO sysUserDO = saveUserMapper.convert.apply(param);
        sysUserDO.setCreateUserId(getCurrentUser().getId());
        sysUserDO.setChangeUserId(getCurrentUser().getId());
        sysUserDO.setActive(true);
        sysUserDAO.insertSelective(sysUserDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(Long id, UpdateUserDTO param) {
        SysUserDO sysUserDO = updateUserMapper.convert.apply(param);
        sysUserDO.setId(id);
        sysUserDO.setCreateUserId(getCurrentUser().getId());
        sysUserDO.setChangeUserId(getCurrentUser().getId());
        sysUserDO.setActive(true);
        sysUserDAO.updateByPrimaryKeySelective(sysUserDO);
    }

    @Autowired
    public void setSysUserDAO(SysUserDAO sysUserDAO) {
        this.sysUserDAO = sysUserDAO;
    }

    @Autowired
    public void setSysUserExtDAO(SysUserExtDAO sysUserExtDAO) {
        this.sysUserExtDAO = sysUserExtDAO;
    }

    @Autowired
    public void setUserMapper(UserConverter.UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setQueryUserMapper(UserConverter.QueryUserMapper queryUserMapper) {
        this.queryUserMapper = queryUserMapper;
    }

    @Autowired
    public void setSaveUserMapper(UserConverter.SaveUserMapper saveUserMapper) {
        this.saveUserMapper = saveUserMapper;
    }

    @Autowired
    public void setUpdateUserMapper(UserConverter.UpdateUserMapper updateUserMapper) {
        this.updateUserMapper = updateUserMapper;
    }
}
