package com.franco.curso.springboot.error.springboot_error.services;

import java.util.List;
import java.util.Optional;

import com.franco.curso.springboot.error.springboot_error.models.domain.User;

public interface UserService {

    List<User> findAll();
    
    Optional<User> finById(Long id);
}
