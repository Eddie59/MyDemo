package cn.jihe.map;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * HashMapDemo class
 *
 * @author Administrator
 * @date
 */
public class HashMapDemo {

    public static void main(String... args) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            hashMap.put("key" + i, i);
        }
        System.out.println(hashMap);

    }
}
