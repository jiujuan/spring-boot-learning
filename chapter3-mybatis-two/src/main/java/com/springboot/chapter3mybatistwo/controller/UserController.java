package com.springboot.chapter3mybatistwo.controller;

import com.springboot.chapter3mybatistwo.dao.UserDao;
import com.springboot.chapter3mybatistwo.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDao userDao;

    /**
     * 获取所有用户
     * @return
     */
    @RequestMapping("/getAll2")
    public List<UserEntity> getAll2(){
        return userDao.getAll2();
    }

    /**
     * 根据id获取用户
     * @return
     */
    @RequestMapping("/getOne2")
    public List<UserEntity> getOne2(Long id){
        return userDao.getOne2(id);
    }

    @RequestMapping("/insertUser2")
    public String insertUser2(UserEntity user) {
        userDao.insertUser2(user);
        return "insert success";
    }

    @RequestMapping("/updateUser2")
    public String updateUser2(UserEntity user) {
        userDao.updateUser2(user);
        return "update success";
    }

    @RequestMapping("/deleteUser2")
    public String deleteUser2(Long id) {
        userDao.deleteUser2(id);
        return "delete success";
    }
}