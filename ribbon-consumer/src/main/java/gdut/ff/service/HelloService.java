package gdut.ff.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * liuffei
 * 2018-11-19
 */
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "error")
    public String helloService() {
        long start = System.currentTimeMillis();
        String body =  restTemplate.getForEntity("http://hello-service/hello/index",String.class).getBody();
        System.out.println("spend time is:"+ (System.currentTimeMillis() - start));
        return body;
    }

    public String error() {
        return "error";
    }
}
