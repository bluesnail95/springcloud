package gdut.ff.exception;

/**
 * 自定义登录异常
 * 2018-09-21
 * liuffei
 */
public class LoginException extends RuntimeException {

    public LoginException(String message) {
        super(message);
    }
}
