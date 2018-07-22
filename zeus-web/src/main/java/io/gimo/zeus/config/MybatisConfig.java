package io.gimo.zeus.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("io.gimo.zeus.db.dao")
public class MybatisConfig {

}