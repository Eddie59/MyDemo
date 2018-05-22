package spring.springjdbc.rdbms;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.object.GenericSqlQuery;

/**
 * UserGenericSqlQuery class
 *
 * @author Administrator
 * @date
 */
public class UserGenericSqlQuery extends GenericSqlQuery {

    public UserGenericSqlQuery(JdbcTemplate jdbcTemplate)
            throws IllegalAccessException, InstantiationException {
        super.setJdbcTemplate(jdbcTemplate);
        super.setSql("select * from user");
        super.setRowMapper(new UserRowMapper());
        compile();
    }

}
