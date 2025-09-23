package com.avva.five.pointcut;

import com.avva.five.common.SimpleAroundAdvice;
import com.avva.five.common_2.GrammyGuitarist;
import com.avva.five.common_2.Guitar;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

public class NamePointcutDemo {
    public static void main(String[] args) {
        GrammyGuitarist johnMayer = new GrammyGuitarist();

        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.addMethodName("sing");
        pointcut.addMethodName("rest");

        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new SimpleAroundAdvice());
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(johnMayer);
        proxyFactory.addAdvisor(advisor);

        GrammyGuitarist proxy = (GrammyGuitarist) proxyFactory.getProxy();
        proxy.sing();
        proxy.sing(new Guitar());
        proxy.rest();
        proxy.talk();
    }
}

//DEBUG: SimpleAroundAdvice - >> Invoking sing
//INFO : GrammyGuitarist - sing: Gravity is working against me
//And gravity wants to bring me down
//DEBUG: SimpleAroundAdvice - >> Done
//DEBUG: SimpleAroundAdvice - >> Invoking sing
//INFO : GrammyGuitarist - play: G C G C Am D7
//DEBUG: SimpleAroundAdvice - >> Done
//DEBUG: SimpleAroundAdvice - >> Invoking rest
//INFO : GrammyGuitarist - zzz
//DEBUG: SimpleAroundAdvice - >> Done
//INFO : GrammyGuitarist - talk
