package gdut.ff.util;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import gdut.ff.domain.User;
import org.springframework.web.client.RestTemplate;

/**
 * 2018-11-27
 * liuffei
 */
public class UserGetCommand extends HystrixCommand<User> {

    private static final HystrixCommandKey GETTER_KEY = HystrixCommandKey.Factory.asKey("UserGetCommandKey");
    private int id;
    private RestTemplate restTemplate;

    public UserGetCommand(RestTemplate restTemplate) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("UserGetGroupKey")).andCommandKey(GETTER_KEY));
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected User run() throws Exception {
        return restTemplate.getForObject("http://hello-service/users/{1}",User.class,id);
    }

    @Override
    protected String getCacheKey() {
        //根据id置入缓存
        return String.valueOf(id);
    }

    public static void flushCache(int id) {
        //刷新缓存，根据id清理
        HystrixRequestCache.getInstance(GETTER_KEY, HystrixConcurrencyStrategyDefault.getInstance()).clear(String.valueOf(id));
    }
}
