package com.franco.springboot.jpa.springboot_jpa_relationship.repository;

import org.springframework.data.repository.CrudRepository;

import com.franco.springboot.jpa.springboot_jpa_relationship.entities.ClientDetails;

public interface ClientDetailsRepository extends CrudRepository<ClientDetails, Long>{

}
