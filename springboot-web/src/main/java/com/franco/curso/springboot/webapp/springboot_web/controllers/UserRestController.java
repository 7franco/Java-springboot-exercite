package com.franco.curso.springboot.webapp.springboot_web.controllers;



import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.franco.curso.springboot.webapp.springboot_web.models.User;
import com.franco.curso.springboot.webapp.springboot_web.models.dto.UserDto;

@RestController
@RequestMapping("/api")
public class UserRestController {
    

    @GetMapping("/details-map")
    //@RequestMapping(path="/details", method= RequestMethod.GET)
    public Map<String, Object> detailMap(){
        User user = new User("Jonathan", "Franco");
        Map<String, Object> body = new HashMap<String, Object>();

        body.put("title", "Hola Mundo Spring Boot");
        body.put("user", user);
        return body;
    }

    @GetMapping("/details3")
    public UserDto details(){
        UserDto userDto = new UserDto();
        User user = new User("Jonathan", "Franco");
        userDto.setUser(user);
        userDto.setTitle("Hola Mundo Spring Boot");
        return userDto;
    }

    @GetMapping("/list")
    public List<User> list(){
        User user = new User("Andres", "Lopez");
        User user2 = new User("Juan", "Flores");
        User user3 = new User("Prueba", "Uno");
        
        List<User> users = Arrays.asList(user, user2,user3);
        //List<User> users = new ArrayList<User>();
        //users.add(user);
        //users.add(user2);
        //users.add(user3);
        
        return users; 
    }

}
