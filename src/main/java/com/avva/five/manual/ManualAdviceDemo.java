package com.avva.five.manual;

import org.springframework.aop.framework.ProxyFactory;

public class ManualAdviceDemo {

    public static void main(String[] args) {
        Concert concert = new Concert();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new SimpleBeforeAdvice());
        proxyFactory.addAdvice(new SimpleAfterAdvice());
        proxyFactory.addAdvice(new SimpleAroundAdvice());
        proxyFactory.setTarget(concert);

        Performance proxy = (Performance) proxyFactory.getProxy();

        proxy.execute();
    }
}

//INFO : SimpleBeforeAdvice - Before: set up concert hall.
//INFO : SimpleAroundAdvice - Around: starting timer
//INFO : Concert -  ... La la la la laaaa ...
//INFO : SimpleAroundAdvice - Around: concert duration = 2005
//INFO : SimpleAfterAdvice - After: offer standing ovation.
