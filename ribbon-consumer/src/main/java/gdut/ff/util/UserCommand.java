package gdut.ff.util;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolKey;
import gdut.ff.domain.User;
import org.springframework.web.client.RestTemplate;

public class UserCommand extends HystrixCommand<User> {

    private RestTemplate restTemplate;
    private Long id;

    public UserCommand(Setter setter,RestTemplate restTemplate,Long id) {

        //命令组划分
        //super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("UserGroup"))
        //        .andCommandKey(HystrixCommandKey.Factory.asKey("UserCommand")));

        //命令组+线程池划分
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("UserGroupKey"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("UserCommonKey"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("UserThreadPoolKey")));

        //super(setter);
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected User run() throws Exception {
        return restTemplate.getForObject("http://hello-service/users/{1}",User.class,id);
    }

    @Override
    public User getFallback() {
        return new User(1,"send message");
    }

    //开启缓存功能
    @Override
    protected String getCacheKey() {
        return String.valueOf(id);
    }
}
