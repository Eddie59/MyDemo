package spring.springcache.springinner;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;

/**
 * DemoTest2 class
 *
 * @author Administrator
 * @date
 */
public class DemoTest2 {
    private AccountService2 accountService2;
    @Before
    public void setUp() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/springcache/springcache.xml");
        accountService2 = context.getBean("accountService2", AccountService2.class);
    }
    @Test
    public void testInject(){
        assertNotNull(accountService2);
    }
    @Test
    public void testGetAccountByName() throws Exception {
        accountService2.getAccountByName("accountName");
        accountService2.getAccountByName("accountName");
    }
}
