package io.gimo.zeus.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
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

    @Bean("zeusdbMybatisProperties")
    @Primary
    @ConfigurationProperties(prefix = "mybatis.zeusdb")
    public MybatisProperties mybatisProperties() {
        return new MybatisProperties();
    }

    @Bean("zeusdbDataSource")
    @Primary
    @Override
    public DataSource dataSource() {
        return super.dataSource();
    }

    @Bean("zeusdbTxManager")
    @Primary
    @Override
    public PlatformTransactionManager transactionManager(@Qualifier("zeusdbDataSource") DataSource dataSource) {
        return super.transactionManager(dataSource);
    }

    @Bean("zeusdbSqlSessionFactory")
    @Primary
    @Override
    public SqlSessionFactory sqlSessionFactory(@Qualifier("zeusdbDataSource") DataSource dataSource,
                                               @Qualifier("zeusdbMybatisProperties") MybatisProperties mybatisProperties) throws Exception {
        return super.sqlSessionFactory(dataSource, mybatisProperties);
    }
}
