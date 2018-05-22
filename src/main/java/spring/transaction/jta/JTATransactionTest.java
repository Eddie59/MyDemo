package spring.transaction.jta;


import org.junit.Test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.transaction.UserTransaction;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JTATransactionTest class
 *
 * @author Administrator
 * @date
 */
public class JTATransactionTest {

    private Connection getConn(String drive, String url, String username, String pwd) {
        Connection conn = null;
        try {
            Class.forName(drive);
            conn = DriverManager.getConnection(url, username, pwd);
        } catch (ClassNotFoundException exp) {
            exp.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    @Test
    public void Run() {
        String drive = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/mybatis2?characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Shanghai";
        String username = "root";
        String pwd = "123456";
        String drive1 = "com.mysql.cj.jdbc.Driver";
        String url1 = "jdbc:mysql://127.0.0.1:3306/mybatis3?characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Shanghai";
        String username1 = "root";
        String pwd1 = "123456";


        UserTransaction userTx = null;

        Connection connA = null;
        Statement stamtA = null;
        Connection connB = null;
        Statement stamtB = null;

        try {
            Context ctx = new InitialContext();
            //这里会报错，不清楚原因，可能跟项目容器有关，比如Jboss等
            userTx = (UserTransaction) ctx.lookup("java:comp/UserTransaction");
            connA = getConn(drive, url, username, pwd);
            connB = getConn(drive1, url1, username1, pwd1);

            userTx.begin();

            stamtA = connA.createStatement();
            stamtB = connB.createStatement();
            stamtA.execute("create table myuser(id int)");
            stamtB.execute("create table myuser(id int)");

            userTx.commit();

        } catch (Exception exp) {
            try {
                userTx.rollback();
                stamtA.close();
                stamtB.close();
                connA.close();
                connB.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
