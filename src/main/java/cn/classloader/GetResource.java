package cn.classloader;

import org.junit.Test;

import java.io.InputStream;
import java.net.URL;

/**
 * GetResource class
 *
 * @author Administrator
 * @date
 */
public class GetResource {

    public void load1() throws Exception {
        ClassLoader classLoader = GetResource.class.getClassLoader();
        //jdk.internal.loader.ClassLoaders$AppClassLoader@726f3b58
        System.out.println(classLoader);

        //用classLoader.getResource("")加载""资源，确保能加载到，如果getResource("a.txt")肯定加载不到
        //然后输出path，得到/E:/Java/MyResource/MyDemo/target/classes/
        System.out.println(classLoader.getResource("").getPath());

        //有了classLoader类加载的路径，根据a.txt在ClassPath的路径，就能推断出应该用cn/classloader/a.txt来加载a.txt文件
        URL url = classLoader.getResource("cn/classloader/a.txt");
        //有了URL对象，就能读到文件了
        InputStream inputStream = (InputStream) url.getContent();
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        System.out.println(new String(bytes));
    }

    public void load2() throws Exception {
        ClassLoader loader = GetResource.class.getClassLoader();
        System.out.println(loader);
        //loader类加载器为AppClassLoader，所以指向的是ClassPath路径
        System.out.println(loader.getResource("").getPath());
        /*
        //loader类加载器为AppClassLoader，"/"表示ClassPath路径的根目录，并没有这个目录，报错
        System.out.println(loader.getResource("/").getPath());
        */

        //GetResource.class.getResource("")指向的是GetResource.class所在目录，就是/E:/Java/MyResource/MyDemo/target/classes/cn/classloader/
        System.out.println(GetResource.class.getResource("").getPath());
        //GetResource.class.getResource("/")指向的是GetResource.class所在目录的根目录，就是ClassPath目录，就是/E:/Java/MyResource/MyDemo/target/classes/
        System.out.println(GetResource.class.getResource("/").getPath());

    }



    @Test
    public void test() {
        ClassLoader loader = GetResource.class.getClassLoader();
        System.out.println(loader.getResource("").getPath());
        //GetResource.class和this.getClass()是一致的
        System.out.println(GetResource.class.getResource("").getPath());
        System.out.println(GetResource.class.getResource("/").getPath());
        System.out.println(this.getClass().getResource("").getPath());
        System.out.println(this.getClass().getResource("/").getPath());

        System.out.println(System.getProperty("user.dir"));

        /*
        /E:/Java/MyResource/MyDemo/target/test-classes/
        /E:/Java/MyResource/MyDemo/target/classes/cn/classloader/
        /E:/Java/MyResource/MyDemo/target/test-classes/
        /E:/Java/MyResource/MyDemo/target/classes/cn/classloader/
        /E:/Java/MyResource/MyDemo/target/test-classes/
        E:\Java\MyResource\MyDemo
        */

    }

    public static void main(String... args)
            throws Exception {
        GetResource obj = new GetResource();
        obj.load1();
        obj.load2();
    }
}
