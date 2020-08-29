package com.springboot.chapter02configproperties.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:my2.properties")
@ConfigurationProperties(prefix = "my2")
public class MyProperties2 {

    private int age;
    private String name;
    private String email;

    public void setAge(Integer age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "MyProperties2{" +
            "age=" + age +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            '}';
    }
}