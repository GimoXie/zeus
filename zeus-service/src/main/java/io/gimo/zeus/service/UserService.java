package io.gimo.zeus.service;

import io.gimo.zeus.service.dto.RoleDTO;
import io.gimo.zeus.service.dto.UserDTO;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * Created by zmxie on 2018/12/7.
 */
public interface UserService {

    /**
     * 根据用户名查找用户
     * @param username   用户名
     * @return userDTO  用户DTO
     * @throws UsernameNotFoundException 未找到或者数量大于一个时抛出
     */
    UserDTO getUserByUsername(String username) throws UsernameNotFoundException;

}
