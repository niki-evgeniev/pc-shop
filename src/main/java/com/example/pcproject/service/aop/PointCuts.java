package com.example.pcproject.service.aop;

import org.aspectj.lang.annotation.Pointcut;

public class PointCuts {

    @Pointcut("@annotation(ExecutionTime)")
    public void executionTime() {
    }
}
