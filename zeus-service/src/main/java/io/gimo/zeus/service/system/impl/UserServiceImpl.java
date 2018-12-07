package io.gimo.zeus.service.system.impl;

import io.gimo.zeus.db.dao.zeusdb.system.SysUserMapper;
import io.gimo.zeus.db.model.zeusdb.system.SysUserDO;
import io.gimo.zeus.db.model.zeusdb.system.SysUserExample;
import io.gimo.zeus.service.BaseService;
import io.gimo.zeus.service.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by zmxie on 2018/12/7.
 */
@Service
public class UserServiceImpl extends BaseService implements UserService {

    private SysUserMapper sysUserMapper;

    public SysUserDO getUserByUsername(String username) {
        SysUserExample example = new SysUserExample();
        example.createCriteria()
                .andUsernameEqualTo(username)
                .andIsActiveEqualTo(true);
        List<SysUserDO> userList = sysUserMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    @Autowired
    public void setSysUserMapper(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }
}
