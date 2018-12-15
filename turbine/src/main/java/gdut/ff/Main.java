package gdut.ff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.context.annotation.ComponentScan;

/**
 * 2018-12-15
 * liuffei
 */
@ComponentScan(basePackages = "gdut.ff")
@EnableAutoConfiguration
@EnableTurbine
@EnableDiscoveryClient
public class Main {

    public static void main(String args[]) {
        SpringApplication.run(Main.class, args);
    }


}
