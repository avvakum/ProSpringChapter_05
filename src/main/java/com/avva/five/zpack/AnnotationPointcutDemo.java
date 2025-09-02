package com.avva.five.zpack;

import com.avva.five.common.AdviceRequired;
import com.avva.five.common.AnnotatedGuitarist;
import com.avva.five.common.SimpleAroundAdvice;
import com.avva.five.common_2.Guitar;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

public class AnnotationPointcutDemo {
    public static void main(String[] args) {
        AnnotatedGuitarist johnMayer = new AnnotatedGuitarist();
        AnnotationMatchingPointcut pointcut = AnnotationMatchingPointcut.forMethodAnnotation(AdviceRequired.class);

        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new SimpleAroundAdvice());
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(johnMayer);
        proxyFactory.addAdvisor(advisor);

        AnnotatedGuitarist proxy = (AnnotatedGuitarist) proxyFactory.getProxy();
        proxy.sing(new Guitar());
        proxy.rest();

    }
}
