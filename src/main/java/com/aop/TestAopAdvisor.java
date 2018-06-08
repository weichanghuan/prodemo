package com.aop;

import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;

/**
 * @Author: Wch
 * @Date 2018/6/8
 */
public class TestAopAdvisor extends AbstractBeanFactoryPointcutAdvisor {

    public static final String TEST_AOP_ADVISOR_BEAN_NAME = "testAop.Advisor";

    private String[] basePackages;

    @Override
    public Pointcut getPointcut() {
        TestAopPointcut pointcut = new TestAopPointcut(basePackages);
        return pointcut;
    }

    public void setBasePackages(String[] basePackages) {
        this.basePackages = basePackages;
    }
}
