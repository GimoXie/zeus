package io.gimo.zeus.config;

import com.zaxxer.hikari.HikariConfig;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

public abstract class BaseDataSourceConfig extends HikariConfig {

    private final MybatisConfig mybatisConfig;

    public BaseDataSourceConfig(MybatisConfig mybatisConfig) {
        this.mybatisConfig = mybatisConfig;
    }

    protected DataSource dataSource(){
        return mybatisConfig.createDataSource(this);
    }

    protected PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    protected SqlSessionFactory sqlSessionFactory(DataSource dataSource, MybatisProperties mybatisProperties) throws Exception{
        return mybatisConfig.createSqlSessionFactoryBean(dataSource, mybatisProperties).getObject();
    }
}
