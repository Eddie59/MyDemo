package cn.jihe.map;

import org.junit.Test;
import java.util.Hashtable;

public class HashTableDemo {

    @Test
    public void run(){
        Hashtable hashtable=new Hashtable();
        hashtable.put("age","28");
        hashtable.put("name","eddie");
        System.out.println(hashtable.keySet());
        System.out.println(hashtable.get("name"));
    }


}
