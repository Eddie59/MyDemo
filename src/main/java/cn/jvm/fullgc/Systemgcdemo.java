package cn.jvm.fullgc;

import java.util.ArrayList;
import java.util.List;

public class Systemgcdemo {

    /**
     * 模拟fullgc场景
     * 场景1 使用System.gc
     * -XX:+UseSerialGC -Xms200M -Xmx200M -Xmn32m -XX:SurvivorRatio=8 -XX:+PrintGCDetails
     */
    public static void main(String...args){
        //模拟fullgc场景
        //场景1 使用System.gc
        List<Object> l = new ArrayList<Object>();
        for (int i =0; i< 100;i++)
        {
            l.add(new byte[1024*1024]);
            if (i % 10 ==0)
            {
                System.gc();
            }
        }
    }


}
