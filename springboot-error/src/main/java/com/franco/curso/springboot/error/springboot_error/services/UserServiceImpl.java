package com.franco.curso.springboot.error.springboot_error.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.franco.curso.springboot.error.springboot_error.models.domain.User;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private List<User> users;

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> finById(Long id) {
        /*User user = null;
        for (User u: users) {
            if(u.getId().equals(id)){
                user = u;
                break;
            }
        }

        if(user == null){
            return Optional.empty();// devuelve false
        }
            return Optional.ofNullable(user);
            */
        Optional<User> user = users.stream().filter(u -> u.getId().equals(id)).findFirst();
        return user;
    }
    

}
