package spring.configuration.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

/**
 * Configuration2 class
 *
 * @author Administrator
 * @date
 */
@Configuration
@ComponentScan(basePackages = "spring.configuration.test")
public class Configuration2 {

    /**
     * 设置为lazy的bean将不会在ApplicationContext启动时提前被实例化，而是第一次向容器通过getBean索取bean时实例化的。
     * @return
     */
    @Bean
    @Lazy
    public Person getPerson() {
        Person person = new Person();
        person.setAge(28);
        person.setName("eddie");
        return person;
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Configuration2.class);
        TestBean1 testBean1 = context.getBean(TestBean1.class);
        TestBean2 testBean2 = context.getBean(TestBean2.class);
        testBean1.sayHello();
        testBean2.sayHello();

        //初始化person
        Person person = context.getBean(Person.class);
        System.out.println(person);
    }


    class Person {
        public Person() {
            System.out.println("person");
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        private String name;
        private int age;
    }
}
