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
@ConfigurationProperties(prefix = "spring.datasource.db1")
@MapperScan(basePackages = "io.gimo.zeus.db.db1", sqlSessionFactoryRef = "db1SqlSessionFactory")
public class DB1DatasourceConfig extends BaseDataSourceConfig {

    public DB1DatasourceConfig(MybatisConfig mybatisConfig) {
        super(mybatisConfig);
    }

    @Bean("db1DataSource")
    @Primary
    @Override
    public DataSource createDataSource() throws Exception {
        return super.createDataSource();
    }

    @Bean("db1TxManager")
    @Primary
    @Override
    public PlatformTransactionManager createTxManager(@Qualifier("db1DataSource") DataSource dataSource) {
        return super.createTxManager(dataSource);
    }

    @Bean("db1SqlSessionFactory")
    @Primary
    @Override
    public SqlSessionFactory createSqlSessionFactory(@Qualifier("db1DataSource") DataSource dataSource) throws Exception {
        return super.createSqlSessionFactory(dataSource);
    }
}
