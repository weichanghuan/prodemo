package com.config;

import com.aop.TestAopAdvisor;
import com.aop.TestAopInterceptor;
import org.springframework.aop.config.AopNamespaceUtils;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.parsing.CompositeComponentDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * @Author: Wch
 * @Date 2018/6/8
 */
public class TestAopParser implements BeanDefinitionParser {

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        doParse(element, parserContext);
        return null;
    }

    synchronized private void doParse(Element element, ParserContext parserContext) {
        String[] basePackages = StringUtils.tokenizeToStringArray(element.getAttribute("base-package"), ",; \t\n");
        AopNamespaceUtils.registerAutoProxyCreatorIfNecessary(parserContext, element);
        if (!parserContext.getRegistry().containsBeanDefinition(TestAopAdvisor.TEST_AOP_ADVISOR_BEAN_NAME)) {

            Object eleSource = parserContext.extractSource(element);

            RootBeanDefinition interceptorDef = new RootBeanDefinition(TestAopInterceptor.class);
            interceptorDef.setSource(eleSource);
            interceptorDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
            String interceptorName = parserContext.getReaderContext().registerWithGeneratedName(interceptorDef);

            RootBeanDefinition advisorDef = new RootBeanDefinition(TestAopAdvisor.class);
            advisorDef.setSource(eleSource);
            advisorDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
            advisorDef.getPropertyValues().addPropertyValue(new PropertyValue("adviceBeanName", interceptorName));
            advisorDef.getPropertyValues().addPropertyValue(new PropertyValue("basePackages", basePackages));
            parserContext.getRegistry().registerBeanDefinition(TestAopAdvisor.TEST_AOP_ADVISOR_BEAN_NAME, advisorDef);

            CompositeComponentDefinition compositeDef = new CompositeComponentDefinition(element.getTagName(),
                    eleSource);
            compositeDef.addNestedComponent(new BeanComponentDefinition(interceptorDef, interceptorName));
            compositeDef.addNestedComponent(new BeanComponentDefinition(advisorDef, TestAopAdvisor.TEST_AOP_ADVISOR_BEAN_NAME));
            parserContext.registerComponent(compositeDef);

        }
    }
}
