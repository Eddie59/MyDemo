package com.em.mybatisgj.dao;

import com.em.mybatisgj.common.MyMapper;
import com.em.mybatisgj.domain.User;

import java.util.List;
import java.util.Map;

public interface UserMapper extends MyMapper<User> {
    List<User> queryUserList(Map<String,Object> condition);
}