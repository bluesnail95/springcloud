package gdut.ff.service.impl;

import gdut.ff.domain.User;
import gdut.ff.mapper.MyRowMapper;
import gdut.ff.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * 2018-09-13
 * liuffei
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insert(User user) {
        jdbcTemplate.update("insert into user(username,password) values(?,?)",user.getUsername(),user.getPassword());
    }

    @Override
    public User findOneUser(String username, String password) {
        return jdbcTemplate.queryForObject("select * from user where username = ? and password = ?",new MyRowMapper(),username,password);
    }
}
