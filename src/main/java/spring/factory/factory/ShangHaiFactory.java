package spring.factory.factory;

import spring.factory.model.ShangHaiYaBo;
import spring.factory.model.ShangHaiYaJia;
import spring.factory.model.YaBo;
import spring.factory.model.YaJia;

/**
 * ShangHaiFactory class
 *
 * @author Administrator
 * @date
 */
public class ShangHaiFactory implements IFactory {
    @Override
    public YaJia produceYaJia() {
        return new ShangHaiYaJia();
    }

    @Override
    public YaBo ProduceYaBo() {
        return new ShangHaiYaBo();
    }
}
