package gdut.ff.mapper;

import gdut.ff.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 2018-09-13
 * liuffei
 */
public class MyRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        String name = resultSet.getString("username");
        String password = resultSet.getString("password");
        User user = new User();
        user.setPassword(password);
        user.setUsername(name);
        return user;
    }
}
