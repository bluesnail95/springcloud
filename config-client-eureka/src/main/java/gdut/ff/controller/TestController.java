package gdut.ff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 2019-01-20
 * liufeifei
 */
@RefreshScope
@RestController
public class TestController {

    @Value("${from}")
    private String from;

    @Autowired
    private Environment environment;

    @RequestMapping("/from1")
    public String from1() {
        return this.from;
    }

    @RequestMapping("/from2")
    public String from2() {
        return environment.getProperty("from","undefined");
    }
}
