package com.em.mybatisgj;

import com.em.mybatisgj.domain.User;
import com.em.mybatisgj.model.PageModel;
import com.em.mybatisgj.service.impl.UserService;
import com.github.pagehelper.PageInfo;
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

	/**
	 * BaseService和UserService中的Mapper是同一个对象
	 */
	@Test
	public void userTest() {
		Map<String,Object> condition=new HashMap<>();
		condition.put("username","a");
		List<User> users= userService.page1(condition);
		System.out.println(users);

		User oneUser=new User();
		oneUser.setSex("1");
		oneUser.setUsername("abcd");
		oneUser.setBirthday(new Date());
		int i= userService.save(oneUser);
		System.out.println(i);
	}

	@Test
	public void testPage1(){
		Map<String,Object> condition=new HashMap<>();
		condition.put("username","a");
		List<User> users= userService.page1(condition);

		PageInfo<User> userPage=new PageInfo<>(users);
		System.out.println(userPage);
	}

	@Test
	public void testPage2(){
		Map<String,Object> condition=new HashMap<>();
		condition.put("username","a");
		List<User> users= userService.page2(condition);
		PageInfo<User> userPage=new PageInfo<>(users);
		System.out.println(userPage);
	}

	/**
	 * 需配置 <property name="supportMethodsArguments" value="true"/>
	 */
	@Test
	public void testPage3(){
		List<User> users= userService.page3(2,5);
		PageInfo<User> userPage=new PageInfo<>(users);
		System.out.println(userPage);
	}

	/**
	 * 需配置 <property name="supportMethodsArguments" value="true"/>
	 */
	@Test
	public void testPage4(){
		PageModel pageModel=new PageModel();
		pageModel.setPageNum(2);
		pageModel.setPageSize(5);

		List<User> users= userService.page4(pageModel);
		PageInfo<User> userPage=new PageInfo<>(users);
		System.out.println(userPage);
	}


}
