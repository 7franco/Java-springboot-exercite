package com.franco.springboot.app.aop.springboot_aop.services;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImp implements GreetingService{


    @Override
    public String sayHello(String person, String phrase) {
        String greeting = phrase + " " + person; 
        System.out.println(greeting);
        return greeting;
    }

    @Override
    public String sayHelloError(String person, String phrase) {
        throw new RuntimeErrorException(null, "algun error");
    }

}
