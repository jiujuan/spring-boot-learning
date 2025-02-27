package com.jiujuan.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jiujuan.demo.entity.User;
import com.jiujuan.demo.utils.RedisUtil;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/string")
    public String setString(@RequestParam String key, @RequestParam String value) {
        redisUtil.set(key, value);
        return "set success";
    }

    @GetMapping("/string/{key}")
    public Object getString(@PathVariable String key) {
        return redisUtil.get(key);
    }

    @PostMapping("/user")
    public String setUser(@RequestBody User user) {
        redisUtil.set("user:" + user.getId(), user);
        return "set success";
    }

    @GetMapping("/user/{id}")
    public Object getUser(@PathVariable String id) {
        return redisUtil.get("user:" + id);
    }

    // 左边添加值
    @PostMapping("/list")
    public String addToList(@RequestParam String key, @RequestParam String value) {
        redisUtil.leftPush(key, value);
        return "success";
    }

    @GetMapping("/list/range/{key}")
    public List<Object> getListRange(@PathVariable String key) {
        return redisUtil.range(key, 0, -1);
    }

    @PostMapping("/set")
    public String addToSet(@RequestParam String key, @RequestParam String value) {
        redisUtil.sAdd(key, value);
        return "success";
    }

    @GetMapping("/set/{key}")
    public Set<Object> getSet(@PathVariable String key) {
        return redisUtil.sMembers(key);
    }

    @PostMapping("/zset")
    public String addToZSet(@RequestParam String key, @RequestParam String value, @RequestParam double score) {
        redisUtil.zAdd(key, value, score);
        return "success";
    }

    @GetMapping("/zset/{key}")
    public Set<Object> getZSet(@PathVariable String key, @RequestParam double min, @RequestParam double max) {
        return redisUtil.zRangeByScore(key, min, max);
    }
}