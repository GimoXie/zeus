package io.gimo.zeus.web.controller;

import io.gimo.zeus.db.plugin.interceptor.Page;
import io.gimo.zeus.entity.dto.UserDTO;
import io.gimo.zeus.service.UserService;
import io.gimo.zeus.service.mapper.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by gimo on 2018/12/18.
 */
@Controller
@RequestMapping("/system")
public class SystemController extends BaseController {

    private UserService userService;
    private UserConverter.UserViewMapper userViewMapper;

    @RequestMapping("/users")
    @ResponseBody
    public Map<String, Object> listUser(@RequestBody UserDTO request) {
        try {
            Page<UserDTO> result = userService.listUserByPage(request);
            return success(userViewMapper.pageConvert.apply(result));
        } catch (Exception e) {
            String msg = "查询用户数据时发生异常!";
            logger.error(msg, e.getMessage());
            return failure(msg);
        }
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserViewMapper(UserConverter.UserViewMapper userViewMapper) {
        this.userViewMapper = userViewMapper;
    }
}
