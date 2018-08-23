package com.em.mybatisgj.service.impl;

import com.em.mybatisgj.dao.UserMapper;
import com.em.mybatisgj.domain.User;
import com.em.mybatisgj.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * UserService class
 *
 * @author Administrator
 * @date
 */
public class UserService extends BaseService<User> implements IUserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> queryUserList(Map<String, Object> condition) {
        return userMapper.queryUserList(condition);
    }
}
