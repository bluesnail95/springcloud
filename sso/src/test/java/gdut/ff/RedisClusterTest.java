package gdut.ff;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class)
public class RedisClusterTest {

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    public void testRedisCluster() {
        ValueOperations operations = redisTemplate.opsForValue();
        operations.set("Hello","I am ok");
        String value = (String) operations.get("Hello");
        System.out.println(value);
    }

}
