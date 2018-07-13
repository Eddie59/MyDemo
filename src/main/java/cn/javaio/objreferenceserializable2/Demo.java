package cn.javaio.objreferenceserializable2;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Demo class
 *
 * @author Administrator
 * @date
 */
public class Demo {
    private final static String OUTPUT = "C:\\Users\\Administrator\\Desktop\\teacher.txt";

    @Test
    public void run() {
        Person student = new Person("eddie", 28);
        Teacher teacher1 = new Teacher("nick", student);
        Teacher teacher2 = new Teacher("susan", student);


        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(OUTPUT))) {
            //第一次序列化，序列化teacher1,student对象，把这两个对象都保存到磁盘，保存到磁盘中的对象都有个序列化编号
            //比如teacher1保存到磁盘上的编号是t1,student保存到磁盘上的编号为s1
            objectOutputStream.writeObject(teacher1);
            //序列化teacher2，保存到磁盘上，编号为t2，teacher2引用的student对象已经保存到磁盘上了，所以序列化teacher2时，引用的student对象不会序列化，只保存编号s1
            objectOutputStream.writeObject(teacher2);
            objectOutputStream.writeObject(student);
            objectOutputStream.writeObject(teacher2);
            objectOutputStream.close();
        } catch (Exception e) {
            System.out.println(e);
        }


        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(OUTPUT))) {
            Teacher t1 = (Teacher) objectInputStream.readObject();
            Teacher t2 = (Teacher) objectInputStream.readObject();
            Person p1 = (Person) objectInputStream.readObject();
            Teacher t2_1 = (Teacher) objectInputStream.readObject();

            Assert.assertEquals(t2_1, t2);
            Assert.assertEquals(t1.getStudent(), t2.getStudent());
            Assert.assertEquals(t1.getStudent(), p1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
