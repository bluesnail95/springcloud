package gdut.ff.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
    public User getUserById(int id) {
        return restTemplate.getForObject("http://hello-service/users/{1}", User.class,id);
    }

    @HystrixCommand(fallbackMethod = "defaultUserSec")
    public User defaultUser(int id) {
        return new User(1,"First Fallback");
    }

    public User defaultUserSec(int id) {
        return new User(1,"Second Fallback");
    }

    @HystrixCommand
    public Future<User> getUserByIdAsync(int id){
        return new AsyncResult<User>() {
            @Override
            public User invoke() {
                return restTemplate.getForObject("http://hello-service/users/{1}", User.class,id);
            }
        };
    }
}
