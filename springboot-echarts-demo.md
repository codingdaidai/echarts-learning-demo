# echarts-demo



## 建表

MySQL中创建一个示例表：

```sql
CREATE TABLE `echarts_demo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
```

有下面几个字段：

![image-20211110113215428](https://codingdaidai.oss-cn-beijing.aliyuncs.com/img/image-20211110113215428.png)





## 接口

与MySQL交互，需要写一下接口，用springboot比较方便



IDEA中创建一个springboot项目，先写好项目的依赖配置pom文件；

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.5.6</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.demon</groupId>
    <artifactId>echarts_demo</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>echarts_demo</name>
    <description>Demo project for Spring Boot</description>
    <properties>
        <java.version>1.8</java.version>
        <mybatis-plus.version>3.2.0</mybatis-plus.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.1.4</version>
        </dependency>

        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>${mybatis-plus.version}</version>
        </dependency>

        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-generator</artifactId>
            <version>${mybatis-plus.version}</version>
        </dependency>

        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.2.2</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>

        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>5.5.4</version>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>

    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <fork>true</fork>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```





再写配置文件application.yml：

```yml
server:
  port: 9091
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: codingdaidai
    url: jdbc:mysql://127.0.0.1:3306/echarts?autoReconnect=true&&useSSL=false&&serverTimezone=GMT%2B8
    type: com.alibaba.druid.pool.DruidDataSource

logging:
  level:
    com:
      demon:
        echarts_demo:
          debug
```



写整个程序的启动入口：

```java
package com.demon.echarts_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com/demon/echarts_demo/mapper")
public class EchartsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EchartsDemoApplication.class, args);
    }

}
```



写一个简单的控制器看是否能成功启动：

```java
package com.demon.echarts_demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class EchartsController {

    @GetMapping("/pie")
    public String pie(){
        return "hello";
    }
}
```



运行一下：

![image-20211110140016987](https://codingdaidai.oss-cn-beijing.aliyuncs.com/img/image-20211110140016987.png)

没问题，然后写与MySQL的连接



先在数据库里加一点数据：

![image-20211110140847103](https://codingdaidai.oss-cn-beijing.aliyuncs.com/img/image-20211110140847103.png)



接下来再写与数据库的连接，编写实体类和对应的接口，实现类：

具体略，使用Mybatis-plus方便操作数据库，项目结构：

![image-20211110150531658](https://codingdaidai.oss-cn-beijing.aliyuncs.com/img/image-20211110150531658.png)



写好后重启服务，访问localhost:9091/echarts：

![image-20211110150641660](https://codingdaidai.oss-cn-beijing.aliyuncs.com/img/image-20211110150641660.png)



PostMan测试接口。可用：

![image-20211110164223562](https://codingdaidai.oss-cn-beijing.aliyuncs.com/img/image-20211110164223562.png)

只写了一个接口演示用，后续有需求再补充



### 可视化

前端通过Ajax或者其他来请求后端的接口，拿到数据再做后续处理

