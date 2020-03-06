package com.epsilon.lx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
@EnableFeignClients
//@ComponentScan({"epsilon_2048.security"})
public class LXUploadFileApplication {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SpringApplication.run(LXUploadFileApplication.class, args);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //允许上传的文件最大值
        factory.setMaxFileSize("2048MB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("2048MB");
        return factory.createMultipartConfig();
    }
}
