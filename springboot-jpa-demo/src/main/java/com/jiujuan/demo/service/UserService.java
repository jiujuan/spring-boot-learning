package com.jiujuan.demo.service;

import com.jiujuan.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User createUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    User getUserById(Long id);
    User getUserByUsername(String username);
    Page<User> getAllUsers(Pageable pageable);
}