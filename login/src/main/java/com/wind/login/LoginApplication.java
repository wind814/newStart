package com.wind.login;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@MapperScan("com.wind.login.dao")
@tk.mybatis.spring.annotation.MapperScan("com.wind.login.dao")
//@EnableEurekaClient  现在不需要了。
@EnableFeignClients
public class LoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginApplication.class, args);
    }

}
