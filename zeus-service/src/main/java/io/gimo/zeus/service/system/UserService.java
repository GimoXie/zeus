package io.gimo.zeus.service.system;

import io.gimo.zeus.db.model.zeusdb.SysRoleDO;
import io.gimo.zeus.db.model.zeusdb.SysUserDO;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * Created by zmxie on 2018/12/7.
 */
public interface UserService {

    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return user    用户
     * @throws UsernameNotFoundException 未找到或者数量大于一个时抛出
     */
    SysUserDO getUserByUsername(String username) throws UsernameNotFoundException;

    /**
     * 根据用户id获取所有角色
     * @param userId  用户id
     * @return roleLit  角色列表
     */
    List<SysRoleDO> listRoleByUserName(Long userId);
}
