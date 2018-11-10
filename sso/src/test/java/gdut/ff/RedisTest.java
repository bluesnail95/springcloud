package gdut.ff;

import org.junit.Test;
import redis.clients.jedis.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class RedisTest {

    @Test
    public void testSetString() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(10);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(3000);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);

        Set<HostAndPort> set = new HashSet<HostAndPort>();
        set.add(new HostAndPort("139.199.210.171",6380));
        set.add(new HostAndPort("139.199.210.171",6381));
        set.add(new HostAndPort("139.199.210.171",6382));
        set.add(new HostAndPort("139.199.210.171",6383));
        set.add(new HostAndPort("139.199.210.171",6384));
        set.add(new HostAndPort("139.199.210.171",6385));

        JedisCluster cluster = new JedisCluster(set,15000,10000,10,"19950821abc",config);
        cluster.setnx("hello","tomorrow will be better");
        String result = cluster.get("hello");
        System.out.println(result);
        try {
            cluster.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        Jedis jedis = new Jedis("139.199.210.171",6381,20000);
        jedis.auth("19950821abc");
        jedis.set("hello","world");
        System.out.println(jedis.get("hello"));
        jedis.close();
    }

}
