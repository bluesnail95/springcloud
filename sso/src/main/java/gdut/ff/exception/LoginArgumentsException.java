package gdut.ff.exception;

/**
 * 自定义登录参数异常
 * liuffei
 * 2018-09-21
 */
public class LoginArgumentsException  extends RuntimeException{

    public LoginArgumentsException(String message) {
        super(message);
    }
}
