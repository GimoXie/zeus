package io.gimo.zeus.web.security;

import com.google.common.collect.Lists;
import io.gimo.zeus.common.consts.MenuConsts;
import io.gimo.zeus.entity.dto.PermissionDTO;
import io.gimo.zeus.entity.vo.MenuVO;
import io.gimo.zeus.service.mapper.PermissionConverter;
import io.gimo.zeus.service.security.ZeusUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gimo on 2018/12/7.
 */
public class ZeusAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private PermissionConverter.MenuMapper menuMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("login success...");
        ZeusUser user = (ZeusUser) authentication.getPrincipal();
        List<PermissionDTO> menuList = user.getPermissionList().stream()
                .filter(permission -> permission.getType() == 0)
                .collect(Collectors.toList());
        user.setMenuList(generateMenu(menuList));
        authenticationSuccess(request, response, authentication);
    }

    private List<MenuVO> generateMenu(List<PermissionDTO> permissionList) {
        return generateMenu(permissionList, MenuConsts.ROOT_LEVEL_ID, MenuConsts.ROOT_LEVEL);
    }

    private List<MenuVO> generateMenu(List<PermissionDTO> permissionList, Long parentId, Integer level) {
        level ++;
        List<MenuVO> menuList = Lists.newArrayList();
        for (PermissionDTO permission : permissionList) {
            if (permission.getParentId().equals(parentId)) {
                MenuVO menu = menuMapper.convert.apply(permission);
                menu.setLevel(level);
                menu.setSubmenuList(generateMenu(permissionList, permission.getId(), level));
                menuList.add(menu);
            }
        }
        level --;
        return menuList;
    }

    private void authenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        this.setDefaultTargetUrl("/index");
        super.onAuthenticationSuccess(request, response, authentication);
    }

    @Autowired
    public void setMenuMapper(PermissionConverter.MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }
}
