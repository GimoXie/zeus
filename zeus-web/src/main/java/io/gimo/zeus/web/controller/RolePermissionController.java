package io.gimo.zeus.web.controller;

import io.gimo.zeus.entity.dto.SaveRolePermissionDTO;
import io.gimo.zeus.service.RolePermissionService;
import io.gimo.zeus.service.mapper.RolePermissionConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/rolePermissions")
public class RolePermissionController extends BaseController {

    private RolePermissionService rolePermissionService;
    private RolePermissionConverter.RolePermissionViewMapper rolePermissionViewMapper;

    @GetMapping
    @ResponseBody
    public Map<String, Object> listRolePermission(Long roleId) {
        return success(rolePermissionService.listRolePermission(roleId).stream().map(rolePermissionViewMapper.convert).collect(Collectors.toList()));
    }


    @PostMapping
    @ResponseBody
    public Map<String, Object> saveRolePermission(@RequestBody SaveRolePermissionDTO param) {
        try {
            rolePermissionService.saveRolePermission(param);
            return success();
        } catch (Exception e) {
            String msg = "保存角色-权限数据时发生异常！";
            logger.error(msg, e);
            return failure(msg);
        }
    }

    @Autowired
    public void setRolePermissionService(RolePermissionService rolePermissionService) {
        this.rolePermissionService = rolePermissionService;
    }

    @Autowired
    public void setRolePermissionViewMapper(RolePermissionConverter.RolePermissionViewMapper rolePermissionViewMapper) {
        this.rolePermissionViewMapper = rolePermissionViewMapper;
    }
}
