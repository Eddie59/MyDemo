package com.em.mybatisgj.controller;

import com.alibaba.fastjson.JSON;
import com.em.mybatisgj.domain.User;
import com.em.mybatisgj.service.impl.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * UserController class
 *
 * @author Administrator
 * @date
 */
@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

    @Autowired
    UserService userService;

    @RequestMapping("/test")
    public void test(){

        Map<String,Object> condition=new HashMap<>();
        condition.put("username","a");
        List<User> userList= userService.queryUserList(condition);
        System.out.println(JSON.toJSONString(userList));
    }
}
