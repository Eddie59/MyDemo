package com.em.mybatisgj.service.impl;

import com.em.mybatisgj.dao.UserMapper;
import com.em.mybatisgj.domain.User;
import com.em.mybatisgj.model.PageModel;
import com.em.mybatisgj.service.IUserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * UserService class
 *
 * @author Administrator
 * @date
 */
@Service
public class UserService extends BaseService<User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> page1(Map<String, Object> condition) {
        //limit 5,5   2表示从第二页开始
        PageHelper.startPage(2, 5);
        return userMapper.queryUserList(condition);
    }

    @Override
    public List<User> page2(Map<String, Object> condition) {
        //limit 2,5   2表示跳过前两条
        PageHelper.offsetPage(2, 5);
        return userMapper.queryUserList(condition);
    }

    @Override
    public List<User> page3(int pageNum, int pageSize) {
        return userMapper.findUsers(pageNum, pageSize);
    }

    @Override
    public List<User> page4(PageModel pageModel) {
        return userMapper.findUsersByModel(pageModel);
    }


}
