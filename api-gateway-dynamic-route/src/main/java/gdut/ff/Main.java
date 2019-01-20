package gdut.ff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * 2019-01-20
 * liuffei
 */
//@ComponentScan(basePackages = "gdut.ff")
//@EnableAutoConfiguration
@EnableZuulProxy
@SpringCloudApplication
public class Main {


//    public static void main(String args[]) {
//        SpringApplication.run(Main.class, args);
//    }


}
