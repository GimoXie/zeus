package io.gimo.zeus.config;

import com.google.common.base.Strings;
import com.zaxxer.hikari.HikariDataSource;
import io.gimo.zeus.db.plugin.interceptor.PaginationResultSetHandlerInterceptor;
import io.gimo.zeus.db.plugin.interceptor.PaginationStatementHandlerInterceptor;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@org.springframework.context.annotation.Configuration
public class MybatisConfig {

    private final MybatisProperties mybatisProperties;

    public MybatisConfig(MybatisProperties mybatisProperties) {
        this.mybatisProperties = mybatisProperties;
    }

    public DataSource createDataSource(BaseDataSourceConfig dataSourceConfig) {
        return new HikariDataSource(dataSourceConfig);
    }

    public SqlSessionFactoryBean createSqlSessionFactoryBean(DataSource dataSource, BaseDataSourceConfig dataSourceConfig) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setVfs(SpringBootVFS.class);
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage(dataSourceConfig.getTypeAliasesPackage());
        sqlSessionFactoryBean.setMapperLocations(this.resolveMapperLocations(dataSourceConfig.getMapperLocations()));
        Configuration configuration = new Configuration();
        if (mybatisProperties.getConfiguration() != null) {
            BeanUtils.copyProperties(mybatisProperties.getConfiguration(), configuration);
        }
        configuration.addInterceptor(new PaginationStatementHandlerInterceptor());
        configuration.addInterceptor(new PaginationResultSetHandlerInterceptor());
        sqlSessionFactoryBean.setConfiguration(configuration);
        sqlSessionFactoryBean.setConfigurationProperties(mybatisProperties.getConfigurationProperties());
        return sqlSessionFactoryBean;
    }

    private Resource[] resolveMapperLocations(String mapperLocation) {
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        List<Resource> resources = new ArrayList<>();
        if (!Strings.isNullOrEmpty(mapperLocation)) {
            try {
                Resource[] mappers = resourceResolver.getResources(mapperLocation);
                resources.addAll(Arrays.asList(mappers));
            } catch (IOException e) {
                // ignore
            }
        }
        return resources.toArray(new Resource[]{});
    }
}