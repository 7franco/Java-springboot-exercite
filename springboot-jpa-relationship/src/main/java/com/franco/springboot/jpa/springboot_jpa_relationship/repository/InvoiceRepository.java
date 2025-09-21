package com.franco.springboot.jpa.springboot_jpa_relationship.repository;

import org.springframework.data.repository.CrudRepository;

import com.franco.springboot.jpa.springboot_jpa_relationship.entities.Invoice;

public interface InvoiceRepository extends CrudRepository<Invoice, Long>{

    

}
