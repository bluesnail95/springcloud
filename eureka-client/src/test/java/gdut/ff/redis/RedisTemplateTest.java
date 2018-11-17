package gdut.ff.redis;

import gdut.ff.Main;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class)
public class RedisTemplateTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void testListOperations() {
        ListOperations operations = redisTemplate.opsForList();
        operations.leftPush("name","liuffei");
        operations.leftPush("age","23");

        String name = (String) operations.leftPop("name");
        String age = (String) operations.leftPop("age");
        System.out.println("name:" + name + ",age:" + age);
    }
}
