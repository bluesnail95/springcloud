package gdut.ff.util;

import com.netflix.hystrix.HystrixCommand;
import gdut.ff.domain.User;
import org.springframework.web.client.RestTemplate;

public class UserCommand extends HystrixCommand<User> {

    private RestTemplate restTemplate;
    private Long id;

    public UserCommand(Setter setter,RestTemplate restTemplate,Long id) {
        super(setter);
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected User run() throws Exception {
        return restTemplate.getForObject("http://USER-SERVER/users/{1}",User.class,id);
    }

    @Override
    public User getFallback() {
        return new User(1,"send message");
    }
}
