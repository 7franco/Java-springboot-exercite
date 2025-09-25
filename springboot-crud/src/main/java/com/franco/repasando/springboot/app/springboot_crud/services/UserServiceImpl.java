package com.franco.repasando.springboot.app.springboot_crud.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.franco.repasando.springboot.app.springboot_crud.entities.Role;
import com.franco.repasando.springboot.app.springboot_crud.entities.User;
import com.franco.repasando.springboot.app.springboot_crud.repositories.RoleRepository;
import com.franco.repasando.springboot.app.springboot_crud.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
       return (List<User>) repository.findAll();
    }

    @Override
    @Transactional
    public User save(User user) {
        Optional<Role> optionalRoleUser = roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        optionalRoleUser.ifPresent(roles::add);
        if(user.isAdmin()){
            Optional<Role> optionalRoleAdmin=roleRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }
        user.setRoles(roles);
        String passwordEncoded = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncoded);

        return repository.save(user);
    }

    @Override
    public boolean existsByUsername(String username){
        return repository.existsByUsername(username); 
    }

}
