package spring.transaction;


import org.junit.Before;
import org.junit.jupiter.api.Test;
import spring.transaction.model.AddressModel;
import spring.transaction.service.IAddressService;
import spring.transaction.service.annotation.AnnotationAddressServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * AnnotationtransactionTest class
 *
 * @author Administrator
 * @date
 */
public class AnnotationtransactionTest {
    private static ApplicationContext ctx;
    private static PlatformTransactionManager txManager;
    private static DataSource dataSource;
    private static JdbcTemplate jdbcTemplate;


    private static final String CREATE_TABLE_SQL = "create table test" +
            "(id int auto_increment PRIMARY KEY, " +
            "name varchar(100))";
    private static final String DROP_TABLE_SQL = "drop table test";
    private static final String CREATE_USER_TABLE_SQL = "create table user" +
            "(id int  PRIMARY KEY, " +
            "name varchar(100))";

    private static final String DROP_USER_TABLE_SQL = "drop table user";
    private static final String CREATE_ADDRESS_TABLE_SQL = "create table address" +
            "(id int auto_increment PRIMARY KEY, " +
            "province varchar(100), city varchar(100), street varchar(100), user_id int)";

    private static final String DROP_ADDRESS_TABLE_SQL = "drop table address";
    private static final String INSERT_SQL = "insert into test(id) values(?)";
    private static final String COUNT_SQL = "select count(*) from test";


    @Before
    public void init() {
        String[] config = new String[]{"classpath:spring/transaction/transaction.xml","classpath:spring/transaction/transaction-annotation.xml"};
        ctx = new ClassPathXmlApplicationContext(config);
        txManager = ctx.getBean(PlatformTransactionManager.class);
        dataSource = ctx.getBean(DataSource.class);
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Test
    public void DeclareTrunsaction()
    {
        AddressModel addressModel = new AddressModel();
        addressModel.setCity("sh");
        addressModel.setProvince("sh");
        addressModel.setStreet("宛平南路1");
        addressModel.setUserId(1);

        IAddressService addressService = ctx.getBean("addressService", AnnotationAddressServiceImpl.class);
        addressService.save(addressModel);
        addressService.countAll();
    }


}
