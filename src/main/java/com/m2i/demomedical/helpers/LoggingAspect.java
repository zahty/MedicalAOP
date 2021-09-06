package com.m2i.demomedical.helpers;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(0)
public class LoggingAspect {

    private LoggingHelper lh;

    @After("execution(* com.m2i.demomedical.controller.PatientController.list(..))")
    public void frenchAroundAdvice() throws Throwable {

        //proceedingJoinPoint.proceed();
        System.out.println("Caling you");
    }
}
