package em.service;

/**
 * HelloService class
 *
 * @author Administrator
 * @date
 */

public class HelloService {

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String sayHello() {
        return "hi:" + msg;
    }

}
