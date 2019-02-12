package cn.serialize.javaserialize;

import cn.serialize.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JavaSerialize {
    public static void main(String... args) throws IOException, ClassNotFoundException {
        new JavaSerialize().start();
    }

    public void start() throws IOException, ClassNotFoundException {
        User u = this.getUser();

        Long t1 = System.currentTimeMillis();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream obj = new ObjectOutputStream(out);
        for (int i = 0; i < 10; i++) {
            obj.writeObject(u);
        }
        System.out.println("java serialize: " + (System.currentTimeMillis() - t1) + "ms; 总大小：" + out.toByteArray().length);

        Long t2 = System.currentTimeMillis();
        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new java.io.ByteArrayInputStream(out.toByteArray())));
        User user = (User) ois.readObject();
        System.out.println("java deserialize: " + (System.currentTimeMillis() - t2) + "ms; User: " + user);
    }

    public User getUser() {
        User u = new User();
        List<User> friends = new ArrayList<User>();
        User f1 = new User();
        f1.setUserName("李四");
        f1.setPassWord("123456");
        f1.setUserInfo("李四是一个很牛逼的人");

        User f2 = new User();
        f2.setUserName("王五");
        f2.setPassWord("123456");
        f2.setUserInfo("王五是一个很牛逼的人");

        friends.add(f1);
        friends.add(f2);

        u.setUserName("张三");
        u.setPassWord("123456");
        u.setUserInfo("张三是一个很牛逼的人");
        u.setFriends(friends);
        return u;
    }
}

