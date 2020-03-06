package com.epsilon.lx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@RestController
@EnableEurekaClient
@EnableSwagger2
//@EnableFeignClients
@ComponentScan({"epsilon_2048.security","com.epsilon.lx"})
public class LXAuthApplication {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SpringApplication.run(LXAuthApplication.class,args);
    }
}

