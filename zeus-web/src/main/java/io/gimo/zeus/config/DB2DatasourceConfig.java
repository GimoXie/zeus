package io.gimo.zeus.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource.db2")
@MapperScan(basePackages = "io.gimo.zeus.db.dao.db2", sqlSessionFactoryRef = "db2SqlSessionFactory")
public class DB2DatasourceConfig extends BaseDataSourceConfig {

    public DB2DatasourceConfig(MybatisConfig mybatisConfig) {
        super(mybatisConfig);
    }

    @Bean("db2DataSource")
    @Override
    public DataSource createDataSource() throws Exception {
        return super.createDataSource();
    }

    @Bean("db2TxManager")
    @Override
    public PlatformTransactionManager createTxManager(@Qualifier("db2DataSource") DataSource dataSource) {
        return super.createTxManager(dataSource);
    }

    @Bean("db2SqlSessionFactory")
    @Override
    public SqlSessionFactory createSqlSessionFactory(@Qualifier("db2DataSource") DataSource dataSource) throws Exception {
        return super.createSqlSessionFactory(dataSource);
    }
}
