SpringBoot3 + jedis 简单代码例子

## SpringBoot3 + jedis 的简单示例项目有以下功能

- 1. 设置键值对（SET）
- 2. 获取值（GET）
- 3. 删除键（DELETE）
- 4. 更新键值对（UPDATE）
- 5. 搜索键（SEARCH）
- 6. 分页扫描键（SCAN）

你可以使用以下 API 端点测试这些功能：

- 设置值：POST /redis/set?key=mykey&value=myvalue
- 获取值：GET /redis/get/mykey
- 删除键：DELETE /redis/delete/mykey
- 更新值：PUT /redis/update?key=mykey&value=newvalue
- 搜索键：GET /redis/search?pattern=my*
- 扫描键：GET /redis/scan?cursor=0&pattern=my*&count=10
  

> 本地环境已经安装并运行了 Redis 服务器，默认端口为 6379，默认用户设置密码为 foobared



比如你启动SpringBoot项目端口为 8080，那么设置值可以使用：

> http://localhost:8080/redis/set?key=mykey&value=myvalue

## 测试 API 功能：

我用的 HOPPSCOTCH 作为 API 测试工具。

- **设置值测试**：POST /redis/set?key=mykey&value=myvalue

![image-20250227214419198](https://github.com/jiujuan/spring-boot-learning/blob/master/springboot-jedis-demo/images/image-20250227214419198.png)

- **获取值测试**：获取值：GET /redis/get/mykey

![image-20250227214621742](https://github.com/jiujuan/spring-boot-learning/blob/master/springboot-jedis-demo/images/image-20250227214621742.png)

- **更新值测试**：PUT /redis/update?key=mykey&value=newvalue

如果我用 GET 请求，返回是报错

```json
{
  "status": 405,
  "error": "Method Not Allowed",
  "path": "/redis/update"
}
```

用 PUT 请求正常返回值

![image-20250227215131261](https://github.com/jiujuan/spring-boot-learning/blob/master/springboot-jedis-demo/images/image-20250227215131261.png)

- **搜索键**：GET /redis/search?pattern=my*

先多设置一个键值 `mykey2:myvalue2`：http://localhost:8080/redis/set?key=mykey2&value=myvalue2 , 然后在搜索键

![image-20250227215652308](https://github.com/jiujuan/spring-boot-learning/blob/master/springboot-jedis-demo/images/image-20250227215652308.png)

 

- **扫描键**：GET /redis/scan?cursor=0&pattern=my*&count=10

![image-20250227215759363](https://github.com/jiujuan/spring-boot-learning/blob/master/springboot-jedis-demo/images/image-20250227215759363.png)
