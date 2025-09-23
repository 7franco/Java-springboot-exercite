package com.franco.repasando.springboot.app.springboot_crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.franco.repasando.springboot.app.springboot_crud.entities.Product;
import com.franco.repasando.springboot.app.springboot_crud.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {        
        return (List<Product>) productRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    @Override
    public Optional<Product> delete(Long id) {
        Optional<Product> productDb = productRepository.findById(id);
        productDb.ifPresent(pr->{
            productRepository.delete(pr);
        });
        return productDb;
        
    }

    @Transactional
    @Override
    public Optional<Product> update(Long id, Product product){
        Optional<Product> productDb = productRepository.findById(id);
        if(productDb.isPresent()){
            Product pr = productDb.orElseThrow();
            pr.setSku(product.getSku());
            pr.setName(product.getName());
            pr.setPrice(product.getPrice());
            pr.setDescription(product.getDescription());
            return Optional.of(productRepository.save(pr));
        }
        return productDb;
    }

    @Override
    // @Transactional(readOnly = true)
    public boolean existsBySku(String sku) {
        return productRepository.existsBySku(sku);
    }

}

