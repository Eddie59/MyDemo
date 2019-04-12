package cn.jvm.fullgc;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MetaspaceDemo {

    public static void main(String[] args)
    {
        //模拟fullgc场景
        //持久代空间不足
        //String.intern() 1.6保存到永久代，1.7保存到堆上，1.8到Metaspace
        //当前应该设置Metaspace
        //-XX:+UseSerialGC -Xms20M -Xmx20M -Xmn10m -XX:SurvivorRatio=8 -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m -XX:+PrintGCDetails
        //
        List<String> list = new ArrayList<String>();
        int i = 0;
        while (true)
        {
            list.add(String.valueOf("ABCD:"  + i ++).intern());
        }
    }

   /* public static void main(String[] args)
    {
        //模拟fullgc场景
        //持久代空间不足
        //class 加载信息
        //需要cglib + asm (http://forge.ow2.org/projects/asm/)
        while (true)
        {
            Enhancer en = new Enhancer();
            en.setSuperclass(OOMObject.class);
            en.setUseCache(false);
            en.setCallback(new MethodInterceptor()
            {

                @Override
                public Object intercept(Object arg0, Method arg1, Object[] arg2,
                                        MethodProxy arg3) throws Throwable
                {
                    // TODO Auto-generated method stub
                    return null;
                }
            });
            en.create();
        }
    }
    static class OOMObject
    {

    }*/
}
