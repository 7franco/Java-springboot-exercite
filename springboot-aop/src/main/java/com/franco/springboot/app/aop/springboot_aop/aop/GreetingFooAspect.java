package com.franco.springboot.app.aop.springboot_aop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
@Aspect
public class GreetingFooAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    //@Before("execution(String com.franco.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    @Before("GreetingServicePointcuts.greetingFooLoggerPointCut()")
    public void loggerBefore(JoinPoint jointPoint){
        String method = jointPoint.getSignature().getName();
        String args = Arrays.toString(jointPoint.getArgs());

        logger.info("2. Antes: "+ method+" invocado con los argumentos "+ args);
    }

    @After("GreetingServicePointcuts.greetingFooLoggerPointCut()")
    public void loggerAfter(JoinPoint jointPoint){
        String method = jointPoint.getSignature().getName();
        String args = Arrays.toString(jointPoint.getArgs());

        logger.info("2 segundo.Despues: "+ method+" con los argumentos "+ args);
    }
}
