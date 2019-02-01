package gdut.ff;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 2018-11-18
 * liuffei
 */
@ComponentScan(basePackages = "gdut.ff")
@EnableAutoConfiguration
@EnableDiscoveryClient
@RestController
public class Main {

    private Logger logger = LoggerFactory.getLogger(Main.class);

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RequestMapping(value = "/trace-1",method = RequestMethod.GET)
    public String trace() {
        logger.info("trace-1");
        return restTemplate().getForEntity("http://trace-2/trace-2",String.class).getBody();
    }

    public static void main(String args[]) {
        SpringApplication.run(Main.class, args);
    }


}

