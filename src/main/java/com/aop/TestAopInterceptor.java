package com.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;

/**
 * @Author: Wch
 * @Date 2018/6/8
 */
public class TestAopInterceptor implements MethodInterceptor, ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(TestAopInterceptor.class);

    private ApplicationContext applicationContext;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        Object obj = invocation.getThis();
        final Object[] arguments = invocation.getArguments();
        logger.info("开始拦截");
        return invocation.proceed();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
