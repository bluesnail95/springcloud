package gdut.ff.redis;

import gdut.ff.util.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis操作类
 * 2018-11-11
 */
@Component
public class RedisConfig {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private HashOperations<String, String, Object> mapCollections;

    private ValueOperations<String, String> stringCollections;

    /**
     * 在RedisConfig实例化之后执行init()方法
     */
    @PostConstruct
    public void init() {
        mapCollections = redisTemplate.opsForHash();
        stringCollections = redisTemplate.opsForValue();
    }

    /**
     * 添加一个Map,
     * @param key
     * @param hKey
     * @param hValue
     */
    public void addHKey(String key, String hKey, Object hValue) {
        mapCollections.put(key ,hKey,hValue);
    }

    /**
     * 删除一个map
     * @param key
     * @param hKey
     */
    public void removeHKey(String key, String hKey) {
        if(mapCollections.hasKey(key,hKey)) {
            mapCollections.delete(key,hKey);
        }
    }

    /**
     * 获取map的value值
     * @param key
     * @param hKey
     * @return
     */
    public Object getHValue(String key, String hKey) {
        if(!mapCollections.hasKey(key, hKey)) {
            return null;
        }
        return mapCollections.get(key, hKey);
    }

    /**
     * 获得一个map的全部数据
     * @param key
     * @return
     */
    public Map<String,Object> getMap(String key) {
        return mapCollections.entries(key);
    }

    /**
     * 设置键的过期时间
     * @param key
     * @param timeout
     * @param timeUnit
     */
    public void expireKey(String key, long timeout, TimeUnit timeUnit) {
        redisTemplate.expire(key,timeout,timeUnit);
    }

    public void addKey(String key, String value) {
        stringCollections.set(key, value);
    }

    public String getValue(String key) {
        return stringCollections.get(key);
    }

    /**
     * 模糊搜索
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    public void removeKey(String key) {
        redisTemplate.delete(key);
    }

}
