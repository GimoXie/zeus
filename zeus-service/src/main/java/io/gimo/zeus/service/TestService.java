package io.gimo.zeus.service;

import io.gimo.zeus.db.dao.TestMapper;
import io.gimo.zeus.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestMapper testMapper;

    public List<User> test() {
        return testMapper.test();
    }
}
