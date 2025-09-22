package com.franco.springboot.jpa.springboot_jpa_relationship.repository;

import org.springframework.data.repository.CrudRepository;

import com.franco.springboot.jpa.springboot_jpa_relationship.entities.Student;

public interface StudentRepository extends CrudRepository<Student, Long>{

}
