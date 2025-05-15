package com.vic.lovelytrip.aspect;

import com.vic.lovelytrip.dto.restservice.RestServiceResponse;
import com.vic.lovelytrip.lib.BusinessException;
import com.vic.lovelytrip.lib.HttpStatusEnum;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HttpExceptionHandlingAspect {

    private final Logger logger = LoggerFactory.getLogger(HttpExceptionHandlingAspect.class);

    @Pointcut("execution(* com.vic.lovelytrip.controller..*(..))")
    private void httpEntryMethods() {

    }

    @Around("httpEntryMethods()")
    public Object handleEndPointException(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        
        try{

            return proceedingJoinPoint.proceed(); // throw Throwable

        }catch (BusinessException businessException) {
            logger.warn("Business Exception in {}: {}", proceedingJoinPoint.getSignature().getName(), businessException.getMessage());

            return generateErrorRestResponse(
                      businessException.getHttpStatusEnum().getStatusCode()
                    , businessException.getMessage()
                    , businessException.getMessageInfoContainer()
            );

        }catch (Exception exception) {

            logger.error("Unexpected exception in {}: {}", proceedingJoinPoint.getSignature().getName(), exception.getMessage());

            return generateErrorRestResponse(
                      HttpStatusEnum.INTERNAL_SERVER_ERROR.getStatusCode()
                    , HttpStatusEnum.INTERNAL_SERVER_ERROR.getStatusMessage()
                    , null
            );

        }
    }

    private RestServiceResponse<Object> generateErrorRestResponse(String errorCode, String errorMessage, Object errors) {
        RestServiceResponse<Object> response = new RestServiceResponse<>();

        response.setResponseCode(errorCode);
        response.setResponseDescription(errorMessage);
        response.setErrors(errors);
        response.setResponseBody(null);

        return response;
    }

}
