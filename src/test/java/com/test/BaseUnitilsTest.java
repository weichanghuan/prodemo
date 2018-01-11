package com.test;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.unitils.UnitilsJUnit4TestClassRunner;

/**
 * @author 危常焕
 * @time 2017/11/28
 * @description https://github.com/newsoulbr/mokitopoc/blob/master/src/test/java/com/github/newsoulbr/mokitopoc/service/PersonServiceTest.java
 *              http://www.unitils.org/tutorial-reflectionassert.html
 *              http://blog.csdn.net/achuo/article/details/47726241
 *
 */
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class BaseUnitilsTest {

    protected static ApplicationContext ctx;
    protected static ConfigurableApplicationContext configurableContext;
    protected static BeanDefinitionRegistry beanDefinitionRegistry;

    @BeforeClass
    public static void setUpBeforeClass() {

        ctx = new ClassPathXmlApplicationContext("classpath*:testapplicationContext.xml");
        configurableContext = (ConfigurableApplicationContext) ctx;
        beanDefinitionRegistry = (DefaultListableBeanFactory) configurableContext.getBeanFactory();
    }

    public static void registerBean(String beanId, String className) {
        // get the BeanDefinitionBuilder
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(className);
        // get the BeanDefinition
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        // register the bean
        beanDefinitionRegistry.registerBeanDefinition(beanId, beanDefinition);
    }

    /**
     * 移除bean
     * 
     * @param beanId
     *            bean的id
     */
    public static void unregisterBean(String beanId) {
        beanDefinitionRegistry.removeBeanDefinition(beanId);
    }
}
