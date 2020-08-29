package com.springboot.chapter07mongodb.dao;

import com.springboot.chapter07mongodb.entity.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepository extends MongoRepository<User, String> {

    public Page<User> findByUserNameLike(String userName, Pageable pageable);
    User findOne(String id);
    void delete(String id);
}