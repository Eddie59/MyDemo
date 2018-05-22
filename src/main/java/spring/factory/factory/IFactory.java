package spring.factory.factory;

        import spring.factory.model.YaBo;
        import spring.factory.model.YaJia;

public interface IFactory {
    YaJia produceYaJia();
    YaBo ProduceYaBo();
}
