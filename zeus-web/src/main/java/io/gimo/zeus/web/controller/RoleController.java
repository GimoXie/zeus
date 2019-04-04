package io.gimo.zeus.web.controller;

import io.gimo.zeus.entity.dto.RoleDTO;
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
    public Map<String, Object> listRole(RoleDTO request) {
        return success(roleViewMapper.pageConvert.apply(roleService.listRoleByPage(request)));
    }

    @PostMapping("/modify")
    @ResponseBody
    public Map<String, Object> modifyRole(@RequestBody RoleDTO request) {
        try {
            roleService.modifyRole(request);
            return success();
        } catch (Exception e) {
            String msg = "变更角色数据时发生异常！";
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
