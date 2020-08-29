package com.springboot.chapter3mybatistwo.dao;

import com.springboot.chapter3mybatistwo.entity.UserEntity;
import java.util.List;

public interface UserDaoXML {
    //mapper.xml 方式

    List<UserEntity> getAll();

    List<UserEntity> getOne(Long id);

    void insertUser(UserEntity user);

    void updateUser(UserEntity user);

    void deleteUser(Long id);
}
