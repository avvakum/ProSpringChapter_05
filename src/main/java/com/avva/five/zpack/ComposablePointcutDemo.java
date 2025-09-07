package com.avva.five.zpack;

import com.avva.five.advanced.SimpleBeforeAdvice;
import com.avva.five.common_2.GrammyGuitarist;
import com.avva.five.common_2.Guitar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcher;

import java.lang.reflect.Method;

public class ComposablePointcutDemo {
    private static Logger LOGGER = LoggerFactory.getLogger(ComposablePointcutDemo.class);

    public static void main(String[] args) {
        GrammyGuitarist johnMayer = new GrammyGuitarist();
        ComposablePointcut pointcut = new ComposablePointcut(ClassFilter.TRUE, new SingMethodMatcher());

        LOGGER.info("Test 1 >> ---------------------------");
        GrammyGuitarist proxy = getProxy(pointcut, johnMayer);
        testInvoke(proxy);
        LOGGER.info("Test 2 >> ---------------------------");
        pointcut.union(new TalkMethodMatcher());
        proxy = getProxy(pointcut, johnMayer);
        testInvoke(proxy);
        LOGGER.info("Test 3 >> ---------------------------");
        pointcut.intersection(new RestMethodMatcher());
        proxy = getProxy(pointcut, johnMayer);
        testInvoke(proxy);

    }

    private static GrammyGuitarist getProxy(ComposablePointcut pointcut, GrammyGuitarist target) {
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new SimpleBeforeAdvice());

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);
        return (GrammyGuitarist) proxyFactory.getProxy();
    }

    private static void testInvoke(GrammyGuitarist proxy) {
        proxy.sing();
        proxy.sing(new Guitar());
        proxy.talk();
        proxy.rest();
    }
}

class SingMethodMatcher extends StaticMethodMatcher {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return (method.getName().startsWith("si"));
    }
}

class TalkMethodMatcher extends StaticMethodMatcher {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return "talk".equals(method.getName());
    }
}

class RestMethodMatcher extends StaticMethodMatcher {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return (method.getName().endsWith("st"));
    }
}
