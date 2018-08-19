package io.gimo.zeus.web.controller;

import io.gimo.zeus.db.db1.entity.User;
import io.gimo.zeus.db.db2.entity.User2;
import io.gimo.zeus.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class TestController {
    @Autowired
    private TestService testService;


    @RequestMapping("/testDataSource1")
    public List<User> testDataSource1() {
        return testService.testDataSource1();
    }

    @RequestMapping("/testDataSource2")
    public List<User2> testDataSource2() {
        return testService.testDataSource2();
    }

}
