package io.gimo.zeus.web.controller;

import io.gimo.zeus.entity.dto.PermissionDTO;
import io.gimo.zeus.service.PermissionService;
import io.gimo.zeus.service.mapper.PermissionConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/permissions")
public class PermissionController extends BaseController {
    private PermissionService permissionService;
    private PermissionConverter.PermissionViewMapper permissionViewMapper;

    @GetMapping
    @ResponseBody
    public Map<String, Object> listPermission(PermissionDTO request) {
        return success(permissionService.listPermission(request).stream().map(permissionViewMapper.convert).collect(Collectors.toList()));
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
