package com.avva.five.pointcut;

import com.avva.five.common_2.GoodGuitarist;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;

public class SimpleDynamicPointcut extends DynamicMethodMatcherPointcut {
    private static Logger logger = LoggerFactory.getLogger(SimpleDynamicPointcut.class);

    @Override
    public ClassFilter getClassFilter() {
        return clazz -> (clazz == GoodGuitarist.class);
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        logger.debug("Static check for " + method.getName());
        return ("sing".equals(method.getName()));
    }

        @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        logger.debug("Dynamic check for " + method.getName());

        if (args.length == 0) {
            return false;
        }
        var key = (String) args[0];

        return key.equalsIgnoreCase("C");
    }
}
