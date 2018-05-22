package em.jihe.collection;

import java.util.Iterator;

/**
 * MyStringDemo class
 *
 * @author Administrator
 * @date
 */
public class MyStringDemo {
    public static void main(String... args) {
        MyString myString = new MyString("abcd");

        Iterator<Character> iterator= myString.iterator();
        while (iterator.hasNext())
        {
           Character cha= iterator.next();
           System.out.println(cha);
        }

        //有了迭代器，就可以使用foreach了
        for (Character cha : myString) {
            System.out.println(cha);
        }

    }
}
