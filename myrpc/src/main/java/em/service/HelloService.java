package em.service;

public class HelloService implements IHelloService {
    @Override
    public String sayHi(String name) {
        return "Hi, " + name;
    }
}
