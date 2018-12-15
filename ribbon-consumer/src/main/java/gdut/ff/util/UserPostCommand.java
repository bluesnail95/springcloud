package gdut.ff.util;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import gdut.ff.domain.User;
import org.springframework.web.client.RestTemplate;

import static com.netflix.hystrix.HystrixCommand.*;

/**
 * 2018-11-27
 * liuffei
 */
public class UserPostCommand  extends HystrixCommand<User> {

    private User user;
    private RestTemplate restTemplate;

    public UserPostCommand(RestTemplate restTemplate, User user) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("UserGetGroupKey")));
        this.restTemplate = restTemplate;
        this.user = user;
    }

    @Override
    protected User run() throws Exception {
        User u = restTemplate.postForObject("http://hello-service/users",user,User.class);
        UserGetCommand.flushCache(user.getId());
        return u;
    }
}
