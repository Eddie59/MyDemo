package cn.serialize.protobuff;


import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    @Test
    public void Run() throws java.io.IOException{
        byte[] bytes=null;
        Person person=this.getPerson();
        Codec<Person> personCodec= ProtobufProxy.create(Person.class,false);
        bytes= personCodec.encode(person);
        System.out.println(bytes);

        Person person1= personCodec.decode(bytes);
        System.out.println(person1);

    }

    public Person getPerson() {
        Person u = new Person();
        List<Person> friends = new ArrayList<Person>();
        Person f1 = new Person();
        f1.setUserName("李四");
        f1.setPassWord("123456");
        f1.setUserInfo("李四是一个很牛逼的人");

        Person f2 = new Person();
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
