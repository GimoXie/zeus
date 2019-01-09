package io.gimo.zeus.web.security;

import com.google.common.collect.Lists;
import io.gimo.zeus.common.consts.MenuConsts;
import io.gimo.zeus.entity.dto.OperationDTO;
import io.gimo.zeus.entity.dto.PermissionDTO;
import io.gimo.zeus.entity.vo.MenuVO;
import io.gimo.zeus.service.OperationService;
import io.gimo.zeus.service.PermissionService;
import io.gimo.zeus.service.mapper.PermissionConverter;
import io.gimo.zeus.service.security.ZeusGrantedAuthority;
import io.gimo.zeus.service.security.ZeusUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by gimo on 2018/12/7.
 */
public class ZeusAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private PermissionService permissionService;
    private OperationService operationService;
    private PermissionConverter.MenuMapper menuMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("login success...");
        // 根据角色id加载菜单
        List<ZeusGrantedAuthority> authorityList = Lists.newArrayList();
        authentication.getAuthorities().forEach(authority -> authorityList.add((ZeusGrantedAuthority) authority));
        List<Long> roleIdList = authorityList.stream().map(ZeusGrantedAuthority::getId).collect(Collectors.toList());
        List<PermissionDTO> permissionList = permissionService.listPermissionByRoleId(roleIdList);
        List<Long> permissionIdList = permissionList.stream().map(PermissionDTO::getId).collect(Collectors.toList());
        Map<Long, List<OperationDTO>> permissionOperationMap = operationService.mappingPermissionOperationByPermissionId(permissionIdList);
        // 构建菜单树
        List<MenuVO> menuList = generateMenu(permissionList, permissionOperationMap);
        ((ZeusUser) authentication.getPrincipal()).setMenuList(menuList);
        authenticationSuccess(request, response, authentication);
    }

    private List<MenuVO> generateMenu(List<PermissionDTO> permissionList, Map<Long, List<OperationDTO>> permissionOperationMap) {
        return generateMenu(permissionList, MenuConsts.ROOT_LEVEL_ID, permissionOperationMap, MenuConsts.ROOT_LEVEL);
    }

    private List<MenuVO> generateMenu(List<PermissionDTO> permissionList, Long parentId, Map<Long, List<OperationDTO>> permissionOperationMap, Integer level) {
        level ++;
        List<MenuVO> menuList = Lists.newArrayList();
        for (PermissionDTO permission : permissionList) {
            if (permission.getParentId().equals(parentId)) {
                MenuVO menu = menuMapper.convert.apply(permission);
                menu.setLevel(level);
                menu.setSubmenuList(generateMenu(permissionList, permission.getId(), permissionOperationMap, level));
                if (permissionOperationMap.containsKey(permission.getId())) {
                    menu.setOperationCode(permissionOperationMap.get(permission.getId()).stream().map(OperationDTO::getCode).collect(Collectors.toList()));
                }
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
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Autowired
    public void setOperationService(OperationService operationService) {
        this.operationService = operationService;
    }

    @Autowired
    public void setMenuMapper(PermissionConverter.MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }
}
