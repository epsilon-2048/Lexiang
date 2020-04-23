package com.epsilon.lx.config;


import com.epsilon.lx.config.rule.RibbonRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class ZuulConfig {

    /**
     * https://www.jianshu.com/p/647cf467b47c
     * 因为遇到了路由转发404的问题，所以决定采用自定义路由。刚开始尝试了从DB里读取，但发现再以后扩展服务的时候不够灵活，所以还是采用了自己从eureka中拉取服务列表缓存到zuul内存中的方式。
     */
    /**
     * 有bug
     */
   /* @Autowired
    ZuulProperties zuulProperties;
    @Autowired
    ServerProperties server;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Bean
    public CustomRouteLocator routeLocator() {
        CustomRouteLocator routeLocator = new CustomRouteLocator(this.server.getServletPrefix(), this.zuulProperties);
        routeLocator.setJdbcTemplate(jdbcTemplate);
        return routeLocator;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(){
        return new JdbcTemplate();
    }*/

    //类似RedisTemplate，提供了多种边界访问远程http服务的方法
    //是一种简单便捷的访问restful服务模板类，是spring提供的用于访问Rest服务的客户端模板工具类
    //关于RestTemplate可参考：https://blog.csdn.net/itguangit/article/details/78825505

    /**
     *  简单使用
     *  （url,requsetMap,ResponseBean,class）这三个参数
     *  分别代表Rest请求地址，请求参数，Http响应转换为想要的class对象类型
     *
     */

/*
    @Bean
    @LoadBalanced  //Spring cloud ribbon 是基于netflix Ribbon实现的一套客户端 负载均衡的工具
    public RestTemplate getRestTemplate(){
        return  new RestTemplate();
    }
*/
   /* //默认轮询选择算法，更改默认算法，为随机,和ribbon一致，因为feign的负载均衡是基于ribbon，或者说集成了ribbon
    @Bean
    IRule myRule(){
        return new RandomRule();
    }*/
    //自定义选择算法 这是一种更改方式，
    @Bean
    IRule feignRule(){
        return new RibbonRule();
    }

    //未能生效
    //还有一种是
    //在客户端启动类添加注解@RibbonClient(name="MICROSERVICECLOUD-DEPT",configuration=MySelfRule.class)
    //他会在启动该微服务时去加载我们自定义的Ribbon配置类
    //但是第二种方式，自定义配置类不能放在@ComponentScan所扫描的当前包下及其子包下，
    //否则我们自定义的这个配置类就会被所有的Ribbon客户端所共享，也就说我们达不到特殊化定制的目的了
    //而启动类的注解@SpringBootApplication中带有ComponentScan注解，也就是说自定义配置类不能放在
    //com.atguigu.springcloud包下以及其子包下。
}
