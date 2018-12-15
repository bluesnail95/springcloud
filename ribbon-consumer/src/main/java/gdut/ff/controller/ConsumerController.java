package gdut.ff.controller;

import gdut.ff.service.HelloService;
import gdut.ff.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * liuffei
 * 2018-11-18
 */
@RestController
public class ConsumerController {

    @Autowired
    private HelloService helloService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/ribbon-consumer", method= RequestMethod.GET)
    public String helloConsumer() {
        return helloService.helloService();
    }

    @RequestMapping(value = "/ribbon-user", method = RequestMethod.GET)
    public String helloUser() {
        return userService.getUserById(1).toString();
    }

    @RequestMapping(value = "/ribbon-user-async", method = RequestMethod.GET)
    public String helloUserAsync() {
        return userService.getUserByIdAsync(1).toString();
    }

    @RequestMapping(value = "/ribbon-user-exception", method = RequestMethod.GET)
    public void helloUserException() {
        userService.getUserThrowException();
    }
}
