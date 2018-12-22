package gdut.ff.controller;

import gdut.ff.domain.User;
import gdut.ff.domain.User2;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User2 users(@PathVariable int id) {
        return new User2(id,"This is a User");
    }
}
