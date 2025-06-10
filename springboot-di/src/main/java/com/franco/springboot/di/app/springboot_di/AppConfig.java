package com.franco.springboot.di.app.springboot_di;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.io.Resource;

import com.franco.springboot.di.app.springboot_di.repositories.ProductRepository;
import com.franco.springboot.di.app.springboot_di.repositories.ProductRepositoryJson;

@Configuration
@PropertySources({
    @PropertySource(value = "classpath:config.properties", encoding = "UTF-8")
})
public class AppConfig {

    @Value("Classpath:json/product.json")
    private Resource resource;

    @Bean
    ProductRepository productRepositoryJson(){
        return new ProductRepositoryJson(resource);
    }
}
