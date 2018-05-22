package spring.transaction.dao.impl;

import spring.transaction.dao.IAddressDao;
import spring.transaction.model.AddressModel;
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
 * AddressImpl class
 *
 * @author Administrator
 * @date
 */
public class AddressImpl extends NamedParameterJdbcDaoSupport implements IAddressDao {
    private final String INSERT_SQL = "insert into address(province, city, street, userId)" +
            "values(:province, :city, :street, :userId)";
    private final String COUNT_ALL_SQL = "select * from address";

    @Override
    public void save(AddressModel address) {
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(address);
        getNamedParameterJdbcTemplate().update(INSERT_SQL, sqlParameterSource);
    }

    @Override
    public int countAll() {
        List address=new ArrayList();

      Object obj= getJdbcTemplate().query(COUNT_ALL_SQL,new ResultSetExtractor(){
            @Override
            public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
                List list = new ArrayList();
                while (rs.next()) {
                    AddressModel model=new AddressModel();
                    model.setUserId(rs.getInt("userId"));
                    list.add(model);
                }
                return list;
            }
        });

      if(obj instanceof Collection)
      {
          address=(List)obj;
      }

        return address.size();
    }
}
