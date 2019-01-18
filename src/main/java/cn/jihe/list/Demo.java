package cn.jihe.list;

import org.junit.Test;

import java.util.*;

/**
 * Demo class
 *
 * @author Administrator
 * @date
 */
public class Demo {
    @Test
    public void go() {
        List<Person> personList = Arrays.asList(new Person(1, "a", 1), new Person(2, "b", 2), new Person(3, "c", 3), new Person(4, "d", 4));

        Person p = new Person(1, "test", 1);
        if(personList.contains(p))
        {
            personList.remove(p);
        }
        personList.add(p);
        System.out.println(personList);
    }

    @Test
    public void set(){
        Set<Person> personSet=new HashSet<Person>();
    }

    @Test
    public void dic(){
        Map<String,Person> map=new HashMap<String,Person>();
        Person p1=new Person(1,"a",1);
        map.put("a",p1);
        Person p2=new Person(1,"b",1);
        map.put("a",p2);
        System.out.println(map);
    }

    @Test
    public void test(){
        ArrayList al=new ArrayList();

        ArrayList list=new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        System.out.println(list.size());
        list.ensureCapacity(100);
        System.out.println(list.size());
    }

}
