package spring.transaction.dao.impl;

import spring.transaction.dao.IUserDao;
import spring.transaction.model.UserModel;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * UserImpl class
 *
 * @author Administrator
 * @date
 */
public class UserImpl extends NamedParameterJdbcDaoSupport implements IUserDao {

    private final String INSERT_SQL = "insert into user(name) values(:name)";
    private final String COUNT_ALL_SQL = "select count(*) from user";

    @Override
    public void save(UserModel user) {
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user);
        getNamedParameterJdbcTemplate().update(INSERT_SQL, sqlParameterSource);
    }

    @Override
    public int countAll() {
        Object obj = getNamedParameterJdbcTemplate().query(COUNT_ALL_SQL, new ResultSetExtractor<Object>() {
            @Override
            public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
                List list = new ArrayList();
                while (rs.next()) {
                    UserModel model = new UserModel();
                    model.setName(rs.getString("name"));
                    list.add(model);
                }
                return list;
            }
        });
        if (obj instanceof Collection) {
            return ((Collection) obj).size();
        }
        return 0;
    }
}
