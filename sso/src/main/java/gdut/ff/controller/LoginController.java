package gdut.ff.controller;

import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.StringUtils;
import gdut.ff.domain.User;
import gdut.ff.exception.LoginArgumentsException;
import gdut.ff.exception.LoginException;
import gdut.ff.exception.PasswordException;
import gdut.ff.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 2018-09-13
 * liuffei
 */
@RestController
public class LoginController {

    @Autowired
    private IUserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * SSO登录接口
     * @return
     */
    @RequestMapping(value="/ssoLogin")
    public JSONObject ssoLogin(HttpServletRequest request,@RequestBody JSONObject requestJson) {
        JSONObject result = new JSONObject();
        boolean isLogin = false;
        //1.从cookies中获取JSESSIONID
        Cookie cookies[] = request.getCookies();
        if(null == cookies || cookies.length == 0) {
            //如果JSESSION不存在，就根据用户名密码登录
            String username = requestJson.getString("username");
            String password = requestJson.getString("password");
            if(StringUtils.isNullOrEmpty(username) ||
                    StringUtils.isNullOrEmpty(password)) {
                throw new LoginArgumentsException("请传递用户名和密码");
            }
            User loginUser = userService.findOneUser(username, password);
            if(null == loginUser) {
                throw new LoginException("用户名或密码错误");
            }
            String jSessionId = request.getSession().getId();
            //以key=jSessionId，value=loginUser的形式存储在Redis中
            redisTemplate.opsForValue().set(jSessionId,loginUser);
            result.put("msg","登录成功");
            result.put("user",loginUser);
            result.put("code",200);
            return result;
        }
        for(int i = 0;i < cookies.length;i++) {
            Cookie cookie = cookies[i];
            String cookieName = cookie.getName();
            if("JSESSIONID".equals(cookieName)) {
                //根据cookieValue获取用户的登录密码，进行直接登录。
                String cookieValue = cookie.getValue();
                if(!redisTemplate.hasKey(cookieValue)) {
                    throw new LoginException("Cookie 已经失效，请重新登录");
                }
                User loginUser = (User) redisTemplate.opsForValue().get(cookieValue);
                if(null != loginUser) {
                    result.put("msg","登录成功");
                    result.put("user",loginUser);
                    result.put("code",200);
                }
            }
        }
        return result;
    }

    /**
     * 登出接口
     */
    @RequestMapping(value="/logout")
    public JSONObject logout(HttpServletRequest request) {
        JSONObject result = new JSONObject();
        boolean isDelete = false;
        Cookie cookies[] = request.getCookies();
        if(null != cookies && cookies.length > 0) {
            for(int i = 0;i < cookies.length;i++) {
                Cookie cookie = cookies[i];
                String cookieKey = cookie.getName();
                if(cookieKey.equals("JSESSIONID")) {
                    String cookieValue = cookie.getValue();
                    isDelete = redisTemplate.delete(cookieValue);
                }


            }
        }
        if(isDelete) {
            result.put("msg","退出登录成功");
            result.put("code",200);
        }else {
            result.put("msg","退出登录失败");
            result.put("code",300);
        }
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