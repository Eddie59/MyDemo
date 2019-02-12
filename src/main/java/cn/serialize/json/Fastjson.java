package cn.serialize.json;

import cn.serialize.User;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class Fastjson {
    public static void main(String...args){
        User user = new Fastjson().getUser();
        String jsonString = JSON.toJSONString(user);
        System.out.println(jsonString);

        User user1 = JSON.parseObject(jsonString, User.class);
        System.out.println(user1);
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
