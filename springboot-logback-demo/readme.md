标题：SpringBoot3 中日志框架 Logback 使用说明和实战

## SpringBoot3 日志管理系统

SpringBoot3 中有一个日志管理系统，它支持和管理各种日志框架，提供了多种不同的配置，包括默认配置、自定义日志配置，还可以使用不同的日志框架。

SpringBoot3 中日志管理系统简单架构实现是：**日志门面（日志抽象层） + 实现框架**。

它通过日志门面模式统一抽象出日志管理，提供一套统一的接口，对应用程序中日志的输出进行统一管理。然后通过具体的日志框架来实现抽象层，进行具体的日志操作。

使用日志门面模式可以在不改变应用程序情况下，在日志框架和应用程序之间架设一座桥梁（中间层隔离），无论使用什么日志实现框架，用的都是统一的门面模式的API。这样就可以灵活的切换不同的日志实现框架，更好的适应不同的需求。

内置的 **门面（日志抽象层）**：

- Commons Logging（Apache Commons Logging）
- SLF4J
- jboss-logging

具体的**日志框架实现**：

- Logback

- Log4j
- JUL（Java自带的java.util.logging）
- Log4j2

SpringBoot3 中默认使用 SLF4J + Logback 作为日志门面和具体分框架实现。

## Logback 日志框架

### logback和SLF4J 简介

在 SpringBoot3 中，默认记录日志使用的日志实现框架是 Logback，日志抽象层（门面）是 SLF4J。这2个都是大神 Ceki Gülcü 实现的。

[SLF4J](https://www.slf4j.org/) 是 Ceki Gülcü 写的一个新的日志门面，用来替换 JCL（(Jakarta Commons Logging)），SLF4J 比较简洁，性能更好。

[Logback](https://logback.qos.ch/)  是 Ceki Gülcü 对 log4j 进行了改造升级，实现了 SLF4J 日志门面的一个日志框架，性能更好，功能更丰富。

Logback 由下面 3 个模块组成：

- logback-core：这个是核心模块，为其它 2 个模块奠定了基础。
- logback-classic：实现了 SLF4J API，方便日志实现框架的切换。
- logback-access：此模块与 Tomcat 和 Jetty 等 Servlet 容器集成，提供 HTTP 访问日志功能。

> Logback 框架项目地址：https://logback.qos.ch/

> SLF4J 项目地址：https://www.slf4j.org/

SLF4J接口及其各种适配器如下图：

![image-20250225192852386](.\images\image-20250225192852386.png)

（from：https://www.slf4j.org/manual.html）

### 添加依赖 引入 logback

要使用 logback，需要添加依赖项  spring-boot-starter-logging。对于网络应用程序，添加 spring-boot-starter-web 即可，它包含了 spring-boot-starter-logging。

如果使用 Maven：

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

此依赖 spring-boot-starter-logging 在 spring-boot-starter 中也包含了，所以如下配置引入也可以：

```xml
<dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter</artifactId>
</dependency>
```

### logback配置文件名称设置

SpringBoot 有一个日志系统的抽象，如果 Logback 可用，它将会是首选。

如果想按照自己的要求输出日志格式，需要设置或修改配置文件名为 `logback-spring.xml` 或 `logback.xml`，把这个文件放置到 `src/main/resources` 目录下，就可以定制化日志配置。建议用 `logback-spring.xml` 这样更容易被 Spring 管理。

> SpringBoot 的默认 logback 配置文件可在依赖项中找到。路径为：
>
> `org/springframework/boot/logging/logback/defaults.xml`

### 日志级别

日志级别由低到高依次为：

> ALL < TRACE < DEBUG < INFO < WARN < ERROR < FATAL < OFF

设置了日志级别后，只会打印指定级别及以上级别的日志。

**日志级别说明**：

- - **ALL**：最低等级，打印所有日志记录

  - **TRACE**：最详细的日志信息，通常用于诊断问题和追踪代码执行流程。在生产环境中，都会关闭 TRACE 级别，避免影响系统性能

  - **DEBUG**：开发调试细节日志，用于调试应用程序。一般也是建议关闭

  - **INFO**：普通的信息日志，通常用于记录操作结果或执行状态。比如：用户登录成功

  - **WARN**：警告但不是错误的信息日志，比如：版本过时，系统资源紧张

  - **ERROR**：错误信息日志，通常记录系统运行时发生的错误。比：如出现各种异常，数据库连接失败

  - **FATAL**：致命错误日志，通常记录系统崩溃或无法恢复的错误。比如： `jvm` 系统崩溃

  - **OFF**：最高等级，用来关闭所有日志记录

如果不指定级别的所有类，都使用 root 指定的级别作为默认级别。

SpringBoot3 中日志记录的 **默认级别是** `INFO`。

**日志级别设置：**

在 application.yml 或 application.properties 文件中可以设置：

```yaml
logging:
  level:
    root: WARN
    org.springframework.web: ERROR
    com.springbootdemo: INFO
```

> 上面的 root 级别设置为 WARN，Spring web 包日志级别设置为 ERROR，而代码包 com.springbootdemo 设置为 INFO。

### 日志配置说明

`logback-spring.xml` 里的配置项解释说明。

#### 1、基本配置项详细说明

```xml
<?xml version='1.0' encoding='UTF-8'?>

<configuration>
    <!-- 直接定义属性 -->
    <property name="" value=""/>
    
    <!-- 通过配置文件定义属性 -->
    <springProperty name="" source=""/>
    
    <!-- 定义并描述一个日志的输出属性 -->
    <appender name="" class="">

    </appender>

    <!-- 基础的日志输出，必选节点 -->
    <root level="">
        <appender-ref ref=""/>
    </root>
    
    <!-- 创建一个具体的日志输出，可选节点 -->
    <!-- name 指定包名 -->
    <logger name="" level="" additivity="">
    	<!-- ref 值指向 appender 的name -->
        <appender-ref ref=""/>
    </logger>
    
</configuration>
```

- **configuration**：配置的根节点，它包含多个子节点 appender、logger、root等。它的属性如下：

  > `scan` - 当此属性设置为 true 时，配置文件如果发生变化，将会被重新加载，默认为 ture。
  >
  > `scanPeriod`：设置监测配置文件是否有修改的时间间隔，如果没有给出时间单位，默认单位是毫秒。当 scan 为 true 时，此属性生效。默认的时间间隔为 1 分钟。
  >
  > `debug`：当此属性设置为 true 时，将打印出 logback 内部日志信息，实时查看 logback 运行状态。默认值为 false。

  ```xml
  <configuration scan="true" scanPeriod="30 seconds" debug="false">  
        <!-- 其他配置略-->  
  </configuration>  
  ```

  

- **property和springProperty**：这2个节点都可以设置全局变量。property 可以直接设置，springProperty 则要配合配置文件。

  ```xml
  <!-- 设置了一个名为 logFile 的变量，后续通过 ${logFile} 的方式就引用到了其值 logs/errinfo -->
  <property name="logFile" value="logs/errinfo"/>
  ```

  springProperty 则要配合配置文件，例如：

  ```xml
  <springProperty name="logFile" source="log.file"/>
  ```

  设置了一个名为 logFile 的变量，但没有直接给 logFile 赋值，而是通过 source 指向了配置文件的路径，配置文件设置是这样的：

  ```java
  log:
    file: logs/errinfo
  ```

  

- **root**：必选节点，定义了根日志记录器，根logger，用来指定基础的日志输出级别、文件类型等，并指定 \<appender\>

  ```xml
  <root level="debug">
  	<appender-ref ref="console" />
  	<appender-ref ref="file" />
  </root>
  ```

  

- **appender**：定义日志关键属性，比如格式化日志输出，设置日志存储类型、位置、滚动（切割）规则等属性，每一个 appender 都需要有一个唯一的名称和一个类定义。appender 有三种类型：

  - ConsoleAppender：控制台日志
  - FileAppender：文件日志
  - RollingFileAppender：滚动日志

  

  **1、ConsoleAppender** 将日志输出到控制台，本地调试时使用，它的基本配置：

  ```xml
  <appender name="console" class="ch.qos.logback.core.ConsoleAppender">
  	<encoder>
          <pattern>%d [%thread] %-5level %logger{50} -[%file:%line]- %msg%n</pattern>
          <charset>UTF-8</charset>
      </encoder>
  </appender>
  ```

  它的 2 个属性 name 和 class：

  - name：appender 节点名，在后面被 logger 节点引用。注意⚠️：一个 logback 配置文件中不能有重复的 appender name。
  - class：使用那种日志输出策略，有 ConsoleAppender - 控制台日志，FileAppender - 文件日志，RollingFileAppender - 滚动日志

  

  **2、FileAppender** 将日志输出到文件里。它的基本配置：

  ```xml
  <appender name="FILE" class="ch.qos.logback.core.FileAppender"> 
  	<file>testFile.log</file> 
  	<append>true</append> 
  	<encoder> 
  		<pattern>%-4relative [%thread] %-5level %logger{35} - %msg%n</pattern> 
  	</encoder>
  </appender> 
  ```

  它的 2 个属性和 ConsoleAppender 含义相同。它里面的子节点说明：

  - file：写入日志的文件名，可以是相对目录，也可以是绝对目录，如果上级目录不存在会自动创建。没有默认值。

  - append：如果是true，日志被追加到文件结尾；如果是 false，清空现有文件。默认为 false。

  - encoder：将记录的日志信息、事件格式化。

  - pattern：定义日志的输出格式。

    > pattern 日志输出格式说明，以 `<pattern>%d [%thread] %-5level -[%file:%line]- %msg%n</pattern>` 为例：
    >
    > - %d：表示日期
    > - %thread：线程名
    > - %-5level："-" 左对齐，"5" 不满 5 位右补空格
    > - %file:%line：文件名:行数
    > - %msg：日志消息
    > - %n：换行符
    > - %logger{50}：表示 Logger 名字最长 50 个字符，若超过 50 位，包名会精简为类似 a.b.s.JavaBean 形式)

    

  **3、RollingFileAppender** 滚动记录日志文件，先将日记记录到一个文件，当符合某个设置条件时，将会滚动切割日志记录到另外一个文件里。具体配置请看下一小节 **滚动切割日志配置**。

- **logger**：可选的子节点，设置具体的包或类的日志输出级别，以及使用 appender。一个 logger 节点配置：

  ```xml
  <!-- name 属性表示匹配的logger类型前缀 -->  
  <logger name="com.logback.demo">  
      <level value="INFO" />  
      <!-- 引用的appender，类似于spring的ref -->  
      <appender-ref ref="file" />  
      <appender-ref ref="STDOUT" /> 
  </logger>  
  
  <!--
  - name：必写属性，指定具体包或类名，被指定的包或类中的日志输出将遵从该 logger 设置的配置。
  
  - level：非必需属性，指定日志输出级别，该级别将覆盖 root 配置。
  
  - addtivity：非必需属性，是否向上级 logger 传递打印信息。默认是true。
  
  - appender-ref：引用的 appender，引用后将实现 appender 中定义的行为，例如上面示例中引用了 file 这个appender，那么com.logback.demo 中打印的日志将按 file 的配置进行输出。一个 logger 可以有多个引用，互不影响。
  -->
  ```

  

#### 2、滚动切割日志配置

appender 节点的 **RollingFileAppender** 类型，滚动切割日志文件，先将日记记录到一个文件，当符合某个设置条件时，将会滚动切割日志记录信息到另外一个文件里。`RollingFileAppender`节点基本配置：

```xml
<configuration>
	<!-- 直接定义属性 -->
    <property name="logFile" value="logs/loginfo"/>
    <property name="maxFileSize" value="30MB"/>

	<appender name="fileLog" class="ch.qos.logback.core.rolling.RollingFileAppender">
		<!-- 日志文件存储路径，来自property设置 -->
        <file>${logFile}.log</file>
        
        <encoder>
            <pattern>%d [%thread] %-5level -[%file:%line]- %msg%n</pattern>
            <charset>UTF-8</charset>
        </encoder>
        
        <!--
            设置滚动策略，用于配置文件大小限制、保留天数、文件名格式
            TimeBasedRollingPolicy: 基于时间滚动切割策略
            SizeBasedTriggeringPolicy：只以文件大小为切割条件
            SizeAndTimeBasedRollingPolicy: 基于大小和时间的滚动切割策略（对时间滚动切割策略的补充）
            FixedWindowRollingPolicy: 基于固定窗口的滚动切割策略   
        -->
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <!-- 活动日志文件最大值，超过这个值将产生新日志文件 -->
            <maxFileSize>${maxFileSize}</maxFileSize>
        	<!-- 每天生成一个新的活动日志文件，旧的日志归档，后缀名为2024.05.21这种格式 -->
            <fileNamePattern>${logFile}.%d{yyyy-MM-dd}.%i.log</fileNamePattern>
            <!-- 只保留最近15天的日志 -->
        	<maxHistory>15</maxHistory>
        	<!-- 用来指定日志文件的上限大小，那么到了这个值，就会删除旧的日志 -->
        	<totalSizeCap>2GB</totalSizeCap>
        </rollingPolicy>
        
        <!-- 用来指定日志文件的上限大小，那么到了这个值，就会删除旧的日志 -->
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>error</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
    </appender>
</configuration>
```

`RollingFileAppender` 基本配置说明：

> - filter：日志输出拦截器，可以自定义拦截器，也可以用系统定义的拦截器。
>
>   
>
> - rollingPolicy：当设置的切割条件触发时，决定 RollingFileAppender 执行动作，涉及日志文件的切割和重命名。属性 class 定义具体的切割策略。
>
>   - SizeAndTimeBasedRollingPolicy：根据日志文件大小和时间周期作为切割日志的触发条件，满足其中任意一条件时就会切割日志。fileNamePattern 设置日志文件，maxFileSize 的设置值决定了当天的日志文件大小上限，超过这个上限，同一天将会有多个日志文件，因此 `<fileNamePattern>${logFile}.%d{yyyy-MM-dd}.%i</fileNamePattern>` 中有一个 `%i`，为同一天生成多个日志文件而设置，在业务请求量大日志量多的情况下，会切割出多个日志文件如 `loginfo.2024-05-20.0.log`、`loginfo.2024-05-20.1.log` 等多个文件。
>
>   - TimeBasedRollingPolicy：以时间周期作为切割日志的触发条件，在这种设置策略下，存档日志文件的命名格式设置为`<fileNamePattern>${logFile}.%d{yyyy-MM-dd}.log</fileNamePattern>` 。
>
>   - FixedWindowRollingPolicy: 基于固定窗口的滚动切割策略。
>
>   - SizeBasedTriggeringPolicy：以文件大小为切割的触发条件，在这种策略下，`<maxFileSize>` 的设置是日志切割的唯一触发条件。
>
>     
>
>  - fileNamePattern：必要的设置节点。以 `${logFile}.%d{yyyy-MM-dd}.%i.log` 设置为例（`loginfo.2024-05-20.0.log`），有几个部分：
>
>    - `${logFile}`：固定文件名称前缀，这里是引用了 `<property>` 中设置的变量。
>    - `%d{yyyy-MM}`：指定了日志文件名称中日期的格式，如果只有 `%d`，将默认使用 `yyyy-MM-dd` 格式。
>    - `%i`：当日志记录量过大，导致同一天生成两个及以上日志文件时，这个属性将为日志名称加一个索引作为后缀，以加以区分切割的日志文件。
>    - `.log.zip`：指定存档日志文件的压缩格式。

#### 3、异步日志配置

logback 支持异步日志的写入，怎么异步写入？

异步日志指的是应用程序线程不必等待将日志消息写入磁盘。它可以将消息放入队列中，然后让专门的线程负责将消息写入磁盘。这可以大大提高程序的性能，特别是在高并发场景下。

配置例子：

```xml
<configuration>

  <!-- 配置异步Appender 
      queueSize：指定了消息队列的大小，
      discardingThreshold：指定了当消息队列满时应该丢弃多少消息。这里设置为 0，表示不会丢弃消息
      appender-ref：指定了实际的 appender，引用的 appender（下面定义了这个appender），这里我们将其设置为 FILE，表示将日志消息写入文件中
  -->
  <appender name="ASYNC" class="ch.qos.logback.classic.AsyncAppender">
    <queueSize>2048</queueSize>
    <discardingThreshold>0</discardingThreshold>
    <appender-ref ref="FILE" />
  </appender>

  <!-- 配置文件Appender -->
  <appender name="FILE" class="ch.qos.logback.core.FileAppender">
    <file>./logs/loginfo.log</file>
    <encoder>
      <pattern>%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{35} - %msg%n</pattern>
    </encoder>
  </appender>

  <!-- 配置Root logger -->
  <root level="info">
    <appender-ref ref="ASYNC" />
  </root>

</configuration>
```

上面例子中，定义了一个名为 ASYNC 的异步 Appender，它使用的是 AsyncAppender 类。



### 示例配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <!-- 定义属性，日志输出的格式 -->
    <property name="LOG_PATTERN" value="%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"/>

    <!-- 控制台输出 -->
    <appender name="console" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>${LOG_PATTERN}</pattern>
        </encoder>
    </appender>

    <!-- 文件输出 -->
    <appender name="file" class="ch.qos.logback.core.FileAppender">
        <file>app.log</file>
        <encoder>
            <pattern>${LOG_PATTERN}</pattern>
        </encoder>
    </appender>

    <!--创建一个具体的日志输出-->
    <logger name="demo.logback" level="info" additivity="true">
    	<!-- 可以有多个appender-ref，即将日志记录到不同的文件类型 -->
        <appender-ref ref="console"/>
        <appender-ref ref="file"/>
    </logger>

    <!-- 基础的日志输出 -->
    <root level="info">
        <!-- 可以有多个appender-ref，将日志记录到不同的文件类型 -->
        <!--<appender-ref ref="console"/>
        <appender-ref ref="file"/>-->
    </root>
    
    <!-- 开发环境 -->
     <springProfile name="dev">
        <root level="DEBUG">
            <appender-ref ref="console" />
            <appender-ref ref="file" />
        </root>
    </springProfile>

    <!-- 生产环境 -->
    <springProfile name="prod">
        <root level="INFO">
            <appender-ref ref="file" />
        </root>
    </springProfile>
</configuration>
```



## 日志框架的使用

### 第一种：通过SLF4J的LoggerFactory获取Logger实例

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyLogbackDemo {
    private static final Logger logger = LoggerFactory.getLogger(MyLogbackDemo.class);

    public void doSomething() {
        logger.info("doing something");
    }
}
```

### 第二种：引入 lombok，注解实现

引入 lombok，使用 `@Slf4j` 注解自动生成一个 `log` 对象，省去手动获取 `Logger` 对象的步骤，简化了代码编写。

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <scope>compile</scope>
</dependency>
```

使用 lombok 只需在类的头部添加注解 @Slf4j 即可：

```java
@Slf4j
@SpringBootApplication
public class SpringBootLoggingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootLoggingApplication.class, args);

        log.info("app log info");
    }
}
```

## 完整代码例子

**完整的代码在 github 上**：https://github.com/jiujuan/spring-boot-learning/tree/master/springboot-logback-demo



编写的步骤：

1、 到 https://start.aliyun.com/ 生成一个 SpringBoot3 的工程代码，MAVEN 管理依赖项

2、下载代码，然后导入到代码编辑器 IDEA 或 vscode 中

我用的是 vscode 编辑器。

3、整个项目目录结构和文件如下：

![image-20250226213820863](.\images\image-20250226213820863.png)

4、pom.xml 引入项

```xml
<parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.0.2</version>
    </parent>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!-- Spring Boot 默认已经包含了 Logback 依赖，无需额外添加 -->
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
```

5、配置 logback-spring.xml 和 application.properties

application.properties 配置：

```xml
# 设置日志级别
logging.level.com.example.demo=DEBUG

# 禁用Spring Boot默认的日志配置（使用logback-spring.xml）
logging.config=classpath:logback-spring.xml
```

logback-spring.xml 详细配置请查看 [github 上代码](https://github.com/jiujuan/spring-boot-learning/blob/master/springboot-logback-demo/src/main/resources/logback-spring.xml)

6、编写 controller/LogDemoController.java

```java
@RestController
public class LogDemoController {
    private static final Logger logger = LoggerFactory.getLogger(LogDemoController.class);

    @Autowired
    private LogDemoService logDemoService;

    @GetMapping("/test-log")
    public String testLog() {
        logger.trace("这是一条 TRACE 日志");
        logger.debug("这是一条 DEBUG 日志");
        logger.info("这是一条 INFO 日志");
        logger.warn("这是一条 WARN 日志");
        logger.error("这是一条 ERROR 日志");

        logDemoService.performLogging();

        return "日志测试完成，请查看控制台和日志文件";
    }
}
```

7、编写 service/LogDemoService.java

```java
package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LogDemoService {
    private static final Logger logger = LoggerFactory.getLogger(LogDemoService.class);
    
    public void performLogging() {
        logger.trace("s:这是一条 TRACE 日志");
        logger.debug("s:这是一条 DEBUG 日志");
        logger.info("s:这是一条 INFO 日志");
        logger.warn("s:这是一条 WARN 日志");
        logger.error("s:这是一条 ERROR 日志");
    }
}
```

8、然后到终端下 cd 到 springboot-logback-demo下，运行命令：

```bash
mvn spring-boot:run
```

9、在浏览器上运行 http://localhost:8080/test-log

在终端和 logs 目录就会有日志记录

【完】

## 参考

- https://logback.qos.ch/  Logback项目地址
- https://www.slf4j.org/   slf4j项目地址
- https://logback.qos.ch/documentation.html  logback Doc
- https://logback.qos.ch/manual/ logback 帮助手册
- https://github.com/qos-ch logback 和 slf4j 在 GitHub
- https://docs.spring.io/spring-boot/how-to/logging.html springboot logging how-to
- https://github.com/jiujuan/spring-boot-learning/tree/master/springboot-logback-demo 完整的 logback demo 代码例子