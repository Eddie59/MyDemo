package cn.javaio.bytearrayoutputstream;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Demo class
 *
 * @author Administrator
 * @date
 */
public class Demo {

    /**
     *
     * protected byte buf[];
     * protected int count;
     * 看源码可知，ByteArrayOutputStream里面有两个变量，一个是二进制数组，用来缓存内容的，一个是count二进制数
     * @throws Exception
     */
    @Test
    public void run() throws Exception {
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList("abc", "def", "gh"));

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        for (String item : list) {
            //把二进制数组缓存到buf[]中
            byteStream.write(item.getBytes());
        }

        //返回Arrays.copyOf(buf, count)
        byte[] bytes = byteStream.toByteArray();

        System.out.println(new String(bytes));
    }
}
