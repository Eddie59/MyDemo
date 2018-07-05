package cn.classloader;

/**
 * LoaderTest class
 *
 * @author Administrator
 * @date
 */
public class LoaderTest {
    public static void main(String...args) throws Exception
    {
        //jdk.internal.loader.ClassLoaders$AppClassLoader@726f3b58
        //AppClassLoader负责加载ClassPath目录下的类，当前类LoaderTest肯定是在ClassPath目录下
        ClassLoader loader=LoaderTest.class.getClassLoader();
        System.out.println(loader);

        //使用ClassLoader.loadClass()来加载类，不会执行初始化块
        loader.loadClass("cn.classloader.Test2");

        //使用Class.forName()来加载类，默认会执行初始化块
        Class.forName("cn.classloader.Test2");

        //使用Class.forName()来加载类，并指定ClassLoader，初始化时不执行静态块
        Class.forName("cn.classloader.Test2",false,loader);


    }
}
