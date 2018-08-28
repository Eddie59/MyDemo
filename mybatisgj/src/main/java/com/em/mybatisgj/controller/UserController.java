package com.em.mybatisgj.controller;

import com.alibaba.fastjson.JSON;
import com.em.mybatisgj.domain.User;
import com.em.mybatisgj.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public String test() {
        Map<String, Object> condition = new HashMap<>();
        condition.put("username", "a");
        List<User> userList = userService.page1(condition);
        System.out.println(JSON.toJSONString(userList));
        return "abc";
    }

    @ResponseBody
    @RequestMapping("/find/{id}")
    public User selectById(@PathVariable("id") int id) {
        return userService.selectByKey(id);
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public int add(@RequestBody User user) {
        return userService.save(user);
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public int update(User user) {
        return userService.updateNotNull(user);
    }

    @ResponseBody
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public int delete(@PathVariable("id") int id) {
        return userService.delete(id);
    }
}
