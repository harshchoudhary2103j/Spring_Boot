package com.harsh.aop.AOP.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("execution(* com.harsh.aop.AOP.services.impl.*.*(..))")
    public void beforeOrderPackage(JoinPoint joinPoint){
        log.info("Before orderPackage called from impl package, {}",joinPoint.getKind());
        log.info("Before orderPackage called from impl package, {}",joinPoint.getSignature());

    }
    @Before("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void beforeTransactional(JoinPoint joinPoint){
        log.info("Before Transactional called from impl package, {}",joinPoint.getKind());
        log.info("Before Transactional called from impl package, {}",joinPoint.getSignature());
    }


    @Before("@annotation(com.harsh.aop.AOP.aspect.MyLogging)")
    public void beforeMyLogging(JoinPoint joinPoint){
        log.info("Before MyLogging called from impl package, {}",joinPoint.getKind());
        log.info("Before MyLogging called from impl package, {}",joinPoint.getSignature());
    }

    @Before("myLoggingPointcut()")
    public void beforeMyLoggingPointcut(JoinPoint joinPoint){
        log.info("Before MyLoggingPointcut called from impl package, {}",joinPoint.getKind());
        log.info("Before MyLoggingPointcut called from impl package, {}",joinPoint.getSignature());
    }

    @Pointcut("@annotation(com.harsh.aop.AOP.aspect.MyLogging) && execution(* com.harsh.aop.AOP.services.impl.*.*(..))")
    public void myLoggingPointcut(){}

}
