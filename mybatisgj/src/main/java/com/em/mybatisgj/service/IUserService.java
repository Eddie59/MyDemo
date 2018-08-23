package com.em.mybatisgj.service;

import com.em.mybatisgj.domain.User;

import java.util.List;
import java.util.Map;

public interface IUserService extends IService<User> {
    List<User> queryUserList(Map<String,Object> condition);
}
