package com.ercifenpeixitong.ercifenpeixitong.config;

import com.ercifenpeixitong.ercifenpeixitong.interceptors.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 拦截器注册类
 */
@Configuration
public class WebMvcAdapter implements WebMvcConfigurer {
    /**
     * addPathPatterns 该拦截器需要拦截的请求
     * excludePathPatterns 不需要拦截的请求
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePaths =new ArrayList<>();
        excludePaths.add("/login");
        excludePaths.add("/admin/login");
        excludePaths.add("/loginHandler");
        excludePaths.add("loginHandler");
        excludePaths.add("/static/**");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
                                                       .excludePathPatterns(excludePaths);

    }
}
