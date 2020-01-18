package com.epsilon.lx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.epsilon.lx"})
public class DeptFeignApplication   {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SpringApplication.run(DeptFeignApplication.class, args);
    }

}
