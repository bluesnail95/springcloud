package gdut.ff.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/hello")
public class HelloController {

    private final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index() {
        String serviceId = "hello-service";
        List<ServiceInstance> instance = discoveryClient.getInstances(serviceId);
        if(null != instance) {
            for(int i = 0;i < instance.size();i++) {
                System.out.println(instance.get(i).getHost() + ":" + instance.get(i).getPort());
            }
        }

        //模拟服务阻塞，Hystrix默认超时时间是2000毫秒
        long timeout = new Random().nextInt(3000);
        System.out.println("timeout is:" + timeout);
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Hello,world";
    }
}
