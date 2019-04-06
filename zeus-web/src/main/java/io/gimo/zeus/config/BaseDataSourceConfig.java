package io.gimo.zeus.config;

import com.zaxxer.hikari.HikariConfig;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Getter
@Setter
public abstract class BaseDataSourceConfig extends HikariConfig {

    private final MybatisConfig mybatisConfig;

    private String configLocation;
    private String typeAliasesPackage;
    private String mapperLocations;

    public BaseDataSourceConfig(MybatisConfig mybatisConfig) {
        this.mybatisConfig = mybatisConfig;
    }

    protected DataSource dataSource(){
        return mybatisConfig.createDataSource(this);
    }

    protected PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    protected SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
        return mybatisConfig.createSqlSessionFactoryBean(dataSource, this).getObject();
    }
}
