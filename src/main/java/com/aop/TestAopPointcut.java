package com.aop;

import com.utils.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * @Author: Wch
 * @Date 2018/6/8
 */
public class TestAopPointcut extends StaticMethodMatcherPointcut implements ClassFilter {

    private static final Logger logger = LoggerFactory.getLogger(TestAopPointcut.class);

    private String[] basePackages;


    public TestAopPointcut(String[] basePackages) {
        setClassFilter(this);
        this.basePackages = basePackages;
    }

    @Override
    public boolean matches(Class<?> clazz) {
        boolean b = matchesImpl(clazz);
        logger.trace("check class match {}: {}", b, clazz);
        return b;
    }

    private boolean matchesImpl(Class clazz) {
        if (matchesThis(clazz)) {
            return true;
        }
        Class[] cs = clazz.getInterfaces();
        if (cs != null) {
            for (Class c : cs) {
                if (matchesImpl(c)) {
                    return true;
                }
            }
        }
        if (!clazz.isInterface()) {
            Class sp = clazz.getSuperclass();
            if (sp != null && matchesImpl(sp)) {
                return true;
            }
        }
        return false;
    }

    public boolean matchesThis(Class clazz) {
        String name = clazz.getName();
        if (exclude(name)) {
            return false;
        }
        return include(name);
    }

    private boolean include(String name) {
        if (basePackages != null) {
            for (String p : basePackages) {
                if (name.startsWith(p)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean exclude(String name) {
        if (name.startsWith("java")) {
            return true;
        }
        if (name.startsWith("org.springframework")) {
            return true;
        }
        if (name.indexOf("$$EnhancerBySpringCGLIB$$") >= 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        logger.info("method is {}",method.getName());
        logger.info("targetClass is {}",targetClass.getName());
        return true;
    }


}
