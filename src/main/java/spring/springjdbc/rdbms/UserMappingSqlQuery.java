package spring.springjdbc.rdbms;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * UserMappingSqlQuery class
 *
 * @author Administrator
 * @date
 */
public class UserMappingSqlQuery extends MappingSqlQuery<User> {

    public UserMappingSqlQuery(JdbcTemplate jdbcTemplate)
    {
        super.setDataSource(jdbcTemplate.getDataSource());
        super.setSql("select * from User where myName=:name");
        super.declareParameter(new SqlParameter("name", Types.VARCHAR));
        compile();
    }

    @Override
    protected User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User model = new User();
        model.setId(rs.getInt("id"));
        model.setMyName(rs.getString("myName"));
        return model;
    }

}
