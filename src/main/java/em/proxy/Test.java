package em.proxy;

/**
 * Test class
 *
 * @author Administrator
 * @date
 */
public class Test {
    public static void main(String... args) {
        LiuDeHuaProxy proxy = new LiuDeHuaProxy();
        //获得刘德华的代理人
        Person p1 = proxy.getProxy();
        //通过代理人，让刘德华唱冰雨
        p1.sing("冰雨");
        //通过代理人，让刘德华表演江南style
        p1.dance("江南style");
    }
}
