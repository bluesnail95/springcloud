package gdut.ff.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import java.util.Map;

@Component
public class RedisConfig {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private HashOperations<String, String, Object> mapCollections;

    public void init() {
        mapCollections = redisTemplate.opsForHash();
    }

    public static final String REDIS_SESSION_KEY = "loginSessionMap";

    public void addHKey(String key, String hKey, Object hValue) {
        mapCollections.put(key ,hKey,hValue);
    }

    public void removeHKey(String key, String hKey) {
        if(mapCollections.hasKey(key,hKey)) {
            mapCollections.delete(key,hKey);
        }
    }

    public Map<String,Object> getMap(String key) {
        return mapCollections.entries(key);
    }

}
