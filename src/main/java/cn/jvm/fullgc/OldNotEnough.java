package cn.jvm.fullgc;

public class OldNotEnough {
    /**
     * 模拟老年代不足
     * 场景1 使用System.gc
     * -XX:+UseSerialGC -Xms200M -Xmx200M -Xmn32m -XX:SurvivorRatio=8 -XX:+PrintGCDetails
     * heap 200M 新生代32M 老年代大小: 200 -32m = 168M
     * -XX:SurvivorRatio=8 eden/survivor=8  survivorA、B为4M 所以eden 24M   survivorA、B为4M
     *
     */
    public static void main(String...args){
        //模拟fullgc场景
        //老年代空间不足
        //按照上面的参数推算:老年代大小: 200 -32m = 168M
        byte [] MAXOBJ = new byte [1024 * 1024 * 100]; // 100M

        byte [] MAXOBJ2 = new byte [1024 * 1024 * 70]; // 60M
        MAXOBJ = null;

        byte [] MAXOBJ3 = new byte [1024 * 1024 * 100]; // 60M

    }
}
