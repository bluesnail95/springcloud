package gdut.ff.util;

import gdut.ff.domain.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 2018-11-03
 * session相关操作的工具类
 */
public class SessionUtil {

    private static Map<String, HttpSession> sessionMap =  new HashMap<String,HttpSession>();

    private static Map<String,User> userMap = new HashMap<String,User>();

    public static HttpSession getSession() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getSession();
    }

    public static HttpSession getSession(String sessionId) {
        return sessionMap.get(sessionId);
    }

    public static void setSession() {
        sessionMap.put(getSession().getId(),getSession());
    }

    public static void setUser(User user) {
        setSession();
        userMap.put(getSession().getId(),user);
    }

    public static User getCurrentUser() {
        return userMap.get(getSession().getId());
    }

    public static User getUser(String sessionId) {
        return userMap.get(sessionId);
    }

    public static void invalidateSession(String sessionId) {
        HttpSession session = sessionMap.get(sessionId);
        session.invalidate();
        sessionMap.remove(sessionId);
        userMap.remove(sessionId);
    }

    public static void invalidateSession() {
        HttpSession session = getSession();
        session.invalidate();
        sessionMap.remove(session.getId());
        userMap.remove(session.getId());
    }
}
