package com.wind.fegin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by wind on 20/6/30.
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableEurekaClient
@EnableFeignClients
//@ComponentScan("com.wind.fegin.dao")
public class FeginServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeginServerApplication.class, args);
    }

}
