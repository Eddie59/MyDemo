package cn.jihe.collection;


import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashSet;

/**
 * CollectionDemo class
 *
 * @author Administrator
 * @date
 */
public class CollectionDemo {

    class Person
    {
        private String name;
        private int age;

        public Person(String name,int age)
        {
            this.age=age;
            this.name=name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Test
    public void run1()
    {

        Collection<Person> pers=new HashSet<>();
        for (int i=1;i<=5;i++)
        {
            pers.add(new Person("name"+i,i));
        }

        for (Person per :pers)
        {
            System.out.println(per.getName());
        }

//        Collection<String> books=new HashSet();
//        books.add("a1");
//        books.add("a2");
//        books.add("a3");
//        books.add("a4");
//        books.add("a5");
//
//        Iterator<String> iterator= books.iterator();
//        while (iterator.hasNext())
//        {
//            String book=iterator.next();
//            iterator.remove();
//        }
//        System.out.println(books);
    }

    @Test
    public void run2()
    {
        Collection books = new HashSet();
        books.add(new String("轻量级Java EE企业应用实战"));
        books.add(new String("疯狂Java讲义"));
        books.add(new String("疯狂Android讲义"));
        for (Object obj : books)
        {
            // 此处的book变量也不是集合元素本身
            String book = (String)obj;
            System.out.println(book);
            if (book.equals("疯狂Android讲义"))
            {
                // 下面代码会引发ConcurrentModificationException异常
                books.remove(book);
            }
        }
        System.out.println(books);
    }

}
