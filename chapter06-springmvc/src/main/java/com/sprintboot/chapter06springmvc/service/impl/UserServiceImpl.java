package com.sprintboot.chapter06springmvc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sprintboot.chapter06springmvc.dao.UserDao;
import com.sprintboot.chapter06springmvc.entity.UserEntity;
import com.sprintboot.chapter06springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public PageInfo<UserEntity> getUserList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserEntity> list = userDao.getAll();
        PageInfo<UserEntity> pageData = new PageInfo<UserEntity>(list);
        System.out.println("当前页："+pageData.getPageNum());
        System.out.println("页面大小："+pageData.getPageSize());
        System.out.println("总数："+pageData.getTotal());
        System.out.println("总页数："+pageData.getPages());

        return pageData;
    }
}