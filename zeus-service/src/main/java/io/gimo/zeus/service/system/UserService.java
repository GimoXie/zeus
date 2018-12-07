package io.gimo.zeus.service.system;

import io.gimo.zeus.db.model.zeusdb.SysUserDO;

/**
 * Created by zmxie on 2018/12/7.
 */
public interface UserService {

    SysUserDO getUserByUsername(String username);

}
