package io.gimo.zeus.web.controller;

import io.gimo.zeus.entity.dto.UserDTO;
import io.gimo.zeus.service.UserService;
import io.gimo.zeus.service.mapper.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

    private UserService userService;
    private UserConverter.UserViewMapper userViewMapper;

    @GetMapping
    @ResponseBody
    public Map<String, Object> listUser(UserDTO request) {
        try {
            return success(userViewMapper.pageConvert.apply(userService.listUserByPage(request)));
        } catch (Exception e) {
            String msg = "查询用户数据时发生异常!";
            logger.error(msg, e.getMessage());
            return failure(msg);
        }
    }

    @PostMapping("/modify")
    @ResponseBody
    public Map<String, Object> modifyUser(@RequestBody UserDTO request) {
        try {
            userService.modifyUser(request);
            return success();
        } catch (Exception e) {
            String msg = "变更用户数据时发生异常!";
            logger.error(msg, e);
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
