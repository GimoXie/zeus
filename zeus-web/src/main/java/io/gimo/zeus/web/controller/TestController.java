package io.gimo.zeus.web.controller;

import io.gimo.zeus.db.entity.User;
import io.gimo.zeus.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class TestController {
    @Autowired
    private TestService testService;


    @RequestMapping("/test")
    public List<User> test() {
        return testService.test();
    }
}
