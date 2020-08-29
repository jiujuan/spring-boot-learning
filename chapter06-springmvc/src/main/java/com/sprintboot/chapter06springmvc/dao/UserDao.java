package com.sprintboot.chapter06springmvc.dao;

import java.util.List;
import com.sprintboot.chapter06springmvc.entity.UserEntity;

public interface UserDao {
    List<UserEntity> getAll();
}