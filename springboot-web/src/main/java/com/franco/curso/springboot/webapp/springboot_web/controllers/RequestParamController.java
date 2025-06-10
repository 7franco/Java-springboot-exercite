package com.franco.curso.springboot.webapp.springboot_web.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.franco.curso.springboot.webapp.springboot_web.models.dto.ParamDto;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/params")
public class RequestParamController {

    @GetMapping("/foo")
    public ParamDto foot(@RequestParam(required = false, defaultValue = "Hola Terricola", name = "mensaje") String message) {
        ParamDto paramDto = new ParamDto();
        paramDto.setMessage(message != null? message: "hola");
        return paramDto;
    }   

    @GetMapping("bar")
    public ParamDto bar(@RequestParam String text, @RequestParam Integer code) {
        ParamDto params = new ParamDto();
        params.setMessage(text);
        params.setCode(code);
        return params;
    }

    @GetMapping("/request")
    public ParamDto request(HttpServletRequest request) {
        ParamDto params = new ParamDto();
        Integer code = 0;
        try{
            code = Integer.parseInt(request.getParameter("code"));    
        }catch(NumberFormatException e){
        }
        params.setCode(code);
        params.setMessage(request.getParameter("message"));
        return params;
    }
    
    

}
