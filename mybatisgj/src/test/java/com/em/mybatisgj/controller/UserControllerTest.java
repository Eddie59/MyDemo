package com.em.mybatisgj.controller;

import com.em.mybatisgj.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    private MockHttpSession session;

    @Before
    public void setupMockMvc() {
        //初始化mockMvc
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        session = new MockHttpSession();

        //因为有权限认证，所以要加这个
        User user = new User();
        user.setUsername("root");
        user.setSex("male");
        session.setAttribute("user", user);
    }

    @Test
    public void test1() throws Exception {
    }

    @Test
    public void selectById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/find/1")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("eddiesong"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sex").value("m"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional //单元测试回滚，不会产生垃圾数据
    public void add() throws Exception {
        String json = "{\"username\":\"haha\",\"sex\":\"1\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/user/add")
                //发送端发送的数据格式是application/json;charset=UTF-8
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                //客户端希望接受的数据类型为application/json;charset=UTF-8
                .accept(MediaType.APPLICATION_JSON_UTF8)
                //发送的内容
                .content(json.getBytes())
                //注入一个session，这样才可以通过拦截器
                .session(session))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void update() throws Exception {

    }

    @Test
    @Transactional
    public void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/delete/47")
                .session(session))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}