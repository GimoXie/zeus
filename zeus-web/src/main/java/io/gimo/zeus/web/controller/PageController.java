package io.gimo.zeus.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/login")
    public String toLogin() {
        return "/view/login";
    }
}
