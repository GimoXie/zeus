package io.gimo.zeus.service;

import io.gimo.zeus.db.dao.db1.Test1Mapper;
import io.gimo.zeus.entity.model.db1.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private Test1Mapper testMapper;

    public List<User> testDataSource1() {
        return testMapper.test();
    }
}
