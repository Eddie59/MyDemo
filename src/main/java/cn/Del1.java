package cn;

import org.junit.Test;

import java.util.HashMap;

/**
 * Del1 class
 *
 * @author Administrator
 * @date
 */
public class Del1 {
    @Test
    public void test(){
        HashMap<String,String> hm=new HashMap<>();
        hm.put("a","aval");
        hm.put("b","bval");

        for (String key:hm.keySet()){
            System.out.println(key);
            System.out.println(hm.get(key));
        }

    }
}
