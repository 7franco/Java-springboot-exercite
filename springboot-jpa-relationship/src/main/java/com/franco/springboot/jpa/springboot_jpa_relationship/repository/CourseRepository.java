package com.franco.springboot.jpa.springboot_jpa_relationship.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.franco.springboot.jpa.springboot_jpa_relationship.entities.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {


    @Query("select c from Course c left join fetch c.students where c.id=?1")
    Optional<Course> findOneWithStudent(Long id);

}
