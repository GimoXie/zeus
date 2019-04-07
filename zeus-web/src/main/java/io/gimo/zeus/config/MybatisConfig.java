package io.gimo.zeus.config;

import com.zaxxer.hikari.HikariDataSource;
import io.gimo.zeus.db.plugin.interceptor.PaginationResultSetHandlerInterceptor;
import io.gimo.zeus.db.plugin.interceptor.PaginationStatementHandlerInterceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class MybatisConfig {

    DataSource createDataSource(BaseDataSourceConfig dataSourceConfig) {
        return new HikariDataSource(dataSourceConfig);
    }

    SqlSessionFactoryBean createSqlSessionFactoryBean(DataSource dataSource, MybatisProperties mybatisProperties) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setVfs(SpringBootVFS.class);
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage(mybatisProperties.getTypeAliasesPackage());
        sqlSessionFactoryBean.setMapperLocations(this.resolveMapperLocations(mybatisProperties.getMapperLocations()));
        org.apache.ibatis.session.Configuration configuration = mybatisProperties.getConfiguration();
        configuration.addInterceptor(new PaginationStatementHandlerInterceptor());
        configuration.addInterceptor(new PaginationResultSetHandlerInterceptor());
        sqlSessionFactoryBean.setConfiguration(configuration);
        sqlSessionFactoryBean.setConfigurationProperties(mybatisProperties.getConfigurationProperties());
        return sqlSessionFactoryBean;
    }

    private Resource[] resolveMapperLocations(String[] mapperLocations) {
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        List<Resource> resources = new ArrayList<>();
        try {
            if (mapperLocations != null && mapperLocations.length > 0) {
                for (String mapperLocation : mapperLocations) {
                    Resource[] mappers = resourceResolver.getResources(mapperLocation);
                    resources.addAll(Arrays.asList(mappers));
                }
            }
        } catch (IOException e) {
            // ignore
        }
        return resources.toArray(new Resource[]{});
    }
}