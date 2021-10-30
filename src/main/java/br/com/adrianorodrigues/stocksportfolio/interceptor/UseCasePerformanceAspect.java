package br.com.adrianorodrigues.stocksportfolio.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Aspect
public class UseCasePerformanceAspect {

    @Pointcut("within(br.com.adrianorodrigues.stocksportfolio.usecase..*)")
    public void useCasePointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /*
     * Log execution time for any usecase
     */
    @Around("useCasePointcut()")
    public Object useCasePerformanceLogger(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Logger log = LoggerFactory.getLogger(methodSignature.getDeclaringTypeName());

        Instant init = Instant.now();
        log.debug("[USECASE] execution init");
        Object result = joinPoint.proceed();
        Instant end = Instant.now();
        Long time = end.toEpochMilli() - init.toEpochMilli();
        log.debug("[USECASE] execution time: {} ms", time);

        return result;
    }

}
