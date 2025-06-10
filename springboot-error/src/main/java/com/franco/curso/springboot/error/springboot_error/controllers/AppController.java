package com.franco.curso.springboot.error.springboot_error.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.franco.curso.springboot.error.springboot_error.exceptions.UserNotFoundException;
import com.franco.curso.springboot.error.springboot_error.models.domain.User;
import com.franco.curso.springboot.error.springboot_error.services.UserService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private UserService userService;

    @GetMapping()    
    public String index(){
        int value = Integer.parseInt("10x");
        System.out.println(value);
        return "ok 200";
    }

    @GetMapping("/show/{id}")
    public User show(@PathVariable(name = "id") Long id){
        /*User user = userService.finById(id);
        if(user == null){
            throw new UserNotFoundException("Error el ususario no existe");
        }*/
        User user = userService.finById(id).orElseThrow(()-> new UserNotFoundException("Error el ususario no existe"));
    
        return user;
    }
    

    /*@GetMapping("/show/{id}")
    public ResponseEntity<Object> show(@PathVariable(name = "id") Long id){
        Optional<User> optionalUser = userService.finById(id);
        if(optionalUser.isEmpty()){
            return ResponseEntity.notFound().build();

        }
        return ResponseEntity.ok(optionalUser.orElseThrow());
    }*/
    

}
