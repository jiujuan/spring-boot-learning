package com.springboot.chapter35;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private long id;
    private String name;
    private  Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}