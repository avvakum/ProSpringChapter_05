package com.avva.five.introduction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.IntroductionAdvisor;
import org.springframework.aop.framework.ProxyFactory;

public class IntroductionDemo {
    private static Logger LOGGER = LoggerFactory.getLogger(IntroductionDemo.class);

    public static void main(String[] args) {
        Contact target = new Contact();
        target.setName("John Mayer");

        IntroductionAdvisor advisor = new IsModifiedAdvisor();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);
        proxyFactory.setOptimize(true);

        Contact proxy = (Contact) proxyFactory.getProxy();
        IsModified proxyInterface = (IsModified) proxy;
        LOGGER.info("Is Contact? => {} ", (proxy instanceof Contact));
        LOGGER.info("Is IsModified? => {} " , (proxy instanceof IsModified));
        LOGGER.info("Has been modified? => {} " , proxyInterface.isModified());

        proxy.setName("John Mayer");
        LOGGER.info("Has been modified? => {} " , proxyInterface.isModified());

        proxy.setName("Ben Barnes");
        LOGGER.info("Has been modified? => {} " , proxyInterface.isModified());
    }
}

// INFO : IntroductionDemo - Is Contact? => true
// INFO : IntroductionDemo - Is IsModified? => true
// INFO : IntroductionDemo - Has been modified? => false
// INFO : IntroductionDemo - Has been modified? => false
// INFO : IntroductionDemo - Has been modified? => true