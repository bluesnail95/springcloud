package gdut.ff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * liuffei
 * 2018-11-18
 */
@RestController
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/ribbon-consumer", method= RequestMethod.GET)
    public String helloConsumer() {
        return restTemplate.getForEntity("http://hello-service/hello/index",String.class).getBody();
    }
}
