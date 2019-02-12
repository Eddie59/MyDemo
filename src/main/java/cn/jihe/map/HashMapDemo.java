package cn.jihe.map;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * HashMapDemo class
 *
 * @author Administrator
 * @date
 */
public class HashMapDemo {
    public static void main(String...args)
    {

        Hashtable<String,String> hashtable=new Hashtable<String,String>();
        hashtable.put("a","aVal");
        hashtable.put("b","bVal");
        hashtable.put("c","cVal");
        hashtable.put("d","dVal");

        HashMap hashMap=new HashMap();
        hashMap.put("a","a1");
        hashMap.put("b","b1");

        System.out.println(hashMap.size());

        Object obj=new Object();


    }
}
