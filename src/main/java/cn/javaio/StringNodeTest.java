package cn.javaio;

import org.junit.Test;

import java.io.*;

/**
 * StringNodeTest class
 *
 * @author Administrator
 * @date
 */
public class StringNodeTest {

    @Test
    public void writeString2File() {
        String path = "./testdir";
        File dir = new File(path);
        if (dir.exists() == false) {
            dir.mkdir();
        }

        String content = "abc人民";
        File file = new File(path + "/abc.txt");
        if (file.exists() == false) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (OutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(content.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void readFile() {
        StringBuilder sb = new StringBuilder();
        String filePath = "./testdir/abc.txt";
        File file = new File(filePath);
        if (file.exists() == false) {
            return;
        }
        int hasRead = 0;
        byte[] bytes = new byte[1024];
        //把文件抽象成水管，这个理解很重要
        try (InputStream inputStream = new FileInputStream(filePath)) {
            String str="";
            while ((hasRead = inputStream.read(bytes, 0, bytes.length)) > 0) {
                sb.append(new String(bytes));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sb.toString());
    }


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


        try (StringWriter sw = new StringWriter(4)) {
            sw.write("从今天 起做一个幸福的人，/n");
            sw.write("喂马，劈柴，周游世界，");
            sw.write("我有一所房子，面朝大海，春暖花开，");
            sw.write("从明天起，和每一个亲人通信，");
            sw.write("告诉他们我的幸福");
            System.out.println(sw.toString());
        } catch (Exception exp) {
        }
    }

}
