package gdut.ff;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import zipkin.server.EnableZipkinServer;

/**
 * 2018-11-18
 * liuffei
 */
@ComponentScan(basePackages = "gdut.ff")
@EnableAutoConfiguration
@EnableZipkinServer
public class Main {

    public static void main(String args[]) {
        SpringApplication.run(Main.class, args);
    }


}

