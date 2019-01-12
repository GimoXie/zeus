package io.gimo.zeus.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/example")
    public String toExample() {
        return "/view/example";
    }

    @GetMapping("/login")
    public String toLogin() {
        return "/view/login";
    }

    @GetMapping({"/", "/index"})
    public String toIndex() {
        return "/view/index";
    }

    @GetMapping("/dashboard")
    public String toDashboard() {
        return "/view/dashboard";
    }

    @GetMapping("/userManage")
    public String toUserManage() {
        return "/view/system/userManage";
    }

    @GetMapping("/roleManage")
    public String toRoleManage() {
        return "/view/system/roleManage";
    }
}
