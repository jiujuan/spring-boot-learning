package com.springboot.chapter07mongodb.controller;

import com.springboot.chapter07mongodb.dao.UserRepository;
import com.springboot.chapter07mongodb.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.annotation.Order;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public User readUserById(@PathVariable("id") String id) {
        return userRepository.findOne(id);
    }

    /**
     * 根据一个或者多个属性查询单个结果
     * @param name
     * @return
     */
    @GetMapping(value="/name/{name}")
    public Optional<User> readUserByName(@PathVariable("name") String name) {
        User user = new User();
        user.setUserName(name);
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("age", "createTime");
        Example<User> example = Example.of(user, matcher);
        return userRepository.findOne(example);
    }

    /**
     * 根据一个或者多个属性分页查询
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/page/{pageNumber}/pagesize/{pageSize}/name/{name}")
    public Page<User> readUsersByPage(@PathVariable("pageNumber") int pageNumber,
                                      @PathVariable("pageSize") int pageSize,
                                      @PathVariable("name") String name) {
        User user = new User();
        user.setUserName(name);
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("age","createTime");
        Example<User> example = Example.of(user, matcher);
        if (pageNumber < 1) {
            pageNumber = 1;
        } else if (pageSize == 0) {
            pageSize = 20;
        }

        PageRequest pageable = PageRequest.of(pageNumber - 1, pageSize);
        return userRepository.findAll(example, pageable);
    }

    /**
     * 根据用户年龄升序排序
     * @return
     */
    @GetMapping
    public List<User> readUser() {
        Sort sort = Sort.by(Direction.ASC, "age");
        return userRepository.findAll(sort);
    }

    /**
     * 模糊查询带分页
     * @param pageNumber
     * @param pageSize
     * @param keyWords
     * @return
     */
    @GetMapping(value = "/page/{pageNumber}/pagesize/{pageSize}/keyword/{keyWords}")
    public Page<User> readUsersByKeywords(@PathVariable("pageNumber") int pageNumber,
                                          @PathVariable("pageSize") int pageSize,
                                          @PathVariable("keyWords") String keyWords) {
        if (keyWords == null) {
            keyWords = "";
        }
        if (pageNumber < 1) {
            pageNumber = 1;
        } else if (pageSize == 0) {
            pageSize = 20;
        }
        PageRequest pageable = PageRequest.of(pageNumber - 1, pageSize);
        return userRepository.findByUserNameLike(keyWords, pageable);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    public void removeUser(@PathVariable("id") String id) {
        userRepository.delete(id);
    }
}