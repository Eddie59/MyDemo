package spring.springjdbc.rdbms;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import java.sql.Types;

/**
 * InsertUser class
 *
 * @author Administrator
 * @date
 */
public class InsertUser extends SqlUpdate {
    public InsertUser(JdbcTemplate template) {
        super.setJdbcTemplate(template);
        super.setSql("insert into user (myName) VALUES (?)");
        super.declareParameter(new SqlParameter(Types.VARCHAR));
        compile();
    }
}
