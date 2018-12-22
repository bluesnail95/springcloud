package gdut.ff.service;

import gdut.ff.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

//@FeignClient("hello-service")
//public interface HelloService {
//
//    @RequestMapping("/hello/index")
//    String hello();
//
//    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
//    String hello(@RequestParam("name") String name);
//
//    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
//    String hello(@RequestHeader(value = "name") String name,@RequestHeader(value = "age") Integer age);
//
//    @RequestMapping(value = "/hello3",method = RequestMethod.POST)
//    String hello(@RequestBody User user);
//
//}
