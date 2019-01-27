package gdut.ff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

/**
 * 2019-01-20
 * liuffei
 */
@ComponentScan(basePackages = "gdut.ff")
@EnableAutoConfiguration
public class Main {

    public static void main(String args[]) {
        new SpringApplicationBuilder(Main.class).web(true).run(args);
    }


}
