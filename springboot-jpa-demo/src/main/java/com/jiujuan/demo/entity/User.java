package com.jiujuan.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String email;

    private Integer age;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @PrePersist
    public void prePersist() {
        createdTime = LocalDateTime.now();
    }
}