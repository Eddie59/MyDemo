package spring.springcache.springinner;


import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * DemoTest class
 *
 * @author Administrator
 * @date
 */

public class DemoTest {



    private AccountService1 accountService1;

    /**
     * 为毛没有作用呢？
     */
    @Before
    public void setUp() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/springcache/springcache.xml");
        accountService1 = context.getBean("accountService1", AccountService1.class);
    }

    @Test
    public void testInject() {
        this.setUp();
        assertNotNull(accountService1);
    }

    @Test
    public void testGetAccountByName() throws Exception {
        this.setUp();
        accountService1.getAccountByName("accountName");
        accountService1.getAccountByName("accountName");
        accountService1.reload();
        accountService1.getAccountByName("accountName");
        accountService1.getAccountByName("accountName");
    }
}
