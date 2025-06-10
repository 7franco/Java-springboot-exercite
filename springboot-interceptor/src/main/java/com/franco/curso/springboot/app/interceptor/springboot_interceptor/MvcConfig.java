package com.franco.curso.springboot.app.interceptor.springboot_interceptor;

import java.util.logging.Handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;

@Configuration
public class MvcConfig implements WebMvcConfigurer{

    @Autowired
    @Qualifier("timeInterceptor")
    private HandlerInterceptor timeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(timeInterceptor);
        //registry.addInterceptor(timeInterceptor).addPathPatterns("/app/**");//donde si ejecutar 
        registry.addInterceptor(timeInterceptor).addPathPatterns("/app/bar", "/app/foo");//donde si ejecutar 
        //registry.addInterceptor(timeInterceptor).excludePathPatterns("/app/bar", "/app/foo"); // donde no ejecutar
    }

    
}
