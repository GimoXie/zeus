package io.gimo.zeus.web.controller;

import io.gimo.zeus.db.plugin.interceptor.Page;
import io.gimo.zeus.entity.dto.PermissionDTO;
import io.gimo.zeus.entity.dto.RoleDTO;
import io.gimo.zeus.entity.dto.UserDTO;
import io.gimo.zeus.service.PermissionService;
import io.gimo.zeus.service.RoleService;
import io.gimo.zeus.service.UserService;
import io.gimo.zeus.service.mapper.PermissionConverter;
import io.gimo.zeus.service.mapper.RoleConverter;
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
    private RoleService roleService;
    private RoleConverter.RoleViewMapper roleViewMapper;
    private PermissionService permissionService;
    private PermissionConverter.PermissionViewMapper permissionViewMapper;

    @RequestMapping("/users")
    @ResponseBody
    public Map<String, Object> listUser(@RequestBody UserDTO request) {
        try {
            return success(userViewMapper.pageConvert.apply(userService.listUserByPage(request)));
        } catch (Exception e) {
            String msg = "查询用户数据时发生异常!";
            logger.error(msg, e.getMessage());
            return failure(msg);
        }
    }

    @RequestMapping("/users/modify")
    @ResponseBody
    public Map<String, Object> modifyUser(@RequestBody UserDTO request) {
        try {
            userService.modifyUser(request);
            return success(null);
        } catch (Exception e) {
            String msg = "变更用户数据时发生异常!";
            logger.error(msg, e.getMessage());
            return failure(msg);
        }
    }

    @RequestMapping("/roles")
    @ResponseBody
    public Map<String, Object> listRole(@RequestBody RoleDTO request) {
        return success(roleViewMapper.pageConvert.apply(roleService.listRoleByPage(request)));
    }

    @RequestMapping("/permissions")
    @ResponseBody
    public Map<String, Object> listPermission(@RequestBody PermissionDTO request) {
        return success(permissionViewMapper.listConvert.apply(permissionService.listPermission(request)));
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserViewMapper(UserConverter.UserViewMapper userViewMapper) {
        this.userViewMapper = userViewMapper;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setRoleViewMapper(RoleConverter.RoleViewMapper roleViewMapper) {
        this.roleViewMapper = roleViewMapper;
    }

    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Autowired
    public void setPermissionViewMapper(PermissionConverter.PermissionViewMapper permissionViewMapper) {
        this.permissionViewMapper = permissionViewMapper;
    }
}
