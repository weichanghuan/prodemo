/**
 * Created on  13-09-18 16:37
 */
package com.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;


public class DataSourcesNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("datasource", new DataSoucesParser());
    }
}
