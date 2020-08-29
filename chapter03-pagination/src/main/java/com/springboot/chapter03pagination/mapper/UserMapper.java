package com.springboot.chapter03pagination.mapper;

import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;
import com.springboot.chapter03pagination.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名统计（TODO 假设它是一个很复杂的SQL）
     *
     * @param username 用户名
     * @return 统计结果
     */
    int countByUsername(String username);
}