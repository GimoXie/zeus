package io.gimo.zeus.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

    private static Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    @Bean
    @ConfigurationProperties(prefix="spring.redis")
    public JedisPoolConfig getRedisConfig(){
        return new JedisPoolConfig();
    }

    @Bean
    @ConfigurationProperties(prefix="spring.redis")
    public JedisConnectionFactory getConnectionFactory(){
        JedisConnectionFactory factory = new JedisConnectionFactory(getRedisConfig());
        logger.info("JedisConnectionFactory bean init success.");
        return factory;
    }


    @Bean
    public RedisTemplate<?, ?> getRedisTemplate(){
        return new StringRedisTemplate(getConnectionFactory());
    }

    // jedis集成方法 https://blog.csdn.net/i_vic/article/details/53096356
    // redistemplate 集成方法 https://blog.csdn.net/i_vic/article/details/53081241
}
