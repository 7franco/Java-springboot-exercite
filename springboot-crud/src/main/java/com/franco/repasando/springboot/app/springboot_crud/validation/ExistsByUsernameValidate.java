package com.franco.repasando.springboot.app.springboot_crud.validation;

import org.springframework.stereotype.Component;

import com.franco.repasando.springboot.app.springboot_crud.services.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ExistsByUsernameValidate implements ConstraintValidator<ExistsByUsername, String>{

    private final UserService service;

    public ExistsByUsernameValidate(UserService service) {
        this.service = service;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (username == null || username.isEmpty()) {
            return true;
        }
        return !service.existsByUsername(username);
    }

}
