package gdut.ff.controller;

import gdut.ff.domain.User;
import gdut.ff.service.HelloService;
import gdut.ff.service.RefactorHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConsumerController {

    //@Autowired
    //HelloService helloService;

    @Autowired
    RefactorHelloService refactorHelloService;

//    @RequestMapping(value = "/feign-consumer",method = RequestMethod.GET)
//    public String helloConsumer() {
//        return helloService.hello();
//    }
//
//    @RequestMapping(value = "/feign-consumer2",method = RequestMethod.GET)
//    public String helloConsumer2() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(helloService.hello()).append("\n");
//        sb.append(helloService.hello("liuffei")).append("\n");
//        sb.append(helloService.hello("hello",24)).append("\n");
//        sb.append(helloService.hello(new User("feifei",20))).append("\n");
//        return sb.toString();
//    }

    @RequestMapping(value = "/feign-consumer3",method = RequestMethod.GET)
    public String helloConsumer3() {
        StringBuilder sb = new StringBuilder();
        //sb.append(refactorHelloService.hello()).append("\n");
        sb.append(refactorHelloService.hello("liuffei")).append("\n");
        sb.append(refactorHelloService.hello("hello",24)).append("\n");
        sb.append(refactorHelloService.hello(new User("feifei",20))).append("\n");
        return sb.toString();
    }
}
