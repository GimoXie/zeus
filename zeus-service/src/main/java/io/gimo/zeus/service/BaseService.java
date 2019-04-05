package io.gimo.zeus.service;

import io.gimo.zeus.entity.dto.PermissionDTO;
import io.gimo.zeus.entity.dto.RoleDTO;
import io.gimo.zeus.entity.dto.UserDTO;
import io.gimo.zeus.service.security.ZeusUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public abstract class BaseService {
    protected Logger logger = LoggerFactory.getLogger(getClass());

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
