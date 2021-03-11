package com.sxsh.config;

import com.sxsh.config.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description
 * @Author lhy
 * @Date 2021/1/8
 * @Version 1.0.0
 */
@Configuration
public class AuthWebMvcConfigurer implements WebMvcConfigurer {

    @Bean
    public AuthInterceptor authInterceptor(){
        return new AuthInterceptor();
    }

    /**
     * 拦截URL
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        InterceptorRegistration interceptorRegistration = registry.addInterceptor(authInterceptor());
        //要拦截的路径
        interceptorRegistration.addPathPatterns("/**");
    }
}
