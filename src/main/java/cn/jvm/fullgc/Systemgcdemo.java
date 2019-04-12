package cn.jvm.fullgc;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Systemgcdemo {

    /**
     * 模拟fullgc场景
     * 场景1 使用System.gc
     * -XX:+UseSerialGC -Xms30M -Xmx30M -Xmn10m -XX:SurvivorRatio=8 -XX:+PrintGCDetails
     * Eden 8M   from 1M    to 1M    young 10M
     * Tenured 20M
     *
     */
    public static void main(String...args){
        Object obj = new Object();
        obj = null;
        System.gc();

        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(new Date());
            }
        },1000,2000);

      /*  //模拟fullgc场景
        //场景1 使用System.gc
        List<Object> l = new ArrayList<Object>();
        for (int i =0; i< 100;i++)
        {
            l.add(new byte[1024*1024]);
            if (i % 10 ==0)
            {
                System.gc();
            }
        }*/
    }


}
