package com.franco.curso.springboot.webapp.springboot_web.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.franco.curso.springboot.webapp.springboot_web.models.User;



@Controller
public class UserController {
    
    @GetMapping("/details")
    public String details(Model model){
        User user = new User("Jonathan", "Franco");

        model.addAttribute("title", "Hola Mundo Spring Boot");
        model.addAttribute("user",user);
        return "details";
    }

    @GetMapping("/details2")
    public String details(Map<String, Object> model){
        model.put("title", "Hola Mundo Spring Boot");
        model.put("name", "Jonathan");
        model.put("lastname", "franco");
        return "details";
    }

    @GetMapping("list")
    public String list(ModelMap model) {
        //model.addAttribute("users", userModel());
        model.addAttribute("title","Listado de Usuarios");
        return "list";
    }

    @ModelAttribute("users")
    public List<User> userModel(){
        User user = new User("Andres", "Lopez", "andres@correo.com");
        User user2 = new User("Juan", "Flores", "juan@correo.com");
        User user3 = new User("Prueba", "Uno");
        //List<User> users = Arrays.asList(user, user2,user3);
        //List<User> users = new ArrayList<User>();
        return Arrays.asList(user, user2,user3);
    }
    
}
