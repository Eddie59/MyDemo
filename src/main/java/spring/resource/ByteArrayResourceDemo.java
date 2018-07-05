package spring.resource;

import org.junit.Test;
import org.springframework.core.io.*;

import java.io.File;
import java.io.InputStream;

/**
 * ByteArrayResourceDemo class
 *
 * @author Administrator
 * @date
 */
public class ByteArrayResourceDemo {

    @Test
    public void byteArrayResourceRun() {
        ByteArrayResource byteArrayResource = new ByteArrayResource("Hello".getBytes());
        byte[] b = byteArrayResource.getByteArray();
        System.out.println(new String(b));
    }


    @Test
    public void inputStreamResource() {
        try {
            ByteArrayResource byteArrayResource = new ByteArrayResource("Hello".getBytes());
            Resource resource = new InputStreamResource(byteArrayResource.getInputStream());

        } catch (Exception exp) {
        }
    }


    @Test
    public void fileSystemResource() {
        File file = new File("d:/test.txt");
        Resource resource = new FileSystemResource(file);
        if (resource.exists()) {
        }
    }

    @Test
    public void classPathResource() throws Exception {
        //配置文件所在的classPath路径
        String filePath = "spring/resource/abc.properties";

        Resource resource = new ClassPathResource(filePath);
        Resource resource1 = new ClassPathResource(filePath, this.getClass().getClassLoader());
        Resource resource2 = new ClassPathResource(filePath, this.getClass());
        print(resource);
        print(resource1);
        print(resource2);
    }

    private void print(Resource resource) throws Exception {
        if (resource.exists()) {
            InputStream inputStream = resource.getInputStream();
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            System.out.println(new String(bytes));
        }
    }


}
