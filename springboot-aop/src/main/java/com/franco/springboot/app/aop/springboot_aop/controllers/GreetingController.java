package com.franco.springboot.app.aop.springboot_aop.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.franco.springboot.app.aop.springboot_aop.services.GreetingService;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class GreetingController {

    @Autowired
    private GreetingService greetingService;

    @GetMapping("/greeting")
    public ResponseEntity<?> greeting(Object one) {
        return ResponseEntity.ok(Collections.singletonMap("greeting", greetingService.sayHello("Pepe", "Hola que tal!")));
    }

    @GetMapping("/greeting-error")
    public ResponseEntity<?> greetingError(Object one) {
        return ResponseEntity.ok(Collections.singletonMap("greeting", greetingService.sayHelloError("Pepe", "Hola que tal!")));
    }
    
}
