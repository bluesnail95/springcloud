package gdut.ff.controller;

import gdut.ff.domain.User;
import gdut.ff.service.HelloService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RefactorController implements HelloService {
    @Override
    public String hello(String name) {
        return "hello " + name;
    }

    @Override
    public User hello(String name, Integer age) {
        User user = new User();
        user.setAge(age);
        user.setName(name);
        return user;
    }

    @Override
    public User hello(User user) {
        return user;
    }
}
