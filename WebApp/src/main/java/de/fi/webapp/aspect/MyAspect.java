package de.fi.webapp.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
@Aspect
@Slf4j
public class MyAspect {




    @Before( "Pointcuts.PersonControllerMethodes()")  // Pointcut
    public void peterle(JoinPoint joinPoint) {
        log.warn("############ "+ joinPoint.getSignature().getName() + " #################################"); // Advice

/*
        Instant start = Instant.now();
        //
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println(duration.toMillis());
*/
    }

    @AfterReturning( value = "Pointcuts.PersonControllerMethodes()", returning = "result")
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

    @Around( value = "@within(de.fi.webapp.aspect.Benchmark)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        Instant start = Instant.now();
        var result = joinPoint.proceed();
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());
        return result;
    }
}
