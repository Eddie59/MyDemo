package com.em.mybatisgj.dao;

import com.em.mybatisgj.common.MyMapper;
import com.em.mybatisgj.domain.User;
import com.em.mybatisgj.model.PageModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface UserMapper extends MyMapper<User> {
    List<User> queryUserList(Map<String, Object> condition);

    List<User> findUsers(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    List<User> findUsersByModel(PageModel pageModel);

}