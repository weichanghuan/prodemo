package com.test.aop.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 * @description:(基于cglib动态代理实现aop)
 * @date 2018年1月3日 下午9:30:06
 * @since JDK 1.6
 */
public class TestCGLibProxy implements MethodInterceptor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Object target;

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public TestCGLibProxy() {
        super();
    }

    public TestCGLibProxy(Object target) {
        super();
        this.target = target;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        logger.debug("开始拦截");
        StringBuffer sb = new StringBuffer();
        for (Object object : args) {
            sb.append(object.toString() + ",");
        }
        logger.debug("输入参数为：" + sb);
        String result = (String) proxy.invokeSuper(obj, args);
        logger.debug(result);
        logger.debug("结束拦截");
        return result;

    }

    public static void main(String[] args) throws Throwable {
        // 创建一个cglib的增强器，用来在运行时生成类
        Enhancer enhancer = new Enhancer();
        // 设置要继承的目标类
        enhancer.setSuperclass(TestService.class);
        // 设置回调（增强规则）
        enhancer.setCallback(new TestCGLibProxy());
        // 生成新的代理类
        TestService create = (TestService) enhancer.create();
        create.sayHello2(2, "3");

    }

}
