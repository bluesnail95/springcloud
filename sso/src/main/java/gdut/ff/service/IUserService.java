package gdut.ff.service;

import gdut.ff.domain.User;

/**
 * 2018-09-13
 * liuffei
 */
public interface IUserService {

    void insert(User user);

    User findOneUser(String username,String password);
}
