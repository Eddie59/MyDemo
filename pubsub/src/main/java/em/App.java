package em;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        try (InputStreamReader inputStreamReader = new InputStreamReader(System.in);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                if (str.equals("stop")) {
                    break;
                } else {
                    System.out.println(str);
                }
            }
            throw new Exception("");
        } catch (Exception e) {
            e.printStackTrace();
        }


/*
        // 连接redis服务端
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "127.0.0.1", 6379);
        //订阅者
        SubThread subThread1 = new SubThread(jedisPool);
        subThread1.start();
        SubThread subThread2 = new SubThread(jedisPool);
        subThread2.start();
        SubThread subThread3 = new SubThread(jedisPool);
        subThread3.start();

        //发布者
        Publisher publisher = new Publisher(jedisPool);
        publisher.start();*/
    }
}
