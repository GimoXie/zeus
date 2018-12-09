package io.gimo.zeus.web.security;

import com.google.common.collect.Lists;
import io.gimo.zeus.service.PermissionService;
import io.gimo.zeus.service.dto.PermissionDTO;
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
 * Created by zmxie on 2018/12/7.
 */
public class ZeusAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private PermissionService permissionService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("login success...");
        // 根据角色id加载菜单
        List<ZeusGrantedAuthority> authorityList = Lists.newArrayList();
        authentication.getAuthorities().forEach(authority -> authorityList.add ((ZeusGrantedAuthority) authority));
        List<Long> roleIdList = authorityList.stream().map(ZeusGrantedAuthority::getId).collect(Collectors.toList());
        List<PermissionDTO> permissionDTOList = permissionService.listPermissionByRoleId(roleIdList);
        // TODO: 构建菜单树

        authenticationSuccess(request, response, authentication);
    }

    private void authenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        this.setDefaultTargetUrl("/index");
        super.onAuthenticationSuccess(request, response, authentication);
    }

    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }
}
