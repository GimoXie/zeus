package io.gimo.zeus.web;

import io.gimo.zeus.ZeusWebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.sql.DataSource;

@SpringBootTest(classes = ZeusWebApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class DataSourceTests {
    @Autowired
    private DataSource dataSource;
    @Test
    public void testConnection() throws Exception {
        System.out.println(this.dataSource);
    }
}