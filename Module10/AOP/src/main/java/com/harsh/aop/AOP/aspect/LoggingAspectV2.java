package com.harsh.aop.AOP.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspectV2 {

    @Before("servicePointcut()")
    public void beforeService(JoinPoint joinPoint){
        log.info("Before service called, {}",joinPoint.getSignature());
    }

    @After("servicePointcut()")
    public void afterService(JoinPoint joinPoint){
        log.info("After service called, {}",joinPoint.getSignature());
    }

    @AfterReturning(value = "servicePointcut()", returning = "result")
    public void afterReturningService(JoinPoint joinPoint, Object result){
        log.info("AfterReturning service called, {}",joinPoint.getSignature());
        log.info("Result: {}",result);
    }

    @AfterThrowing(value = "servicePointcut()", throwing = "ex")
    public void afterThrowingService(JoinPoint joinPoint, Exception ex){
        log.info("AfterThrowing service called, {}",joinPoint.getSignature());
        log.error("Exception: {}",ex.getMessage());
    }

    @Around("servicePointcut()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        Long startTime = System.currentTimeMillis();
       Object obj =  joinPoint.proceed();
       Long endTime = System.currentTimeMillis();
       log.info("Execution time of {} is {} ms",joinPoint.getSignature(),endTime-startTime);
       return obj;

    }

    @Pointcut("execution(* com.harsh.aop.AOP.services.impl.*.*(..))")
    public void servicePointcut(){}
}
