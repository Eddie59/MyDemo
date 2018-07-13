package cn.javaio;

import java.io.StringReader;
import java.io.StringWriter;

/**
 * StringNodeTest class
 *
 * @author Administrator
 * @date
 */
public class StringNodeTest {

    public static void main(String... args) {
        String src = "从今天 起做一个幸福的人，/n" +
                "喂马，劈柴，周游世界，" +
                "我有一所房子，面朝大海，春暖花开，" +
                "从明天起，和每一个亲人通信，" +
                "告诉他们我的幸福";
        char[] buffer = new char[4];
        int hasRead = 0;
        try (StringReader sr = new StringReader(src)) {
            while ((hasRead = sr.read(buffer)) > 0) {
                System.out.println(new String(buffer, 0, hasRead));
            }
        } catch (Exception exp) {
        }


        try (StringWriter sw=new StringWriter(4)){
            sw.write("从今天 起做一个幸福的人，/n");
            sw.write("喂马，劈柴，周游世界，");
            sw.write("我有一所房子，面朝大海，春暖花开，");
            sw.write("从明天起，和每一个亲人通信，");
            sw.write("告诉他们我的幸福");
            System.out.println(sw.toString());
        } catch (Exception exp) {}



    }
}
