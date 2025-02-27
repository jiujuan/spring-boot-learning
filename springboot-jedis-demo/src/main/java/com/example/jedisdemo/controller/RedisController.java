package com.example.jedisdemo.controller;

import com.example.jedisdemo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.resps.ScanResult;

import java.util.List;

@RestController
@RequestMapping("/redis")
public class RedisController {
    
    @Autowired
    private RedisService redisService;
    
    @PostMapping("/set")
    public ResponseEntity<String> setValue(@RequestParam String key, @RequestParam String value) {
        redisService.set(key, value);
        return ResponseEntity.ok("Value set successfully");
    }
    
    @GetMapping("/get/{key}")
    public ResponseEntity<String> getValue(@PathVariable String key) {
        String value = redisService.get(key);
        if (value != null) {
            return ResponseEntity.ok(value);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/delete/{key}")
    public ResponseEntity<String> deleteKey(@PathVariable String key) {
        redisService.delete(key);
        return ResponseEntity.ok("Key deleted successfully");
    }
    
    @PutMapping("/update")
    public ResponseEntity<String> updateValue(@RequestParam String key, @RequestParam String value) {
        boolean updated = redisService.update(key, value);
        if (updated) {
            return ResponseEntity.ok("Value updated successfully");
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<String>> searchKeys(@RequestParam String pattern) {
        List<String> keys = redisService.searchKeys(pattern);
        return ResponseEntity.ok(keys);
    }
    
    @GetMapping("/scan")
    public ResponseEntity<ScanResult<String>> scanKeys(
            @RequestParam(defaultValue = "0") String cursor,
            @RequestParam String pattern,
            @RequestParam(defaultValue = "10") int count) {
        ScanResult<String> result = redisService.scan(cursor, pattern, count);
        return ResponseEntity.ok(result);
    }
}