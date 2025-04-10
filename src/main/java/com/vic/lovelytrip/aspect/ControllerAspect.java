package com.vic.lovelytrip.aspect;

import com.vic.lovelytrip.dto.restservice.RestServiceResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerAspect {

//    @Around("PointCuts.allControllerMethods() && PointCuts.restControllerMethods()")
    public <T> RestServiceResponse<T> wrapResultToResponse(ProceedingJoinPoint joinPoint) throws Throwable {

        @SuppressWarnings("unchecked")
        T resultBody = (T) joinPoint.proceed();

        RestServiceResponse<T> response = new RestServiceResponse<>();
        response.setResponseBody(resultBody);

        return response;

    }


}
