package com.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;


public class SourcesNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("datasource", new DataSoucesParser());
        registerBeanDefinitionParser("annotation-driven", new TestAopParser());

    }
}
