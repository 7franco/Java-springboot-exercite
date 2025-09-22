package com.franco.springboot.jpa.springboot_jpa_relationship.repository;

import org.springframework.data.repository.CrudRepository;

import com.franco.springboot.jpa.springboot_jpa_relationship.entities.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {

}
