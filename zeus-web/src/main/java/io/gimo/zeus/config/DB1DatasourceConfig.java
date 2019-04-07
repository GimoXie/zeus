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
@ConfigurationProperties(prefix = "spring.datasource.db1")
@MapperScan(basePackages = "io.gimo.zeus.db.dao.db1", sqlSessionFactoryRef = "db1SqlSessionFactory")
public class DB1DatasourceConfig extends BaseDataSourceConfig {

    public DB1DatasourceConfig(MybatisConfig mybatisConfig) {
        super(mybatisConfig);
    }

    @Bean("db1MybatisProperties")
    @ConfigurationProperties(prefix = "mybatis.db1")
    public MybatisProperties mybatisProperties() {
        return new MybatisProperties();
    }

    @Bean("db1DataSource")
    @Override
    public DataSource dataSource() {
        return super.dataSource();
    }

    @Bean("db1TxManager")
    @Override
    public PlatformTransactionManager transactionManager(@Qualifier("db1DataSource") DataSource dataSource) {
        return super.transactionManager(dataSource);
    }

    @Bean("db1SqlSessionFactory")
    @Override
    public SqlSessionFactory sqlSessionFactory(@Qualifier("db1DataSource") DataSource dataSource,
                                               @Qualifier("db1MybatisProperties") MybatisProperties mybatisProperties) throws Exception {
        return super.sqlSessionFactory(dataSource, mybatisProperties);
    }
}
