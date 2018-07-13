package cn.javaio.objreferenceserializable;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Demo class
 *
 * @author Administrator
 * @date
 */
public class Demo {
    private final static String OUTPUT="C:\\Users\\Administrator\\Desktop\\teacher.txt";


    public static void main(String... args) {
        Person student=new Person("eddie",28);
        Teacher teacher=new Teacher("nick",student);

        try(ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(OUTPUT))){
            objectOutputStream.writeObject(teacher);
            objectOutputStream.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }
}
