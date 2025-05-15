package com.vic.lovelytrip.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TraceableAspect {

    Logger logger = LoggerFactory.getLogger(TraceableAspect.class);

    @Pointcut("@annotation(com.vic.lovelytrip.lib.Traceable)")
    private void traceableMethods(){
    }

    @Around("traceableMethods()")
    Object logTraceableMethod(ProceedingJoinPoint joinPoint) throws Throwable {

        String methodName = joinPoint.getSignature().getName();
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(joinPoint.getTarget());

        String className = targetClass.getName();

        logger.info("Start: {}.{}", className, methodName);

        try{
            Object result = joinPoint.proceed();

            logger.info("End: {}.{}", className, methodName);

            return result;

        }catch (Throwable throwable){

            logger.error("Exception in: {}.{} with trace: {}", className, methodName, throwable.getMessage(), throwable);

            throw throwable;

        }
    }
}
