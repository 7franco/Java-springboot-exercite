package com.franco.curso.springboot.app.interceptor.springboot_interceptor.interceptors;

import java.util.Date;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("timeInterceptor")
public class LoadingTimeInterceptor implements HandlerInterceptor{

    private static final Logger logger= LoggerFactory.getLogger(LoadingTimeInterceptor.class);
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
                logger.info("LoadingTimeInterceptor: preHandler entrando ....."+ ((HandlerMethod) handler).getMethod().getName());
                long start =System.currentTimeMillis();
                
                request.setAttribute("start", start);
                
                Random random = new Random();
                int delay = random.nextInt(500);
                Thread.sleep(delay);

                //return true;
                Map<String, Object> result = new HashMap<>();
                result.put("error", "no tienes acceso a este recurso");
                result.put("date", new Date());

                ObjectMapper mapper = new ObjectMapper();
                String jsonString = mapper.writeValueAsString(result);
                response.setContentType("application/json");
                response.setStatus(401);
                response.getWriter().write(jsonString);
                return false;
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable ModelAndView modelAndView) throws Exception {
                HandlerMethod method = ((HandlerMethod) handler);

                long end = System.currentTimeMillis();
                long start = (long) request.getAttribute("start");
                long result = end-start;
                logger.info("Tiempo transcurrido: "+ result+" milisegundos");
                logger.info("LoadingTimeInterceptor: postHandler saliendo ....."+ method.getMethod().getName());    
                
    }

    
    
}
