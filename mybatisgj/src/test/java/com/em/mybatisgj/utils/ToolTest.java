package com.em.mybatisgj.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ToolTest {


    @Test
    public void nowInt() throws Exception {
        int nowInt = Tool.nowInt();
        System.out.println(nowInt);
    }

    @Test
    public void dateStrToInt() throws Exception {

    }

    @Test
    public void dateToInt() throws Exception {

    }

    @Test
    public void dateToInth() throws Exception {
    }

    @Test
    public void calDate() throws Exception {
    }

    @Test
    public void convertFullcode() throws Exception {
    }

    @Test
    public void fullcodeToMarketID() throws Exception {
    }

    @Test
    public void delLastChar() throws Exception {
    }

}