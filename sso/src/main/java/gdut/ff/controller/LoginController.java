package gdut.ff.controller;

import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.StringUtils;
import gdut.ff.domain.User;
import gdut.ff.exception.LoginArgumentsException;
import gdut.ff.exception.LoginException;
import gdut.ff.exception.PasswordException;
import gdut.ff.redis.RedisConfig;
import gdut.ff.service.IUserService;
import gdut.ff.util.ConstantUtil;
import gdut.ff.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 2018-09-13
 * liuffei
 * 登录操作类
 */
@RestController
public class LoginController {

    @Autowired
    private IUserService userService;

    @Autowired
    private RedisConfig redisConfig;

    /**
     * SSO登录接口
     * @return
     */
    @RequestMapping(value="/ssoLogin")
    public JSONObject ssoLogin(HttpServletRequest request,@RequestBody JSONObject requestJson) {
        JSONObject result = new JSONObject();
        //先判断在请求头中是否存在token,如果存在对token进行验证，验证通过，进入主页，验证不通过，校验用户名密码
        String token = request.getHeader("token");
        User user = null;
        if(!StringUtils.isNullOrEmpty(token)) {
            user = JSONObject.parseObject(redisConfig.getValue(ConstantUtil.REDIS_LOGIN_KEY+":"+token), User.class);
        }
        if(null != user) {
            result.put("msg","登录成功");
            result.put("user",user);
            result.put("code",200);
            return result;
        }
        String username = requestJson.getString("username");
        String password = requestJson.getString("password");
        if(StringUtils.isNullOrEmpty(username) ||
                StringUtils.isNullOrEmpty(password)) {
            throw new LoginArgumentsException("请传递用户名和密码");
        }
        user = userService.findOneUser(username, password);
        if(null == user) {
            throw new LoginException("用户名或密码错误");
        }
        SessionUtil.setUser(user);
        token = UUID.randomUUID().toString();
        redisConfig.addKey(ConstantUtil.REDIS_LOGIN_KEY + ":" + token, JSONObject.toJSONString(user));
        redisConfig.expireKey(ConstantUtil.REDIS_LOGIN_KEY + ":" + token, 30, TimeUnit.DAYS);
        result.put("msg","登录成功");
        result.put("user",user);
        result.put("code",200);
        result.put("token",token);
        return result;
    }

    /**
     * 登出接口
     */
    @RequestMapping(value="/logout")
    public JSONObject logout(HttpServletRequest request) {
        JSONObject result = new JSONObject();
        String token = request.getHeader("token");
        redisConfig.removeKey(ConstantUtil.REDIS_LOGIN_KEY + ":" + token);
        SessionUtil.invalidateSession();
        result.put("msg","退出登录失败");
        result.put("code",300);
        return result;
    }

    /**
     * 注册
     * @return
     */
    public JSONObject regist(HttpServletRequest request,@RequestBody JSONObject requestJson) {
        JSONObject result = new JSONObject();
        String username = requestJson.getString("username");
        String password = requestJson.getString("password");
        String confirmPassword = requestJson.getString("confirmPassword");
        if(StringUtils.isNullOrEmpty(username) ||
                StringUtils.isNullOrEmpty(password) ||
                StringUtils.isNullOrEmpty(confirmPassword)) {
            throw new LoginArgumentsException("请传递用户名和密码");
        }
        if(!password.equals(confirmPassword)) {
            throw new PasswordException("两次输入的密码不正确，请重新输入");
        }
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        userService.insert(user);
        return result;
    }
}