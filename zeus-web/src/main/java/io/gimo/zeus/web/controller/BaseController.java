package io.gimo.zeus.web.controller;

import com.google.common.collect.Maps;
import io.gimo.zeus.entity.dto.PermissionDTO;
import io.gimo.zeus.entity.dto.RoleDTO;
import io.gimo.zeus.entity.dto.UserDTO;
import io.gimo.zeus.service.security.ZeusUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Map;

abstract class BaseController {
    Logger logger = LoggerFactory.getLogger(getClass());

    private static final int CODE_SUCCESS = 1;
    private static final int CODE_FAILURE = 0;

    static Map<String, Object> success() {
        return result(CODE_SUCCESS, "success", null);
    }

    static Map<String, Object> success(Object object) {
        return result(CODE_SUCCESS, "success", object);
    }

    static Map<String, Object> failure(String message) {
        return result(CODE_FAILURE, message, null);
    }

    private static Map<String, Object> result(Integer code, String message, Object object) {
        Map<String, Object> result = Maps.newHashMap();
        result.put("code", code);
        result.put("message", message);
        result.put("data", object);
        return result;
    }

    public static UserDTO getCurrentUser() {
        return getCurrentZeusUser().getUser();
    }

    public static List<RoleDTO> listCurrentUserRole() {
        return getCurrentZeusUser().getRoleList();
    }

    public static List<PermissionDTO> listCurrentUserPermission() {
        return getCurrentZeusUser().getPermissionList();
    }

    private static ZeusUser getCurrentZeusUser() {
        return (ZeusUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
