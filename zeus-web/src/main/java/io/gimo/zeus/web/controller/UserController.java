package io.gimo.zeus.web.controller;

import io.gimo.zeus.entity.dto.ListUserDTO;
import io.gimo.zeus.entity.dto.SaveUserDTO;
import io.gimo.zeus.entity.dto.UpdateUserDTO;
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
    public Map<String, Object> listUser(ListUserDTO param) {
        try {
            return success(userViewMapper.pageConvert.apply(userService.listUserByPage(param)));
        } catch (Exception e) {
            String msg = "查询用户数据时发生异常!";
            logger.error(msg, e.getMessage());
            return failure(msg);
        }
    }

    @PostMapping
    @ResponseBody
    public  Map<String, Object> saveUser(@RequestBody SaveUserDTO param) {
        try {
            userService.saveUser(param);
            return success();
        } catch (Exception e) {
            String msg = "新增用户数据时发生异常!";
            logger.error(msg, e);
            return failure(msg);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Map<String, Object> updateUser(@PathVariable("id") Long id, @RequestBody UpdateUserDTO param) {
        try {
            userService.updateUser(id, param);
            return success();
        } catch (Exception e) {
            String msg = "更新用户数据时发生异常!";
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
