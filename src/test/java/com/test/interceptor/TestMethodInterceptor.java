package com.test.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;

/**
 * @Author: Wch
 * @Date 2018/6/7
 */
public class TestMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("调用的方法名" + methodInvocation.getMethod().getName());
        methodInvocation.proceed();
        return null;
    }

    public static void main(String[] args) {
        TestMethodInterceptor testMethodInterceptor = new TestMethodInterceptor();
        A1 a1 = new A1();
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(a1);
        pf.addAdvice(testMethodInterceptor);
        A1 a2 = (A1) pf.getProxy();
        a2.A11();
    }


}


