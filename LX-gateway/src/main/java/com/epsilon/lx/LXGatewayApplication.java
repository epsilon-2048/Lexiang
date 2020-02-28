package com.epsilon.lx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.util.AntPathMatcher;

import java.util.LinkedHashMap;
import java.util.Map;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class LXGatewayApplication {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SpringApplication.run(LXGatewayApplication.class,args);
    }
/*
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        LinkedHashMap<String,String> route = new LinkedHashMap<>();
        route.put("DEPT-TEST","api/dept/**");
        route.put("LX-AUTH","api/**");
        for (Map.Entry<String, String> e :
                route.entrySet()) {
            if (antPathMatcher.match(e.getValue(), "api/authentication/form"))
                System.out.println(e.getValue());
        }
    }*/
}
