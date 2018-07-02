package com.ercifenpeixitong.ercifenpeixitong.config;

import com.ercifenpeixitong.ercifenpeixitong.interceptors.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
                                                        .excludePathPatterns("/login");
    }
}
