package com.jfranco.springboot.jpa.springboot_jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jfranco.springboot.jpa.springboot_jpa.dto.PersonDto;
import com.jfranco.springboot.jpa.springboot_jpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long>{

    @Query("select p from Person p where p.id in ?1")
    public List<Person> getPersonsById(List<Long> ids); 

    @Query("Select p.name, length(p.name) from Person p where length(p.name)=(select min(length(p.name)) from Person p)")
    public List<Object[]> getShorterName();

    @Query("select p from Person p  where p.id=(select max(p.id) from Person p)")
    public Optional<Person> getLastRegistration();

    @Query("select min(p.id), max(p.id), sum(p.id), avg(length(p.name)), count(p.id) from Person p")
    public Object getResumeAggregationFuncion();

    @Query("select min(length(p.name)) from Person p")
    public Integer getMinLengName();

    
    @Query("select max(length(p.name)) from Person p")
    public Integer getMaxLengName();

    @Query("select p.name, length(p.name) from Person p")
    public List<Object[]> getPersonNameLength(); 

    @Query("Select count(p) from Person p")
    Long getTotalPerson();

    @Query("select min(p.id) from Person p")
    Long getMinId();

    @Query("select max(p.id) from Person p")
    Long getMaxId();

    List<Person> findAllByOrderByNameDesc();

    @Query("Select p from Person p order by p.name, p.lastName desc")
    List<Person> getAllOrdered();
    
    List<Person> findByIdBetweenOrderByIdDesc(Long id1,Long id2);

    List<Person> findByNameBetweenOrderByNameDesc(String name1,String name2);

    @Query("Select p from Person p where p.name between ?1 and ?2 order by p.name asc, p.lastName desc")
    public List<Person> findAllBetweenName(String c1, String c2);

    @Query("Select p from Person p where p.id between ?1 and ?2 order by p.name asc")
    public List<Person> findAllBetweenId(Long v1, Long v2);

    @Query("Select lower(CONCAT(p.name,' ', p.lastName)) as fullName from Person p ")
    List<String> getFullNameConcatLower();

    @Query("Select upper(p.name ||' '|| p.lastName)from Person p ")
    List<String> getFullNameConcatUpper();

    //@Query("Select CONCAT(p.name,' ', p.lastName) as fullName from Person p ")
    @Query("Select p.name ||' '|| p.lastName from Person p ")
    List<String> getFullNameConcat();
    
    @Query("Select count(distinct(p.programmingLanguage)) from Person p")
    Long findAllLanguageDistinctCount();

    @Query("Select distinct(p.programmingLanguage) from Person p")
    List<String> findAllLanguageDistinct();

    @Query("Select distinct(p.name) from Person p")
    List<String> findALlNamesDistinct();

    @Query("Select p.name from Person p")
    List<String> findALlNames();

    @Query("Select new com.jfranco.springboot.jpa.springboot_jpa.dto.PersonDto(p.name, p.lastName) from Person p")
    List<PersonDto> findAllPersonDtoPersonalized();

    @Query("Select new Person(p.name, p.lastName) from Person p")
    List<Person> findAllObjectPersonPersonalized();

    @Query("Select p.name from Person p where p.id=?1")
    String getNameById(Long id);

    @Query("Select CONCAT(p.name,' ', p.lastName) as fullName from Person p where p.id=?1")
    String getFullNameById(Long id);

    @Query("select p from Person p where p.id=?1")
    Optional<Person> findOne(Long id); 

    @Query("select p from Person p where p.name=?1")
    Optional<Person> findName(String name); 

    @Query("select p from Person p where p.name like %?1%")
    Optional<Person> findOneLikeName(String name); 

    Optional<Person> findByName(String name); 
    
    Optional<Person> findByNameContaining(String name); 
    
    List<Person> findByProgrammingLanguage(String programmingLanguage);    

    @Query("select p from Person p where p.programmingLanguage=?1 and p.name=?2")
    List<Person> buscarByProgrammingLanguage(String programmingLanguage, String name);    


    List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);    

    @Query("select p.id, p.name, p.lastName, p.programmingLanguage from Person p")
    List<Object[]> obtenerPersonDataFullList();

    @Query("select p.id, p.name, p.lastName, p.programmingLanguage from Person p where p.id=?1")
    Object obtenerPersonDataFullById(Long id);

    @Query("select p.name, p.programmingLanguage from Person p")
    List<Object[]> obtenerPersonData();

    @Query("select p, p.programmingLanguage from Person p")
    List<Object[]> findAllMixPerson();
} 
