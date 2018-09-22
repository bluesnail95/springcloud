package gdut.ff.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * liuffei
 * 2018-09-18
 */
public class RedisOperation {

    @Autowired
    private RedisTemplate redisTemplate;

    public void setString(Object key,Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void getString(Object key) {
        redisTemplate.opsForValue().get(key);
    }

    public long addListLeftPush(Object key,Object element) {
        return redisTemplate.opsForList().leftPush(key, element);
    }

    public long addListRightPush(Object key,Object element) {
        return redisTemplate.opsForList().rightPush(key, element);
    }

    public boolean delete(Object key) {
        return redisTemplate.delete(key);
    }

    /**
     * 设置过期时间,以秒为单位
      * @param key
     * @param timeout
     */
    public boolean expireSeconds(Object key,long timeout) {
        return redisTemplate.expire(key,timeout, TimeUnit.SECONDS);
    }
}
