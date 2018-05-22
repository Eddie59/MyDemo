package spring.factory.factory;

import spring.factory.model.NanChangYaBo;
import spring.factory.model.NanChangYaJia;
import spring.factory.model.YaBo;
import spring.factory.model.YaJia;

/**
 * NanChangFactory class
 *
 * @author Administrator
 * @date
 */
public class NanChangFactory implements IFactory {
    @Override
    public YaBo ProduceYaBo() {
        return new NanChangYaBo();
    }

    @Override
    public YaJia produceYaJia() {
        return new NanChangYaJia();
    }
}
