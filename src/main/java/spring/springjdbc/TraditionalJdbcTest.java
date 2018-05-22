package spring.springjdbc;

import org.junit.Test;

import java.sql.*;

/**
 * TraditionalJdbcTest class
 *
 * @author Administrator
 * @date
 */
public class TraditionalJdbcTest {

    String driver="com.mysql.cj.jdbc.Driver";
    String url="jdbc:mysql://127.0.0.1:3306/myspring?characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Shanghai";
    String name="root";
    String pwd="123456";

    private Connection GetConn(String url, String name, String pwd) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, name, pwd);
        } catch (SQLException exp) {

        }
        return conn;
    }

    private boolean initDriver(String driver) {
        boolean result=true;
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException exp) {
            result=false;
        }
        return result;
    }

    private void closeResultSet(ResultSet set)
    {
        if(set!=null)
        {
            try {
                set.close();
            }
            catch (SQLException exp)
            {}
        }
    }
    private void closeConn(Connection conn)
    {
        if(conn!=null)
        {
            try {
                conn.close();
            }
            catch (SQLException exp)
            {}
        }
    }
    private void closeState(PreparedStatement statement)
    {
        if(statement!=null)
        {
            try {
                statement.close();
            }
            catch (SQLException exp)
            {}
        }
    }
    @Test
    public void Run() {

        if(initDriver(driver))
        {
           Connection conn= GetConn(url,name,pwd);
           if(conn!=null)
           {
               PreparedStatement pst=null;
               try {
                   String sql="select * from student";
                   Statement statement=conn.createStatement();
                   ResultSet set=statement.executeQuery(sql);
                   if(set!=null)
                   {
                       if(set.next())
                       {
                           String name= set.getString(2);
                       }
                       closeResultSet(set);
                       closeState(pst);
                       closeConn(conn);
                   }

               }
               catch (SQLException sqlexp)
               {

               }

           }
        }
    }

}
