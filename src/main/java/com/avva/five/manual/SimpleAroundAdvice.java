package com.avva.five.manual;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

public class SimpleAroundAdvice implements MethodInterceptor {
    private static Logger LOGGER = LoggerFactory.getLogger(SimpleAroundAdvice.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        LOGGER.info("Around: starting timer");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(invocation.getMethod().getName());
        Object returnValue =invocation.proceed();
        stopWatch.stop();
        LOGGER.info("Around: concert duration = {}", stopWatch.getTotalTimeMillis());
        return returnValue;
    }
}
