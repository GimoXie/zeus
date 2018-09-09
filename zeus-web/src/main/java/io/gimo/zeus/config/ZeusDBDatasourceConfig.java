package io.gimo.zeus.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource.zeusdb")
@MapperScan(basePackages = "io.gimo.zeus.db.dao.zeusdb", sqlSessionFactoryRef = "zeusdbSqlSessionFactory")
public class ZeusDBDatasourceConfig extends BaseDataSourceConfig {

    public ZeusDBDatasourceConfig(MybatisConfig mybatisConfig) {
        super(mybatisConfig);
    }

    @Bean("zeusdbDataSource")
    @Primary
    @Override
    public DataSource createDataSource() throws Exception {
        return super.createDataSource();
    }

    @Bean("zeusdbTxManager")
    @Primary
    @Override
    public PlatformTransactionManager createTxManager(@Qualifier("zeusdbDataSource") DataSource dataSource) {
        return super.createTxManager(dataSource);
    }

    @Bean("zeusdbSqlSessionFactory")
    @Primary
    @Override
    public SqlSessionFactory createSqlSessionFactory(@Qualifier("zeusdbDataSource") DataSource dataSource) throws Exception {
        return super.createSqlSessionFactory(dataSource);
    }
}
