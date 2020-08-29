package com.sprintboot.chapter06springmvc.controller;

import com.github.pagehelper.PageInfo;
import com.sprintboot.chapter06springmvc.entity.UserEntity;
import com.sprintboot.chapter06springmvc.service.UserService;
import org.springframework.web.servlet.ModelAndView;

public class UserController {

    private UserService userService;

    public ModelAndView showUserList(int pageNum, int pageSize) {
        PageInfo<UserEntity> pageInfo = userService.getUserList(pageNum, pageSize);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("pageInfo", pageInfo);
        return modelAndView;
    }
}