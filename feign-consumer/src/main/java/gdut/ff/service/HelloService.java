package gdut.ff.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("hello-service")
public interface HelloService {

    @RequestMapping("/hello/index")
    String hello();

}
