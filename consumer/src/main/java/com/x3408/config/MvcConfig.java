package com.x3408.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Autowired
    private IndexHandlerInterceptor indexHandlerInterceptor;
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //路径映射
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/addAdmin").setViewName("addAdmin");
        registry.addViewController("/login").setViewName("login");
            registry.addViewController("/navigateToCargo").setViewName("addCargo");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(indexHandlerInterceptor).addPathPatterns("/index");
    }

}
