package com.franco.springboot.di.factura.springboot_difactura;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import com.franco.springboot.di.factura.springboot_difactura.models.Item;
import com.franco.springboot.di.factura.springboot_difactura.models.Product;

import java.util.List;
import java.util.Arrays;

@Configuration
@PropertySource(value =  "classpath:data.properties", encoding = "UTF-8")
public class AppConfig {

    @Bean()
    List<Item> itemsInvoice(){
        Product p1 = new Product("Camara Sony", 800);
        Product p2 = new Product("Bicicleta", 1200);
        return Arrays.asList(new Item(p1,2), new Item(p2,4));
    }

    @Primary
    @Bean
    List<Item> itemsInvoiceOffice(){
        Product p1 = new Product("Monitor Azus 24", 700);
        Product p2 = new Product("Notebook Razer", 2400);
        Product p3 = new Product("Impresora HP", 800);
        Product p4 = new Product("Destok Oficina", 900);
        return Arrays.asList(new Item(p1,4), new Item(p2,6), new Item(p3,1), new Item(p4,4));
    }

}
