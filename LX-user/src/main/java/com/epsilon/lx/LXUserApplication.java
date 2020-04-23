package com.epsilon.lx;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
@EnableFeignClients
public class LXUserApplication {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SpringApplication.run(LXUserApplication.class,args);
    }

}
