package spring.process;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Person implements BeanNameAware,ApplicationContextAware,InitializingBean {
    private String id;
    private ApplicationContext applicationContext;

    public Person(){
        System.out.println("调用构造函数实例化Person");
    }

    public void myInit(){
        System.out.println("通过initMethod方法 自定义初始化");
    }

    /**
     * 这样就可以得到Bean在Spring上下文的id
     */
    @Override
    public void setBeanName(String beanId) {
        this.id = beanId;
    }

    /**
     * 此Bean在applicationContext这个上下文中
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    /**
     *
     */
    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
