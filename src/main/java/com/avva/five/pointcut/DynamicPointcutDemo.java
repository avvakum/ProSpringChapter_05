package com.avva.five.pointcut;

import com.avva.five.common.SimpleAroundAdvice;
import com.avva.five.common_2.GoodGuitarist;
import com.avva.five.common_2.Singer;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class DynamicPointcutDemo {
    public static void main(String[] args) {
        GoodGuitarist target = new GoodGuitarist();
        Advisor advisor = new DefaultPointcutAdvisor(new SimpleDynamicPointcut(), new SimpleAroundAdvice());
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);

        Singer proxy = (Singer) proxyFactory.getProxy();

        proxy.sing("C");
        proxy.sing("c");
        proxy.sing("E");
        proxy.sing();
    }
}

//DEBUG: SimpleDynamicPointcut - Static check for sing
//DEBUG: SimpleDynamicPointcut - Static check for toString
//DEBUG: SimpleDynamicPointcut - Static check for clone
//DEBUG: SimpleDynamicPointcut - Static check for sing
//DEBUG: SimpleDynamicPointcut - Dynamic check for sing
//DEBUG: SimpleAroundAdvice - >> Invoking sing
//INFO : Singer - Singing in the key of C
//DEBUG: SimpleAroundAdvice - >> Done
//DEBUG: SimpleDynamicPointcut - Dynamic check for sing
//DEBUG: SimpleAroundAdvice - >> Invoking sing
//INFO : Singer - Singing in the key of c
//DEBUG: SimpleAroundAdvice - >> Done
//DEBUG: SimpleDynamicPointcut - Dynamic check for sing
//INFO : Singer - Singing in the key of E
//DEBUG: SimpleDynamicPointcut - Dynamic check for sing
//INFO : GoodGuitarist - Head on your heart, arms around me
