package com.franco.springboot.app.aop.springboot_aop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Aspect
@Component
public class GreetingAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    //@Pointcut("execution(String com.franco.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    //private void greetingLoggerPointCut(){}

    //@Before("execution(String com.franco.springboot.app.aop.springboot_aop.services.GreetingService.sayHello(..))")
    //@Before("execution(* *.*(..))")
    @Before("GreetingServicePointcuts.greetingLoggerPointCut()")
    public void loggerBefore(JoinPoint jointPoint){
        String method = jointPoint.getSignature().getName();
        String args = Arrays.toString(jointPoint.getArgs());

        logger.info("Antes: "+ method+" con los argumentos "+ args);
    }

    //@After("execution(String com.franco.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    @After("GreetingServicePointcuts.greetingLoggerPointCut()")
    public void loggerAfter(JoinPoint jointPoint){
        String method = jointPoint.getSignature().getName();
        String args = Arrays.toString(jointPoint.getArgs());

        logger.info("Despues: "+ method+" con los argumentos "+ args);
    }

    //@AfterReturning("execution(String com.franco.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    @AfterReturning("GreetingServicePointcuts.greetingLoggerPointCut()")
    public void loggerAfterReturning(JoinPoint jointPoint){
        String method = jointPoint.getSignature().getName();
        String args = Arrays.toString(jointPoint.getArgs());

        logger.info("Despues de retornar: "+ method+" con los argumentos "+ args);
    }

    //@AfterThrowing("execution(String com.franco.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    @AfterThrowing("GreetingServicePointcuts.greetingLoggerPointCut()")
    public void loggerAfterThrowing(JoinPoint jointPoint){
        String method = jointPoint.getSignature().getName();
        String args = Arrays.toString(jointPoint.getArgs());

        logger.info("despues de lanzar la exception: "+ method+" con los argumentos "+ args);
    }

    //@Around("execution(String com.franco.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    @Around("GreetingServicePointcuts.greetingLoggerPointCut()")
    public Object loggerAround(ProceedingJoinPoint joinPoint) throws Throwable{
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        Object result  = null;
        try {
            logger.info("El metodo "+ method+ "() con los parametros "+ args);
            // algo antes
            result  = joinPoint.proceed();    
            // algo despues
            logger.info("El metodo "+ method+ "() retorna el resultado "+ result);

            return result;   
        } catch (Throwable e) {            
             logger.error("Error en la llamada del metodo del ", method +"()");
             throw e;
        }
        
        
    }

}
