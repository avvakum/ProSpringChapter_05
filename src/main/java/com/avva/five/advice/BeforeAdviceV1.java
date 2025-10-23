package com.avva.five.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class BeforeAdviceV1 {
    private static Logger LOGGER = LoggerFactory.getLogger(BeforeAdviceV1.class);

    @Before("execution(* com.avva.five..sing*(com.avva.five.common_2.Guitar))")
    public void simpleBeforeAdvice(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        LOGGER.info(" > Executing: {} from {}", signature.getName(), signature.getDeclaringTypeName() );
    }
}
