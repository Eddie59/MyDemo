package com.em.mybatisgj;

import com.em.mybatisgj.domain.User;
import com.em.mybatisgj.service.impl.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisgjApplicationTests {

	@Autowired
	UserService userService;

	@Test
	public void addUser(){
		User oneUser=new User();
		oneUser.setSex("1");
		oneUser.setUsername("abcd");
		oneUser.setBirthday(new Date());

		int i= userService.save(oneUser);
		System.out.println(i);
	}

	@Test
	public void userTest() {
		Map<String,Object> condition=new HashMap<>();
		condition.put("username","riva");

		List<User> users= userService.queryUserList(condition);
		System.out.println(users);

		User oneUser=new User();
		oneUser.setSex("1");
		oneUser.setUsername("abcd");
		oneUser.setBirthday(new Date());
		int i= userService.save(oneUser);
		System.out.println(i);

	}



}
