package com.avva.five.pointcut;

import com.avva.five.common.SimpleAroundAdvice;
import com.avva.five.common_2.Guitarist;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class AspectjexpPointcutDemo {
    public static void main(String[] args) {
        Guitarist johnMayer = new Guitarist();

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* sing*(..))");
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new SimpleAroundAdvice());

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(johnMayer);
        proxyFactory.addAdvisor(advisor);

        Guitarist proxy = (Guitarist) proxyFactory.getProxy();
        proxy.sing();
        proxy.sing2();
        proxy.rest();
    }
}
