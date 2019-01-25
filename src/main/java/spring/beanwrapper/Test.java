package spring.beanwrapper;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.PropertyValue;

/**
 * Test class
 *
 * @author Administrator
 * @date
 */
public class Test {
    public static void main(String...args){
        Wheel leftwheel=new Wheel();
        BeanWrapper beanWrapper= new BeanWrapperImpl(leftwheel);
        PropertyValue propertyLeft=new PropertyValue("position","左边");
        beanWrapper.setPropertyValue(propertyLeft);
        System.out.println(beanWrapper.getWrappedInstance());

        Driver driver=new Driver();
        BeanWrapper driverWrapper=new BeanWrapperImpl(driver);
        driverWrapper.setPropertyValue("age","28");
        System.out.println(driverWrapper.getWrappedInstance());


        Car car=new Car();//这里的car对象是刚new出来的，相当于刚实例化的bean对象
        BeanWrapper carWrapper=new BeanWrapperImpl(car);
        carWrapper.setPropertyValue("name","audi a7");//使用beanWrapper对象对bean的name属性设置值，值相当于从xml或者properties读出来的
        carWrapper.setPropertyValue("wheels",leftwheel);//那么问题来了，在程序运行过程中想要给bean的属性重新赋值，应该怎么做？使用beanWrapper呗
        carWrapper.setPropertyValue("driver",driver);
        System.out.println(carWrapper.getWrappedInstance());

        int age=(int)carWrapper.getPropertyValue("driver.age");
        System.out.println(age);

        carWrapper.setPropertyValue("driver.age",27);
        System.out.println(carWrapper.getWrappedInstance());

    }
}
