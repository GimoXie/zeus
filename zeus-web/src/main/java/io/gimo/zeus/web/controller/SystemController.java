package io.gimo.zeus.web.controller;

import io.gimo.zeus.db.plugin.interceptor.Page;
import io.gimo.zeus.service.UserService;
import io.gimo.zeus.service.dto.UserDTO;
import io.gimo.zeus.web.converter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by zmxie on 2018/12/18.
 */
@Controller
@RequestMapping("/system")
public class SystemController extends BaseController {

    private UserService userService;
    private UserConverter userConverter;

    @RequestMapping("/users")
    @ResponseBody
    public Map<String, Object> listUser(@RequestBody UserDTO request) {
        try {
            Page<UserDTO> result = userService.listUserByPage(request);
            return success(userConverter.convertToVo(result));
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
    public void setUserConverter(UserConverter userConverter) {
        this.userConverter = userConverter;
    }
}
