package com.test.aop.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @description:(基于jdk动态代理实现aop)
 * @author Administrator
 * @date 2018年1月3日 下午9:30:06
 * @since JDK 1.6
 */
public class TestJDKProxy implements InvocationHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Object target;

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public TestJDKProxy() {
        super();
    }

    public TestJDKProxy(Object target) {
        super();
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logger.debug("开始拦截");
        StringBuffer sb = new StringBuffer();
        for (Object object : args) {
            sb.append(object.toString() + ",");
        }
        logger.debug("输入参数为：" + sb);
        String result = (String) method.invoke(target, args);
        logger.debug(result);
        logger.debug("结束拦截");
        return result;
    }

    public static void main(String[] args) throws Throwable {
        TestService testService = new TestService();
        TestJDKProxy testJDKProxy = new TestJDKProxy(testService);
        ITestService newProxyInstance = (ITestService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                testService.getClass().getInterfaces(), testJDKProxy);
        // 这里在调用sayHello方法的时候其实在调用代理类的invoke
        newProxyInstance.sayHello(2, "3");
    }
}
