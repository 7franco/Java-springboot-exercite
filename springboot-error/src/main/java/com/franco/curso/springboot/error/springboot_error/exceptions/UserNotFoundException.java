package com.franco.curso.springboot.error.springboot_error.exceptions;

public class UserNotFoundException extends RuntimeException{
    
    public UserNotFoundException(String message){
        super(message);
    }
}
