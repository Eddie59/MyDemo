package cn.jihe.collection;

import java.util.Iterator;

/**
 * MyString class
 *
 * @author Administrator
 * @date
 */
public class MyString implements Iterable<Character> {

    //字符串的长度
    private int length=0;
    //字符串
    private String inner=null;

    public MyString(String s)
    {
        this.inner=s;
        this.length=s.length();
    }

    /**
     *
     * @return 返回迭代器
     */
    @Override
    public Iterator<Character> iterator() {

        /**
         *  内部类，实现自己的迭代器
         */
        class myiter implements Iterator<Character>
        {
            private int cur=0;

            @Override
            public boolean hasNext() {
                return cur != length;
            }

            @Override
            public Character next() {
                //从这里就能看出，调用迭代器的next()方法，返回的是元素的copy值，不是元素本身
                Character c=inner.charAt(cur);
                cur++;
                return c;
            }

            @Override
            public void remove() {
            }
        }

        return new myiter();
    }
}
