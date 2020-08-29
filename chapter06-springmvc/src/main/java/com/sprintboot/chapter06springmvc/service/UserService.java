package com.sprintboot.chapter06springmvc.service;

import com.github.pagehelper.PageInfo;
import com.sprintboot.chapter06springmvc.entity.UserEntity;


public interface UserService {

    PageInfo<UserEntity> getUserList(int pageNum, int pageSize);

}