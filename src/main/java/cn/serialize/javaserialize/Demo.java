package cn.serialize.javaserialize;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Demo {
    public static void main(String... args) {
        Person per = new Person("xLi", 18);
        Teacher t1 = new Teacher("tseng", per);
        Teacher t2 = new Teacher("pti", per);
        try (FileOutputStream fileOutputStream = new FileOutputStream("test.txt");
             ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
        ) {
            outputStream.writeObject(per);
            outputStream.writeObject(t1);
            outputStream.writeObject(t2);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
