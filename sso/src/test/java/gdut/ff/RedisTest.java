package gdut.ff;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class)
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testSetString() {
        redisTemplate.opsForValue().set("hello","world");

        String value = (String) redisTemplate.opsForValue().get("E439A3BA123466AF79F556F269AD9093");



        System.out.println(value);
    }

}
