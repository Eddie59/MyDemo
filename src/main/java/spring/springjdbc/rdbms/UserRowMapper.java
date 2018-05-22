package spring.springjdbc.rdbms;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * UserRowMapper class
 *
 * @author Administrator
 * @date
 */
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user=new User();
        user.setId(resultSet.getInt("id"));
        user.setMyName(resultSet.getString("myName"));
        return user;
    }
}
