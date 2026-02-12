package com.harsh.aop.AOP.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ValidationAspect {

    @Around("servicePointcut()")
    public Object validateOrder(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Validation started...");
        Object args[] = joinPoint.getArgs();
        Long orderId = (Long) args[0];
        if(orderId > 0){
            return joinPoint.proceed();
        }
        return "Cannot place order with negative order id";
    }

    @Pointcut("execution(* com.harsh.aop.AOP.services.impl.*.*(..))")
    public void servicePointcut(){}
}
