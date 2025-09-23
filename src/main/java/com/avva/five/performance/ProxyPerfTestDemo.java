package com.avva.five.performance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;

public class ProxyPerfTestDemo {
    private static Logger LOGGER = LoggerFactory.getLogger(ProxyPerfTestDemo.class);

    public static void main(String[] args) {
        SimpleBean target = new DefaultSimpleBean();

        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor(new NoOpBeforeAdvice());
        advisor.setMappedNames("advised");

        LOGGER.info("Starting tests ...");
        runCglibTests(advisor, target);
        runCglibFrozenTests(advisor, target);
        runJdkTests(advisor, target);
    }

    public static void runCglibTests(Advisor advisor, SimpleBean target) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);
        SimpleBean proxy = (SimpleBean) proxyFactory.getProxy();
        var testResults = test(proxy);
        LOGGER.info(" --- CGLIB (Standard) Test results --- \n {} ", testResults);
    }

    private static void runCglibFrozenTests(Advisor advisor, SimpleBean target) {
        ProxyFactory pf = new ProxyFactory();
        pf.setProxyTargetClass(true);
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        pf.setFrozen(true);
        SimpleBean proxy = (SimpleBean) pf.getProxy();
        var testResults = test(proxy);
        LOGGER.info(" --- CGLIB (Frozen) Test results ---\n {} ", testResults);
    }
    private static void runJdkTests(Advisor advisor, SimpleBean target) {
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        pf.setInterfaces(SimpleBean.class);
        SimpleBean proxy = (SimpleBean)pf.getProxy();
        var testResults = test(proxy);
        LOGGER.info(" --- JDK Test results ---\n {} ", testResults);
    }

    private static TestResults test(SimpleBean bean) {
        TestResults testResults = new TestResults();
        long before = System.currentTimeMillis();
        for(int x = 0; x < 500000; x++) {
            bean.advised();
        }
        long after = System.currentTimeMillis();
        testResults.advisedMethodTime = after - before;
        //---------------------------------------------

        before = System.currentTimeMillis();
        for(int x = 0; x < 500000; x++) {
            bean.unadvised();
        }
        after = System.currentTimeMillis();
        testResults.unadvisedMethodTime = after - before;
        //---------------------------------------------

        before = System.currentTimeMillis();
        for(int x = 0; x < 500000; x++) {
            bean.equals(bean);
        }
        after = System.currentTimeMillis();
        testResults.equalsTime = after - before;
        //---------------------------------------------

        before = System.currentTimeMillis();
        for(int x = 0; x < 500000; x++) {
            bean.hashCode();
        }
        after = System.currentTimeMillis();
        testResults.hashCodeTime = after - before;
        //---------------------------------------------

        Advised advised = (Advised)bean;
        before = System.currentTimeMillis();
        for(int x = 0; x < 500000; x++) {
            advised.getTargetClass();
        }
        after = System.currentTimeMillis();
        testResults.proxyTargetTime = after - before;
        //---------------------------------------------

        return testResults;
    }
}

//INFO : ProxyPerfTestDemo - Starting tests ...
//INFO : ProxyPerfTestDemo -  --- CGLIB (Standard) Test results ---
// com.avva.five.performance.TestResults@662ac478[advised=431,unadvised=186,equals =15,hashCode=19,getProxyTargetClass =8]
//INFO : ProxyPerfTestDemo -  --- CGLIB (Frozen) Test results ---
// com.avva.five.performance.TestResults@26794848[advised=166,unadvised=106,equals =93,hashCode=22,getProxyTargetClass =66]
//INFO : ProxyPerfTestDemo -  --- JDK Test results ---
// com.avva.five.performance.TestResults@7770f470[advised=429,unadvised=238,equals =160,hashCode=56,getProxyTargetClass =100]
