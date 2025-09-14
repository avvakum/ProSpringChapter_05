package com.avva.five.pointcut;

import com.avva.five.common.GoodGuitarist;
import com.avva.five.common.GreatGuitarist;

import com.avva.five.common.SimpleAroundAdvice;
import com.avva.five.common.Singer;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class StaticPointcutDemo {
    public static void main(String[] args) {

        // targets
        GoodGuitarist johnMayer = new GoodGuitarist();
        GreatGuitarist ericClapton = new GreatGuitarist();

        Singer proxyOne;
        Singer proxyTwo;

        Pointcut pointcut = new SimpleStaticPointcut();
        Advice advice = new SimpleAroundAdvice();
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvisor(advisor);
        proxyFactory.setTarget(johnMayer);
        proxyOne = (Singer) proxyFactory.getProxy();
        proxyOne.sing();

        proxyFactory = new ProxyFactory();
        proxyFactory.addAdvisor(advisor);
        proxyFactory.setTarget(ericClapton);
        proxyTwo = (Singer) proxyFactory.getProxy();


        proxyTwo.sing();
        //INFO : GoodGuitarist - Head on your heart, arms around me
        //INFO : GreatGuitarist - You've got my soul in your hand
    }
}


