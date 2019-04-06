package io.gimo.zeus.web.controller;

import io.gimo.zeus.entity.dto.ListRoleDTO;
import io.gimo.zeus.entity.dto.RoleDTO;
import io.gimo.zeus.entity.dto.SaveRoleDTO;
import io.gimo.zeus.entity.dto.UpdateRoleDTO;
import io.gimo.zeus.service.RoleService;
import io.gimo.zeus.service.mapper.RoleConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/roles")
public class RoleController extends BaseController {

    private RoleService roleService;
    private RoleConverter.RoleViewMapper roleViewMapper;

    @GetMapping
    @ResponseBody
    public Map<String, Object> listRole(ListRoleDTO param) {
        return success(roleViewMapper.pageConvert.apply(roleService.listRoleByPage(param)));
    }

    @PostMapping
    @ResponseBody
    public Map<String, Object> saveRole(@RequestBody SaveRoleDTO param) {
        try {
            roleService.saveRole(param);
            return success();
        } catch (Exception e) {
            String msg = "新增角色发生异常！";
            logger.error(msg, e);
            return failure(msg);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Map<String, Object> updateRole(@PathVariable("id") Long id, @RequestBody UpdateRoleDTO param) {
        try {
            roleService.updateRole(id, param);
            return success();
        } catch (Exception e) {
            String msg = "新增角色发生异常！";
            logger.error(msg, e);
            return failure(msg);
        }
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setRoleViewMapper(RoleConverter.RoleViewMapper roleViewMapper) {
        this.roleViewMapper = roleViewMapper;
    }
}
