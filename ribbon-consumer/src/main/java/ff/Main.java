package ff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * 2018-09-16
 * liuffei
 */
@ComponentScan(basePackages = "gdut.ff")
@EnableAutoConfiguration
@EnableDiscoveryClient
public class Main {

    public static void main(String args[]) {
        SpringApplication.run(Main.class, args);
    }


}
