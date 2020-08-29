package com.springboot.chapter3mybatistwo.dao;

import com.springboot.chapter3mybatistwo.entity.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
public interface UserDao {
    //mapper.xml方式

    /**
     * 获取所有用户
     * @return
     */
    List<UserEntity> getAll();

    /**
     * 根据id获取用户
     *
     * @param id
     * @return
     */
    List<UserEntity> getOne(Long id);

    /**
     * 新增用户
     *
     * @param user
     */
    void insertUser(UserEntity user);

    /**
     * 修改用户
     * @param user
     */
    void updateUser(UserEntity user);

    /**
     * 删除用户
     * @param id
     */
    void deleteUser(Long id);

    // 注解方式

    /**
     * 获取所有用户
     * @return
     */
    @Select("select * from t_user")
    @Results({
        @Result(property = "userName", column = "user_name"),
        @Result(property = "userSex", column = "user_sex")
    })
    List<UserEntity> getAll2();

    /**
     * 获取所有用户
     * @return
     */
    @Select("select * from t_user where id=#{id}")
    @Results({
        @Result(property = "userName", column = "user_name"),
        @Result(property = "userSex", column = "user_sex")
    })
    List<UserEntity> getOne2(Long id);

    /**
     * 新增用户
     *
     * @param user
     */
    @Insert("insert into t_user (user_name, user_sex) values (#{userName}, #{userSex})")
    void insertUser2(UserEntity user);

    /**
     * 修改用户
     * @param user
     */
    @Update("update t_user set user_name=#{userName},user_sex=#{userSex} where id=#{id}")
    void updateUser2(UserEntity user);

    /**
     * 删除用户
     * @param id
     */
    @Delete("delete from t_user where id=#{id}")
    void deleteUser2(Long id);
}