package com.jfranco.springboot.jpa.springboot_jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jfranco.springboot.jpa.springboot_jpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long>{


    @Query("select p from Person p where p.id=?1")
    Optional<Person> findOne(Long id); 

    @Query("select p from Person p where p.name=?1")
    Optional<Person> findName(String name); 
    
    List<Person> findByProgrammingLanguage(String programmingLanguage);    

    @Query("select p from Person p where p.programmingLanguage=?1 and p.name=?2")
    List<Person> buscarByProgrammingLanguage(String programmingLanguage, String name);    


    List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);    

    @Query("select p.name, p.programmingLanguage from Person p")
    List<Object[]> obtenerPersonData();
} 
