package gdut.ff;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * liuffei
 * 2018-11-28
 */
public class RedisTest {

    /**
     * setnx
     */
    @Test
    public void testSetnx() {
        final Jedis jedis = new Jedis("127.0.0.1",6379);
        final CountDownLatch latch = new CountDownLatch(2);

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                System.out.println("t1 begin");
                set(jedis,"hello","world");
                latch.countDown();
            }
        });
        Thread t2 = new Thread(new Runnable(){
            public void run() {
                System.out.println("t2 begin");
                long result = 0;
                set(jedis,"hello","world222");
                latch.countDown();
            }
        });

        t1.start();
        t2.start();

        try {
            latch.await();
            System.out.print("end");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            jedis.close();
            System.out.println("close");
        }
    }

    synchronized private void set(Jedis jedis,String key,String value) {
        if(jedis.exists(key)) {
            jedis.del(key);
        }
        jedis.setnx(key,value);
    }

    synchronized private void del(Jedis jedis,String key) {
        jedis.del(key);
    }

    /**
     * incr 对值进行自增
     */
    @Test
    public void testIncrby() {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.incr("count");
        jedis.incrBy("count",10);
        System.out.println(jedis.get("count"));
        jedis.close();
    }

    @Test
    public void testListPush() {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.del("list");
        jedis.rpush("list","one","two","three","four");
        jedis.rpop("list");

        List<String> list =  jedis.lrange("list",0,7);
        for(int i = 0;i < list.size();i++) {
            System.out.println(list.get(i));
        }

        jedis.close();
    }
}
