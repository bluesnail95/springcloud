package gdut.ff.domain;

import java.io.Serializable;

/**
 * liuffei 2018-09-13
 */
public class User implements Serializable {

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "username=" + this.username + ",password=" + this.password;
    }
}

