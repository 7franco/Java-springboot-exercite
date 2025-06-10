package com.franco.springboot.di.app.springboot_di.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.franco.springboot.di.app.springboot_di.models.Product;
import com.franco.springboot.di.app.springboot_di.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
    
    @Qualifier("productRepositoryJson")
    @Autowired
    private ProductRepository repository ;

    @Autowired
    private Environment environment;

    /*private ProductRepository repository ;

    @Autowired
    public void setRepository(ProductRepository repository) {
        this.repository = repository;
    }*/

    /*private ProductRepository repository ;
       
    public ProductServiceImpl(@Qualifier("productRepositoryImpl") ProductRepository repository) {
        this.repository = repository;
    }*/

    @SuppressWarnings("null")
    @Override
    public List<Product> findAll(){
        return repository.findAll().stream().map(p->{
            Double priceTax = p.getPrice() * environment.getProperty("config.value.tax", Double.class);
            
            /*p.setPrice(priceTax.longValue());
            return p;*/
            
            //Product prd = new Product(p.getId(), p.getName(), priceImp.longValue());
            //return prd;
            
            Product prd =(Product)p.clone();
            prd.setPrice(priceTax.longValue());
            return prd;

        }).collect(Collectors.toList());
    }    

    @Override
    public Product findById(Long id){
        return repository.findById(id);
    }

}
