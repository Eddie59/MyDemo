package com.em.mybatisgj.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * MyMapper class
 *
 * @author Administrator
 * @date
 */
public interface MyMapper<T> extends Mapper<T>,MySqlMapper<T> {
}
