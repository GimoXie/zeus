package io.gimo.zeus.cache.redis.template.impl;

import io.gimo.zeus.cache.redis.template.IRedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements IRedisService {

    private static Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);

    @Autowired
    private RedisTemplate<String, ?> redisTemplate;

    @Override
    public boolean set(final String key, final String value) {
        boolean result = redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            connection.set(serializer.serialize(key), serializer.serialize(value));
            return true;
        });
        return result;
    }

    @Override
    public String get(final String key){
        String result = redisTemplate.execute((RedisCallback<String>) connection -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            byte[] value =  connection.get(serializer.serialize(key));
            return serializer.deserialize(value);
        });
        return result;
    }

    @Override
    public boolean expire(final String key, long expire) {
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    @Override
    public <T> boolean setList(String key, List<T> list) {
        /*String value = JSONUtil.toJson(list);
        return set(key,value);*/
        return false;
    }

    @Override
    public <T> List<T> getList(String key,Class<T> clz) {
        /*String json = get(key);
        if(json!=null){
            List<T> list = JSONUtil.toList(json, clz);
            return list;
        }*/
        return null;
    }

    @Override
    public long lpush(final String key, Object obj) {
        /*final String value = JSONUtil.toJson(obj);
        long result = redisTemplate.execute((RedisCallback<Long>) connection -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            long count = connection.lPush(serializer.serialize(key), serializer.serialize(value));
            return count;
        });
        return result;*/
        return 0;
    }

    @Override
    public long rpush(final String key, Object obj) {
        /*final String value = JSONUtil.toJson(obj);
        long result = redisTemplate.execute((RedisCallback<Long>) connection -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            long count = connection.rPush(serializer.serialize(key), serializer.serialize(value));
            return count;
        });
        return result;*/
        return 0;
    }

    @Override
    public String lpop(final String key) {
        String result = redisTemplate.execute((RedisCallback<String>) connection -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            byte[] res =  connection.lPop(serializer.serialize(key));
            return serializer.deserialize(res);
        });
        return result;
    }


}
