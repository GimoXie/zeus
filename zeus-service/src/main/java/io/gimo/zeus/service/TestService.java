package io.gimo.zeus.service;

import io.gimo.zeus.db.db1.dao.Test1Mapper;
import io.gimo.zeus.db.db1.entity.User;
import io.gimo.zeus.db.db2.dao.Test2Mapper;
import io.gimo.zeus.db.db2.entity.User2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private Test1Mapper testMapper;

    @Autowired
    private Test2Mapper test2Mapper;

    public List<User> testDataSource1() {
        return testMapper.test();
    }

    public List<User2> testDataSource2() {
        return test2Mapper.test2();
    }
}
