## Demo 实现的功能：

1. 用户的增删改查功能
2. 分页显示
3. 使用 Element Plus 的表格、表单、对话框等组件
4. 前后端完全分离的架构
5. RESTful API 设计
6. JPA 实现数据库操作


## 运行 demo

- 后端启动：

cd 到 springboot-jpa-demo 目录，运行命令：

```shell
mvn spring-boot:run
```

- 前端启动：

cd springboot-jpa-demo\vue-admin 目录，运行命令：
```shell
npm install

npm run dev
```

然后在浏览器上访问 http://localhost:3000 即可看到用户管理界面。

## SQL 建表

在 tests 数据库下建 users 表

```
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

