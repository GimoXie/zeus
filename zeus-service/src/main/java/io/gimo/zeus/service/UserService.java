package io.gimo.zeus.service;

import io.gimo.zeus.db.plugin.interceptor.Page;
import io.gimo.zeus.entity.dto.UserDTO;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by gimo on 2018/12/7.
 */
public interface UserService {

    /**
     * 根据用户名查找用户
     * @param username  用户名
     * @return userDTO  用户DTO
     * @throws UsernameNotFoundException 未找到或者数量大于一个时抛出
     */
    UserDTO getUserByUsername(String username) throws UsernameNotFoundException;

    /**
     * 分页查询用户
     * @param request 查询参数
     * @return Page<UserDTO> 分页查询结果
     */
    Page<UserDTO> listUserByPage(UserDTO request);

    /**
     * 新增/修改用户数据
     * @param request 变更参数
     */
    void modifyUser(UserDTO request);
}
