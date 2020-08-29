package com.springboot.chapter07mongodb.entity;

public class User {

    private String id;
    private String userName;
    private String password;
    private int age;
    private long createTime;

    public void setId(String id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public long getCreateTime() {
        return createTime;
    }
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}