package com.avva.five.pointcut;

import com.avva.five.common.SimpleAroundAdvice;
import com.avva.five.common_2.Guitarist;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;

public class RegexpPointcutDemo {
    public static void main(String[] args) {
        Guitarist johnMayer = new Guitarist();

        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        pointcut.setPattern(".*sing.*");

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

//DEBUG: SimpleAroundAdvice - >> Invoking sing
//INFO : Guitarist - Just keep me where the light is
//DEBUG: SimpleAroundAdvice - >> Done
//DEBUG: SimpleAroundAdvice - >> Invoking sing2
//INFO : Guitarist - And wrap me in your arms
//DEBUG: SimpleAroundAdvice - >> Done
//INFO : Guitarist - zzz...zzz...zzz...
