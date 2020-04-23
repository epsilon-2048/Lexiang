package com.epsilon.lx.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * description:
 *
 * @author wangpeng
 * @date 2018/05/17
 */

/**
 * https://www.jianshu.com/p/647cf467b47c
 * 因为遇到了路由转发404的问题，所以决定采用自定义路由。刚开始尝试了从DB里读取，但发现再以后扩展服务的时候不够灵活，所以还是采用了自己从eureka中拉取服务列表缓存到zuul内存中的方式。
 */
public class CustomRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {
    public final static Logger logger = LoggerFactory.getLogger(CustomRouteLocator.class);

    private JdbcTemplate jdbcTemplate;

    private ZuulProperties properties;

    @Autowired
    private DiscoveryClient discoveryClient;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public CustomRouteLocator(String servletPath, ZuulProperties properties) {
        super(servletPath, properties);
        this.properties = properties;
        logger.info("servletPath:{}", servletPath);
    }

    @Override
    public void refresh() {
        doRefresh();
    }

    @Override
    protected Map<String, ZuulProperties.ZuulRoute> locateRoutes() {
        LinkedHashMap<String, ZuulProperties.ZuulRoute> routesMap = new LinkedHashMap<>();
        LinkedHashMap<String, ZuulProperties.ZuulRoute> values = new LinkedHashMap<>();
        //获取所有服务
        List<String> services = discoveryClient.getServices();
        int id = 0;
        for (int i = 0; i < services.size(); i++) {
            String s = services.get(i);
            List<ServiceInstance> instances = discoveryClient.getInstances(s);
            for (int j = 0; j < instances.size(); j++) {
                ServiceInstance serviceInstance = instances.get(j);
                ZuulProperties.ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute();
                zuulRoute.setId(id + "");
                zuulRoute.setServiceId(serviceInstance.getServiceId());
                zuulRoute.setUrl(serviceInstance.getUri().toString());
                zuulRoute.setRetryable(false);
                String path = "/" + s + "/**";
                zuulRoute.setPath(path);
                values.put(path, zuulRoute);
                id++;
            }
        }
//        //从application.properties中加载路由信息
//        routesMap.putAll(super.locateRoutes());
//        //从db中加载路由信息
//        routesMap.putAll(locateRoutesFromDB());
//        //优化一下配置
//        for (Map.Entry<String, ZuulProperties.ZuulRoute> entry : routesMap.entrySet()) {
//            String path = entry.getKey();
//            // Prepend with slash if not already present.
//            if (!path.startsWith("/")) {
//                path = "/" + path;
//            }
//            if (StringUtils.hasText(this.properties.getPrefix())) {
//                path = this.properties.getPrefix() + path;
//                if (!path.startsWith("/")) {
//                    path = "/" + path;
//                }
//            }
//            values.put(path, entry.getValue());
//        }
        return values;
    }

    private Map<String, ZuulProperties.ZuulRoute> locateRoutesFromDB() {
        Map<String, ZuulProperties.ZuulRoute> routes = new LinkedHashMap<>();
        List<ZuulRouteVO> results = jdbcTemplate.query("select * from gateway_api_define where enabled = true ", new
                BeanPropertyRowMapper<>(ZuulRouteVO.class));
        for (ZuulRouteVO result : results) {
            if (StringUtils.isEmpty(result.getPath())) {
                continue;
            }
            if (StringUtils.isEmpty(result.getServiceId()) && StringUtils.isEmpty(result.getUrl())) {
                continue;
            }
            ZuulProperties.ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute();
            try {
                BeanUtils.copyProperties(result, zuulRoute);
            } catch (Exception e) {
                logger.error("=============load zuul route info from db with error==============", e);
            }
            routes.put(zuulRoute.getPath(), zuulRoute);
        }
        return routes;
    }

    public static class ZuulRouteVO {

        /**
         * The ID of the route (the same as its map key by default).
         */
        private String id;

        /**
         * The path (pattern) for the route, e.g. /foo/**.
         */
        private String path;

        /**
         * The service ID (if any) to map to this route. You can specify a physical URL or
         * a service, but not both.
         */
        private String serviceId;

        /**
         * A full physical URL to map to the route. An alternative is to use a service ID
         * and service discovery to find the physical address.
         */
        private String url;

        /**
         * Flag to determine whether the prefix for this route (the path, minus pattern
         * patcher) should be stripped before forwarding.
         */
        private boolean stripPrefix = true;

        /**
         * Flag to indicate that this route should be retryable (if supported). Generally
         * retry requires a service ID and ribbon.
         */
        private Boolean retryable;

        private Boolean enabled;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getServiceId() {
            return serviceId;
        }

        public void setServiceId(String serviceId) {
            this.serviceId = serviceId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isStripPrefix() {
            return stripPrefix;
        }

        public void setStripPrefix(boolean stripPrefix) {
            this.stripPrefix = stripPrefix;
        }

        public Boolean getRetryable() {
            return retryable;
        }

        public void setRetryable(Boolean retryable) {
            this.retryable = retryable;
        }

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }
    }
}