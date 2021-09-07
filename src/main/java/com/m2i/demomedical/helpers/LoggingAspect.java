package com.m2i.demomedical.helpers;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import static com.m2i.demomedical.helpers.LoggingHelper.LogLevel.INFO;

@Aspect
@Component
@Order(0)
public class LoggingAspect {

    final private LoggingHelper lh;

    public LoggingAspect(LoggingHelper lh) {
        this.lh = lh;
    }

    // add(..) = Add ayant n'importe quel paramètres
    @Before("execution(* com.m2i.demomedical.controller.PatientController.add(..) )")
    public void callOnPatientGet() {
        System.out.println("Je suis un aspect, je m'execute avant Get Patient");
    }

    @After("execution(* com.m2i.demomedical.controller.PatientController.add(..) )")
    public void callAfterPatientGet() {
        System.out.println("Je suis un aspect, je m'execute après Get Patient");
    }

    @Before("execution(* com.m2i.demomedical.service.PatientService.*(..) )")
    public void callOnPatientServiceCall() {
        System.out.println("Je suis un aspect, je m'execute avant chaque méthode de patient Service");
    }

    @Around("execution(* com.m2i.demomedical.controller.PatientController.*(..))")
    public Object frenchAroundAdvice(ProceedingJoinPoint
                                             proceedingJoinPoint){
        System.out.println("Valeur du param`etre i dans saluer : " +
                proceedingJoinPoint.getArgs()[0]);
        System.out.println("Signature : " + proceedingJoinPoint.
                getSignature());
        Object value = null;
        try {
            value = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("Valeur de retour de saluer : " + value);
        return value;
    }

    @Around("execution(* com.m2i.demomedical.controller.*.*(..))")
    public Object logActions(ProceedingJoinPoint
                                             proceedingJoinPoint){
        String chaine = "Valeur du param`etre i dans saluer : ";
        if( proceedingJoinPoint.getArgs().length > 0 )
            chaine += proceedingJoinPoint.getArgs()[0];
        chaine += "Signature : " + proceedingJoinPoint.
                getSignature();

        lh.log( chaine , INFO );
        Object value = null;
        try {
            value = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        chaine = "Valeur de retour de saluer : " + value;
        lh.log( chaine , INFO );
        return value;
    }



}
