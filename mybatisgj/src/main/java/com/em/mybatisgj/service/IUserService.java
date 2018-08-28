package com.em.mybatisgj.service;

import com.em.mybatisgj.domain.User;
import com.em.mybatisgj.model.PageModel;

import java.util.List;
import java.util.Map;

public interface IUserService extends IService<User> {
    List<User> page1(Map<String,Object> condition);

    List<User> page2(Map<String,Object> condition);

    List<User> page3(int pageNum,int pageSize);

    List<User> page4(PageModel pageModel);
}
