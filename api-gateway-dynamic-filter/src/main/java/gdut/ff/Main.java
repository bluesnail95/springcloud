package gdut.ff;

import com.netflix.zuul.FilterFileManager;

import com.netflix.zuul.FilterLoader;
import com.netflix.zuul.groovy.GroovyCompiler;
import com.netflix.zuul.groovy.GroovyFileFilter;
import gdut.ff.filter.FilterConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * 2019-01-25
 * liuffei
 */
@EnableZuulProxy
@EnableConfigurationProperties({FilterConfiguration.class})
@SpringCloudApplication
public class Main {

    public static void main(String args[]) {
        new SpringApplicationBuilder(Main.class).web(true).run(args);
    }

    @Bean
    public FilterLoader filterLoader(FilterConfiguration configuration) {
        FilterLoader filterLoader = FilterLoader.getInstance();
        filterLoader.setCompiler(new GroovyCompiler());
        try {
            FilterFileManager.setFilenameFilter(new GroovyFileFilter());
            FilterFileManager.init(configuration.getInterval(),"src/main/java/gdut/ff/" + configuration.getRoot()+"/pre","src/main/java/gdut/ff/" +  configuration.getRoot()+"/post");
        } catch(Exception e) {
            throw new RuntimeException();
        }
        return filterLoader;
    }


}
