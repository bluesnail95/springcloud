package gdut.ff.domain.response;

import gdut.ff.base.BaseSerializableBean;
import gdut.ff.domain.User;

import java.util.List;

/**
 * liuffei
 * 2018-09-23
 */
public class UserResponse extends BaseSerializableBean {

    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
