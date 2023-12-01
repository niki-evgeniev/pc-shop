package com.example.pcproject.Service.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;


@Aspect
@Component
public class MonitoringAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoringAspect.class);

    @Around("PointCuts.executionTime()")
    public Object logTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ExecutionTime executionTime = getAnnotation(proceedingJoinPoint);
        Long time = executionTime.time();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        var returnValue = proceedingJoinPoint.proceed();

        stopWatch.stop();

        if (stopWatch.getLastTaskTimeMillis() > time) {
            LOGGER.warn("The method {} is to slow ... Running for {} and we expected {}"
                    , proceedingJoinPoint.getSignature()
                    , stopWatch.getLastTaskTimeMillis()
                    , time);
        }

        return returnValue;
    }

    private static ExecutionTime getAnnotation(ProceedingJoinPoint proceedingJoinPoint) {

        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
        try {
            return proceedingJoinPoint.getTarget()
                    .getClass().getMethod(method.getName(), method.getParameterTypes())
                    .getAnnotation(ExecutionTime.class);

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
