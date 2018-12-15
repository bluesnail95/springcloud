package gdut.ff.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import gdut.ff.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.Future;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "defaultUser",ignoreExceptions = {HystrixBadRequestException.class})
    @CacheResult(cacheKeyMethod = "getUserByIdCacheKey")
    public User getUserById(int id) {
        return restTemplate.getForObject("http://hello-service/users/{1}", User.class,id);
    }

    private int getUserByIdCacheKey(int id) {
        return id;
    }

    @HystrixCommand(fallbackMethod = "defaultUserSec")
    public User defaultUser(int id) {
        return new User(1,"First Fallback");
    }

    public User defaultUserSec(int id) {
        return new User(1,"Second Fallback");
    }

    @HystrixCommand(fallbackMethod = "defaultUser")
    public Future<User> getUserByIdAsync(int id){
        return new AsyncResult<User>() {
            @Override
            public User invoke() {
                return restTemplate.getForObject("http://hello-service/users/{1}", User.class,id);
            }
        };
    }

    //对不同异常进行针对处理
    @HystrixCommand(fallbackMethod = "fallback1")
    public void getUserThrowException() {
        throw new RuntimeException("getUserById command failed");
    }

    public void fallback1(Throwable e) {
        System.out.println("getUserThrowException command failed".equals(e.getMessage()));
    }

    @HystrixCommand(commandKey = "UserCommonKey",groupKey = "UserGroupKey",threadPoolKey = "UserThreadPoolKey")
    public User getUserByKey(int id) {
        return restTemplate.getForObject("http://hello-service/users/{1}", User.class,id);
    }


}
