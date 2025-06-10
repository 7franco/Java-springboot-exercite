package com.franco.springboot.di.app.springboot_di.repositories;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.franco.springboot.di.app.springboot_di.models.Product;

@Primary
@Repository
public class ProductRepositoryFoo implements ProductRepository{

    @Override
    public List<Product> findAll() {
        return Collections.singletonList(new Product(1L, "Laptop Dell", 1500L ));
    }

    @Override
    public Product findById(Long id) {
        return new Product(id, "Laptop Dell", 1500L );
    }
    

}
