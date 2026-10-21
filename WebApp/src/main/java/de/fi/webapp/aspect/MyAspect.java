package de.fi.webapp.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class MyAspect {

    @Before( "execution(* de.fi.webapp.presentation.controller.v1.PersonenController.*(..))")
    public void peterle(JoinPoint joinPoint) {
        log.warn("############ "+ joinPoint.getSignature().getName() + " #################################");


    }

    @AfterReturning( value = "execution(* de.fi.webapp.presentation.controller.v1.PersonenController.*(..))", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        log.warn("############ "+ joinPoint.getSignature().getName() + " #################################");
        log.warn(result.toString());


    }

    @AfterThrowing( value = "execution(* de.fi.webapp.presentation.controller.v1.PersonenController.*(..))", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        log.warn("############ "+ joinPoint.getSignature().getName() + " #################################");
        log.warn(ex.getMessage());


    }

    @After( value = "execution(* de.fi.webapp.presentation.controller.v1.PersonenController.*(..))")
    public void afterReturning(JoinPoint joinPoint) {
        log.warn("############ "+ joinPoint.getSignature().getName() + " #################################");
    }

    @Around( value = "execution(* de.fi.webapp.presentation.controller.v1.PersonenController.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }
}
