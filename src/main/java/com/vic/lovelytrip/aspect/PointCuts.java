package com.vic.lovelytrip.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PointCuts {

    @Pointcut("within(com.vic.lovelytrip.controller..*)")
    public void allControllerMethods(){
    }

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void restControllerMethods(){}

}
