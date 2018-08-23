package spring.factorybean1;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.lang.Nullable;

/**
 * DemoFactoryBean class
 *
 * @author Administrator
 * @date
 */
public class DemoFactoryBean implements FactoryBean<Person> {


    @Nullable
    @Override
    public Person getObject() throws Exception {
        return new Person();
    }

    @Nullable
    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
