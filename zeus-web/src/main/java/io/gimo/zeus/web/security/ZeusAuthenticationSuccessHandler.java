package io.gimo.zeus.web.security;

import com.google.common.collect.Lists;
import io.gimo.zeus.common.consts.MenuConsts;
import io.gimo.zeus.service.OperationService;
import io.gimo.zeus.service.PermissionService;
import io.gimo.zeus.service.dto.OperationDTO;
import io.gimo.zeus.service.dto.PermissionDTO;
import io.gimo.zeus.web.mapper.MenuMapper;
import io.gimo.zeus.web.vo.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.util.CollectionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by zmxie on 2018/12/7.
 */
public class ZeusAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private PermissionService permissionService;
    private OperationService operationService;
    private MenuMapper menuMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("login success...");
        // 根据角色id加载菜单
        List<ZeusGrantedAuthority> authorityList = Lists.newArrayList();
        authentication.getAuthorities().forEach(authority -> authorityList.add((ZeusGrantedAuthority) authority));
        List<Long> roleIdList = authorityList.stream().map(ZeusGrantedAuthority::getId).collect(Collectors.toList());
        List<PermissionDTO> permissionList = permissionService.listPermissionByRoleId(roleIdList);
        List<Long> permissionIdList = permissionList.stream().map(PermissionDTO::getId).collect(Collectors.toList());
        Map<Long, List<OperationDTO>> permissionOperationMap = operationService.getPermissionOperationMapByPermissionId(permissionIdList);
        // 构建菜单树
        List<MenuVO> menuList = generateMenu(permissionList, permissionOperationMap);
        ((ZeusUser) authentication.getPrincipal()).setMenuList(menuList);
        request.getServletContext().setAttribute("menuList", menuList);
        authenticationSuccess(request, response, authentication);
    }

    private List<MenuVO> generateMenu(List<PermissionDTO> permissionList, Map<Long, List<OperationDTO>> permissionOperationMap) {
        return generateMenu(permissionList, MenuConsts.FIRST_LEVEL_ID, permissionOperationMap);
    }

    private List<MenuVO> generateMenu(List<PermissionDTO> permissionList, Long parentId, Map<Long, List<OperationDTO>> permissionOperationMap) {
        List<MenuVO> menuList = Lists.newArrayList();
        for (PermissionDTO permission : permissionList) {
            if (permission.getParentId().equals(parentId)) {
                MenuVO menu = menuMapper.convertDtoToVo.apply(permission);
                menu.setSubmenuList(generateMenu(permissionList, permission.getId(), permissionOperationMap));
                menu.setOperationCode(permissionOperationMap.get(permission.getId()).stream().map(OperationDTO::getCode).collect(Collectors.toList()));
                menuList.add(menu);
            }
        }
        return menuList;
    }



    /*private List<MenuVO> generateMenu(List<PermissionDTO> permissionList, Map<Long, List<OperationDTO>> permissionOperationMap) {
        Map<Long, List<PermissionDTO>> permissionMap = permissionList.stream().collect(Collectors.groupingBy(PermissionDTO::getParentId));
        List<MenuVO> menuList = generateMenu(permissionMap.get(MenuConsts.FIRST_LEVEL_ID), permissionMap, permissionOperationMap);
        return menuList;
    }

    private List<MenuVO> generateMenu(List<PermissionDTO> parentPermissionList, Map<Long, List<PermissionDTO>> permissionMap, Map<Long, List<OperationDTO>> permissionOperationMap) {
        List<MenuVO> menuList = Lists.newArrayList();
        parentPermissionList.forEach(permission -> {
            MenuVO menu = menuMapper.convertDtoToVo.apply(permission);
            menu.setSubmenuList(generateMenu(permissionMap.get(permission.getId()), permissionMap, permissionOperationMap));
            menu.setOperationCode(permissionOperationMap.get(permission.getId()).stream().map(OperationDTO::getCode).collect(Collectors.toList()));
            menuList.add(menu);
        });
        return menuList;
    }*/

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
    public void setMenuMapper(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }
}
