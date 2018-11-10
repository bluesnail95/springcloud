package gdut.ff;

import gdut.ff.redis.RedisConfig;
import gdut.ff.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class)
public class UserServiceTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedisConfig redisConfig;

    @Test
    public void test() {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("hello","world");
        System.out.println(valueOperations.get("hello"));
    }

    @Test
    public void testRedisConfig() {
        redisConfig.init();
        redisConfig.addHKey("loginSessionMap","1","liufeifei");
        redisConfig.addHKey("loginSessionMap","2","Sophie");

        Map<String,Object> loginSessionMap = redisConfig.getMap("loginSessionMap");
        for(Map.Entry<String, Object> session:loginSessionMap.entrySet()) {
            System.out.println(session.getKey()+":" +session.getValue());
        }


    }

}
