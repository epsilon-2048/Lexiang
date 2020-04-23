package com.epsilon.lx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.util.AntPathMatcher;

import java.util.LinkedHashMap;
import java.util.Map;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class LXGatewayApplication {
    @Bean
    public PatternServiceRouteMapper serviceRouteMapper(){
        // 调用构造函数 PatternServiceRouteMapper(String servicePattern,String routePattern)
        // servicePattern 指定微服务的正则
        // routePattern 指定路由正则
        return new PatternServiceRouteMapper("(?<name>^.+)-(?<version>v.+$)","${version}/${name}");
    }
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
