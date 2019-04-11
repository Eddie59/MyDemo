package cn.utilstream;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.*;

public class BufferedOutPutStreamDemo {

    @Test
    public void demo() throws Exception {
/*        byte[] bytes = "abc".getBytes();
        //直接使用FileOutputStream写入内容
        FileOutputStream fileOutputStream = new FileOutputStream("test.txt", true);
        fileOutputStream.write(bytes);

        //使用BufferedOutputStream包装，先write到内存，然后flush再写入磁盘，提升效率
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        bufferedOutputStream.write(bytes);
        bufferedOutputStream.flush();
        bufferedOutputStream.close();*/


     /*   ClassLoader classLoader = this.getClass().getClassLoader();
        String path = classLoader.getResource("").getPath();
        System.out.println(path);
        FileInputStream inputStream = new FileInputStream("test.txt");
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        System.out.println(new String(bytes));

        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        bufferedInputStream.read(bytes,0,bufferedInputStream.available());
        System.out.println(new String(bytes));
        inputStream.close();
        bufferedInputStream.close();*/



        FileReader fileReader = new FileReader("test.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while (true){
            String line=bufferedReader.readLine();
            if(line!=null&&line.length()>0){
                System.out.println(line);
            }
            else
            {
                break;
            }
        }
        fileReader.close();
        bufferedReader.close();

    }


}
