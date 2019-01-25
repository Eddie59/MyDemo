package spring.beanfactorypostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] definitionNames = beanFactory.getBeanDefinitionNames();
        for (String definitionName:definitionNames){
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(definitionName);
            if(definitionName.equals("car")){
                PropertyValue[] propertyValues = beanDefinition.getPropertyValues().getPropertyValues();
                for (PropertyValue pv:propertyValues){
                    System.out.println(pv.getName()+" "+pv.getValue());
                }

            }
        }
    }

}
