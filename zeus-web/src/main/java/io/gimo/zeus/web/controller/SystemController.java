package io.gimo.zeus.web.controller;

import io.gimo.zeus.db.plugin.interceptor.Page;
import io.gimo.zeus.service.UserService;
import io.gimo.zeus.service.dto.UserDTO;
import io.gimo.zeus.service.dto.UserQueryDTO;
import io.gimo.zeus.web.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by zmxie on 2018/12/18.
 */
@Controller("/system")
public class SystemController extends BaseController{

    private UserService userService;
    private UserMapper userMapper;

    @RequestMapping("/users")
    @ResponseBody
    public Map<String, Object> listUser(@RequestBody UserQueryDTO request) {
        try {
            Page<UserDTO> userDTOList = userService.listUserByPage(request);
            return generateResult(CODE_SUCCESS, "", null); //todo
        } catch (Exception e) {
            logger.error("查询用户数据时发生异常!", e.getMessage());
            return generateResult(CODE_FAILURE, e.getMessage(), "");
        }
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
